package controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.bean.User;
import model.dao.UserDAO;

/*
 * Checks if username/password are valid. If so, encrypts password and registers user.
 */
public class RegisterServlet extends HttpServlet {

    private Random random = new Random();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String verify = request.getParameter("verify");
        String email = request.getParameter("email").toLowerCase();

        // salt and hash password
        // http://stackoverflow.com/questions/2860943/suggestions-for-library-to-hash-passwords-in-java
        // http://stackoverflow.com/questions/5499924/convert-java-string-to-byte-array
        String hashString = null, saltString = null;
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        try {
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 2048, 160);
            SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            hashString = new String(f.generateSecret(spec).getEncoded());
            saltString = new String(salt, "ISO-8859-1");
            
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@asuka.cs.ndsu.nodak.edu:1521:asuka", "jotan", "<password>");
        
            // test if username/email already exists
            if (UserDAO.userExists(username, conn)){
                request.setAttribute("errorRegister", "Username already taken.");
                throw new Exception();
            } else if (UserDAO.emailExists(email, conn)){
                request.setAttribute("errorRegister", "An account registered to this email already exists.");
                throw new Exception();
            }

            // test if user entered same string for password-verification field as for password-field
            if (!password.equals(verify)){
                request.setAttribute("errorRegister", "Passwords do not match.");
                throw new Exception();
            }

            // insert user into DB
            PreparedStatement registerUser = null;
            String update = "insert into user_info "
                    + "values (user_seq.nextval, ? , ?, ?, ?)";

            registerUser = conn.prepareStatement(update);
            registerUser.setString(1, username);
            registerUser.setString(2, hashString);
            registerUser.setString(3, saltString);
            registerUser.setString(4, email);

            registerUser.executeUpdate();
            User newUser = UserDAO.getUserDAO().get(username, password, conn);
            request.getSession().setAttribute("user", newUser);

            registerUser.close();
            conn.close();
            
            // if login success, redirect home
            response.sendRedirect("/~jotan/servlet/grub");

        } catch (ClassNotFoundException ex) {
            request.setAttribute("errorLogin", ex.getMessage());
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            request.setAttribute("errorLogin", ex.getMessage());
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            request.setAttribute("errorLogin", ex.getMessage());
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            request.setAttribute("errorLogin", ex.getMessage());
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            request.getRequestDispatcher("/WEB-INF/loginOrRegisterPage.jsp").forward(request, response);
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        doPost(request, response);

    }
}
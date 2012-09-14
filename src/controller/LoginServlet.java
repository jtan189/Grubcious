package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.bean.User;
import model.dao.UserDAO;

/**
 * Login user if supplied with correct credentials.
 * http://stackoverflow.com/questions/1945377/authenticating-the-username-password-by-using-filters-in-java-contacting-with
 */
public class LoginServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        Connection conn = null;
        
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@asuka.cs.ndsu.nodak.edu:1521:asuka", "jotan", "<password>");
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = UserDAO.getUserDAO().get(username, password, conn);
        if (user != null) {
            request.getSession().setAttribute("user", user); // Put user in session.
            response.sendRedirect("/~jotan/servlet/grub");
        } else {
            request.setAttribute("errorLogin", "Incorrect username/password"); // Set error msg for ${errorLogin}
            request.getRequestDispatcher("/WEB-INF/loginOrRegisterPage.jsp").forward(request, response); // Go back to login page.
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        doPost(request, response);

    }
}
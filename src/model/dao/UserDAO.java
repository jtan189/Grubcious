package model.dao;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import model.bean.User;

/*
 * Data access object for a user.
 */
public class UserDAO {

    private static UserDAO userDAO;

    public static UserDAO getUserDAO() {
        if (userDAO == null) {
            userDAO = new UserDAO();
        }
        return userDAO;
    }

    /*
     * Check if a user already exists.
     */
    public static boolean userExists(String username, Connection conn) {

        PreparedStatement retrieveUser = null;
        boolean exists = false;
        ResultSet rset = null;

        // check for user with matching username
        String query = "select * "
                + "from user_info "
                + "where username = ?";
        try {

            retrieveUser = conn.prepareStatement(query);
            retrieveUser.setString(1, username.toLowerCase());

            //run the query, return a result set        
            rset = retrieveUser.executeQuery();

            // check if found a user
            if (rset.next()) {
                exists = true;
            }

            //clean up database classes
            retrieveUser.close();
            rset.close();

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return exists;
    }

    /*
     * Check if a user already exists with the given email.
     */
    public static boolean emailExists(String email, Connection conn) {

        PreparedStatement retrieveUser = null;
        boolean exists = false;
        ResultSet rset = null;

        String query = "select * "
                + "from user_info "
                + "where email = ?";
        try {

            retrieveUser = conn.prepareStatement(query);
            retrieveUser.setString(1, email);

            rset = retrieveUser.executeQuery();

            // check if found a user
            if (rset.next()) {
                exists = true;
            }

            //clean up database classes
            retrieveUser.close();
            rset.close();

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return exists;
    }

    /*
     * Get the user with the given username and password. If user does not exist,
     * return null.
     * 
     * http://stackoverflow.com/questions/2860943/suggestions-for-library-to-hash-passwords-in-java
     */
    public User get(String username, String password, Connection conn) {

        PreparedStatement retrieveUser = null;
        ResultSet rset = null;
        List<User> users = new ArrayList<User>();

        String query = "select * "
                + "from user_info "
                + "where username = ?";

        try {
            // retrieve user with given username
            retrieveUser = conn.prepareStatement(query);
            retrieveUser.setString(1, username);

            //run the query, return a result set        
            rset = retrieveUser.executeQuery();

            while (rset.next()) {
                User userRow = new User();
                // set values
                userRow.setUserID(rset.getInt("userID"));
                userRow.setUserName(rset.getString("userName"));
                userRow.setPassword(rset.getString("password"));
                userRow.setSalt(rset.getString("salt"));
                userRow.setEmail(rset.getString("email"));

                users.add(userRow);

            }

            //clean up database classes
            retrieveUser.close();
            rset.close();

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (users.isEmpty()) {
            return null; // no user with given username found
        } else {
            // get the user (should only be one)
            User userRetrieved = users.get(0);
            String hashSupplied = null;
            String hashRetrieved = users.get(0).getPassword();

            // generate hash for the password string supplied (using the salt from userRetrieved)
            try {
                byte[] salt = userRetrieved.getSalt().getBytes("ISO-8859-1");
                KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 2048, 160);
                SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
                hashSupplied = new String(f.generateSecret(spec).getEncoded());
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidKeySpecException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            // check if hashes match. if so, return user.
            if (hashSupplied.equals(hashRetrieved)) {
                return userRetrieved;
            } else {
                return null;
            }
        }

    }
}

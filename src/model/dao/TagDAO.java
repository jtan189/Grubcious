package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Data access object for a tag.
 */
public class TagDAO {

    private static TagDAO tagDAO;

    public static TagDAO getTagDAO() {
        if (tagDAO == null) {
            tagDAO = new TagDAO();
        }
        return tagDAO;
    }

    /*
     * Check if a tag already exists.
     */
    public boolean exists(String tag, Connection conn) {

        PreparedStatement retrieveTag = null;
        boolean exists = false;
        ResultSet rset = null;

        String query = "select * "
                + "from tag "
                + "where tagname = ?";
        try {

            retrieveTag = conn.prepareStatement(query);
            retrieveTag.setString(1, tag.toLowerCase());

            //run the query, return a result set        
            rset = retrieveTag.executeQuery();

            // check if found an entry
            if (rset.next()) {
                exists = true;
            }

            //clean up database classes
            retrieveTag.close();
            rset.close();

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return exists;
    }

    /*
     * Insert a tag into the database.
     */
    public void insert(List<String> tags, Connection conn) throws SQLException {
        PreparedStatement insert = null;

        String update = "insert into tag "
                + "values (tag_seq.nextval, ? , sysdate, sysdate)";

        insert = conn.prepareStatement(update);

        for (String tag : tags) {

            insert.setString(1, tag);
            int rows = insert.executeUpdate();

        }

        insert.close();
    }

    /*
     * Update the last-used field for a tag.
     */
    public void update(List<String> tags, Connection conn) throws SQLException {
        PreparedStatement statement = null;

        String update = "update tag "
                + "set lastUsed = sysdate "
                + "where tagname = ?";

        statement = conn.prepareStatement(update);

        for (String tag : tags) {

            statement.setString(1, tag);
            int rows = statement.executeUpdate();

        }

        statement.close();
    }
}
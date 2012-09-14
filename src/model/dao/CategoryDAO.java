package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
 * Data access object for a category.
 */
public class CategoryDAO {

    /*
     * Update the last used field for a category tag.
     */
    public static void update(String[] categories, Connection conn) throws SQLException {
        
        PreparedStatement statement = null;

        String update = "update category "
                + "set lastUsed = sysdate "
                + "where categoryname = ?";

        statement = conn.prepareStatement(update);

        for (String cat : categories) {
            statement.setString(1, cat);
            int rows = statement.executeUpdate();
        }

        statement.close();
    }
}
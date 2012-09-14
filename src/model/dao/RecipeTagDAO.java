package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data access object for a recipe-tag relationship.
 */
public class RecipeTagDAO {

    /*
     * Insert a recipe-tag relationship into the database.
     */
    public static void insert(int recipeID, String tag, Connection conn) throws SQLException {
        
        // get tag ID
        int tagID = -1;
        PreparedStatement retrieve = null;
        
        String id = "select tagid "
                + "from tag "
                + "where tagname = ?";
        
        retrieve = conn.prepareStatement(id);
        retrieve.setString(1, tag);
        ResultSet rset = retrieve.executeQuery();
        while (rset.next()) {
            tagID = rset.getInt(1);
        }
        
        // insert the recipe-tag relationship
        PreparedStatement insert = null;

        String update = "insert into recipe_tag "
                + "values (?, ?)";

        insert = conn.prepareStatement(update);

        insert.setInt(1, recipeID);
        insert.setInt(2, tagID);
        int rows = insert.executeUpdate();

        insert.close();
    }
}
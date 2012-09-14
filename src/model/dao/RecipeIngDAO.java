package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data access object for a recipe-ingredient relationship.
 */
public class RecipeIngDAO {

    /*
     * Insert a recipe-ingredient relationship into the database.
     */
    public static void insert(int recipeID, String ing, Connection conn) throws SQLException {

        // get ingredient ID
        int ingID = -1;
        PreparedStatement retrieve = null;
        
        String id = "select ingid "
                + "from ingredient "
                + "where ingredientname = ?";
        
        retrieve = conn.prepareStatement(id);
        retrieve.setString(1, ing);
        ResultSet rset = retrieve.executeQuery();
        while (rset.next()) {
            ingID = rset.getInt(1);
        }

        // insert the recipe-ingredient relationship
        PreparedStatement insert = null;

        String update = "insert into recipe_ing "
                + "values (?, ?)";

        insert = conn.prepareStatement(update);
        insert.setInt(1, recipeID);
        insert.setInt(2, ingID);
        int rows = insert.executeUpdate();

        insert.close();
    }
}
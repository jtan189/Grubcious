package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data access object for a recipe-category relationship.
 */
public class RecipeCatDAO {
    
    /*
     * Insert a recipe-category relationship into the database.
     */
    public static void insert(int recipeID, String cat, Connection conn) throws SQLException {
        
        // get category ID
        int catID = -1;
        PreparedStatement retrieve = null;
        
        String id = "select catid "
                + "from category "
                + "where categoryname = ?";
        
        retrieve = conn.prepareStatement(id);
        retrieve.setString(1, cat);
        ResultSet rset = retrieve.executeQuery();
        
        while (rset.next()) {
            catID = rset.getInt(1);
        }

        // insert relationship into database
        PreparedStatement insert = null;         
        String update = "insert into recipe_cat "
                + "values (?, ?)";

        insert = conn.prepareStatement(update);

        insert.setInt(1, recipeID);
        insert.setInt(2, catID);
        int rows = insert.executeUpdate();

        insert.close();
    }
}

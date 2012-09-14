package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Data access object for an ingredient.
 */
public class IngredientDAO {

    private static IngredientDAO ingredientDAO;

    public static IngredientDAO getUserDAO() {
        if (ingredientDAO == null) {
            ingredientDAO = new IngredientDAO();
        }
        return ingredientDAO;
    }

    /*
     * Check if an ingredient already exits in the database.
     */
    public boolean exists(String ingredient, Connection conn) {

        PreparedStatement retrieveIng = null;
        boolean exists = false;
        ResultSet rset = null;

        // check database for matching ingredient name
        String query = "select * "
                + "from ingredient "
                + "where ingredientname = ?";
        try {

            retrieveIng = conn.prepareStatement(query);
            retrieveIng.setString(1, ingredient);

            //run the query, return a result set        
            rset = retrieveIng.executeQuery();

            // check if found an entry
            while (rset.next()) {
                exists = true;
            }

            //clean up database classes
            retrieveIng.close();
            rset.close();

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return exists;
    }

    /*
     * Insert an ingredient into the database.
     */
    public void insert(List<String> ingredients, Connection conn) throws SQLException {
        PreparedStatement insert = null;

        String update = "insert into ingredient "
                + "values (ing_seq.nextval, ? , sysdate, sysdate)";
        
        insert = conn.prepareStatement(update);

        for (String ing : ingredients) {
            insert.setString(1, ing);
            int rows = insert.executeUpdate();
        }

        insert.close();
    }
    
    /*
     * Update the last-used field for an ingredient.
     */
    public void update(List<String> ingredients, Connection conn) throws SQLException {
        PreparedStatement statement = null;

        String update = "update ingredient "
                + "set lastUsed = sysdate "
                + "where ingredientname = ?";
        
        statement = conn.prepareStatement(update);

        for (String ing : ingredients) {
            statement.setString(1, ing);
            int rows = statement.executeUpdate();
        }

        statement.close();
    }
}
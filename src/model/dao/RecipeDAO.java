package model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.bean.Recipe;

/*
 * Data access object for a recipe.
 */
public class RecipeDAO {

    private static RecipeDAO recipeDAO;

    public static RecipeDAO getRecipeDAO() {
        if (recipeDAO == null) {
            recipeDAO = new RecipeDAO();
        }
        return recipeDAO;
    }

    /*
     * Get the top ten recipes in the database.
     */
    public List<Recipe> getTopRecipes(Connection conn) throws ClassNotFoundException, SQLException {

        //create a query statement
        Statement stmt = conn.createStatement();

        // get top 10 recipes
        String query = "select * "
                + "from (select * "
                + "from recipe "
                + "order by rating desc) "
                + "where rownum <= 10";
        List<Recipe> result = new ArrayList<Recipe>();

        //run the query, return a result set        
        ResultSet rset = stmt.executeQuery(query);

        while (rset.next()) {
            Recipe recRow = new Recipe();
            // set values
            recRow.setRecipeID(rset.getInt("recipeID"));
            recRow.setTitle(rset.getString("title"));
            recRow.setDateAdded(rset.getDate("dateAdded"));
            recRow.setCost(rset.getDouble("cost"));
            recRow.setDescription(rset.getString("description"));
            recRow.setCreatorID(rset.getInt("creatorID"));
            recRow.setRating(rset.getInt("rating"));
            recRow.setServingSize(rset.getInt("servingSize"));
            recRow.setPrepTime(rset.getInt("prepTime"));

            result.add(recRow);
        }

        //clean up database classes
        stmt.close();
        rset.close();

        return result;
    }

    /*
     * Get the ten newest recipes in the database.
     */
    public List<Recipe> getNewestRecipes(Connection conn) throws ClassNotFoundException, SQLException {

        //create a query statement
        Statement stmt = conn.createStatement();

        // get 10 newest recipes
        String query = "select * "
                + "from (select * "
                + "from recipe "
                + "order by dateAdded desc) "
                + "where rownum <= 10"; //replace this with your query
        List<Recipe> result = new ArrayList<Recipe>();

        //run the query, return a result set        
        ResultSet rset = stmt.executeQuery(query);

        while (rset.next()) {
            Recipe recRow = new Recipe();
            // set values
            recRow.setRecipeID(rset.getInt("recipeID"));
            recRow.setTitle(rset.getString("title"));
            recRow.setDateAdded(rset.getDate("dateAdded"));
            recRow.setCost(rset.getDouble("cost"));
            recRow.setDescription(rset.getString("description"));
            recRow.setCreatorID(rset.getInt("creatorID"));
            recRow.setRating(rset.getInt("rating"));
            recRow.setServingSize(rset.getInt("servingSize"));
            recRow.setPrepTime(rset.getInt("prepTime"));

            result.add(recRow);

        }

        //clean up database classes
        stmt.close();
        rset.close();

        return result;
    }

    /*
     * Insert a recipe into the database.
     */
    public int insertRecipe(Connection conn, int creatorID, String title, double cost,
            String description, String rating, int servingSize, int prepTime)
            throws ClassNotFoundException, SQLException {

        // get next recipe ID
        int recipeID = -1;
        Statement st = conn.createStatement();

        ResultSet rs = st.executeQuery("select recipe_seq.nextval from dual");
        if (rs.next()) {
            recipeID = rs.getInt(1);
        }

        //create a query statement
        String update = "insert into recipe "
                + "values (?, ?, SYSDATE, ?, TO_CLOB(?), ?, ? , ?, ?)";

        PreparedStatement stmt = conn.prepareStatement(update);
        stmt.setInt(1, creatorID);
        stmt.setString(2, title);
        stmt.setDouble(3, cost); // need to set to BigDecimal?
        stmt.setString(4, description);
        stmt.setInt(5, recipeID);
        stmt.setString(6, rating);
        stmt.setInt(7, servingSize);
        stmt.setInt(8, prepTime);

        int rows = stmt.executeUpdate();
        stmt.close();

        return recipeID;

    }
}
package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.bean.Recipe;

/*
 * Search for recipes based on search parameters specified by user.
 */
public class SearchServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        // search parameters
        String searchString = request.getParameter("searchstring");
        searchString = "%" + searchString + "%";
        String searchIn = request.getParameter("searchin");
        String ingredients = request.getParameter("ingredients").toLowerCase();
        String tags = request.getParameter("tags").toLowerCase();
        String[] categories = request.getParameterValues("category");
        String orderBy = request.getParameter("orderby");
        String order = request.getParameter("order");
        String exIngredients = request.getParameter("exingredients").toLowerCase();
        String exTags = request.getParameter("extags").toLowerCase();

        String[] ingArray = ingredients.split(",");
        String[] tagArray = tags.split(",");
        String[] exIngArray = exIngredients.split(",");
        String[] exTagArray = exTags.split(",");

        //remove leading and trailing whitespace; allow only single spaces inside:
        //http://stackoverflow.com/questions/2932392/java-how-to-replace-2-or-more-spaces-with-single-space-in-string-and-delete-lead
        for (int i = 0; i < ingArray.length; i++) {
            ingArray[i] = ingArray[i].trim().replaceAll(" +", " ");
        }

        for (int i = 0; i < tagArray.length; i++) {
            tagArray[i] = tagArray[i].trim().replaceAll(" +", " ");
        }

        for (int i = 0; i < exIngArray.length; i++) {
            exIngArray[i] = exIngArray[i].trim().replaceAll(" +", " ");
        }

        for (int i = 0; i < exTagArray.length; i++) {
            exTagArray[i] = exTagArray[i].trim().replaceAll(" +", " ");
        }

        // build search query
        StringBuilder searchQuery = new StringBuilder();
        
        // root component
        searchQuery.append("select * from recipe where recipeid in ( ");

        // search string component
        searchQuery.append("(select recipeID from recipe where lower(").append(searchIn).append(")  like lower(?) ) ");

        // ingredient component
        if (ingArray != null) {
            if (!ingredients.equals("")) {
                searchQuery.append("intersect "
                        + "(select r.recipeID "
                        + "from recipe r, recipe_ing "
                        + "where r.recipeID = recipe_ing.recipeID "
                        + "and recipe_ing.ingID in "
                        + "( select ingID "
                        + "from ingredient "
                        + "where ingredientName = ? ");

                if (ingArray.length < 2) {
                    searchQuery.append(") ) ");
                } else {
                    for (int j = 1; j < ingArray.length; j++) {
                        searchQuery.append("or ingredientName = ? ");
                    }
                    searchQuery.append(") ) ");
                }
            }
        }

        //exclude ingredient component
        if (exIngArray != null) {
            if (!exIngredients.equals("")) {
                searchQuery.append("intersect "
                        + "(select recipeID "
                        + "from recipe "
                        + "where recipeID not in "
                        + "(select r.recipeID "
                        + "from recipe r, recipe_ing ri "
                        + "where r.recipeID = ri.recipeID "
                        + "and ri.ingID in "
                        + "(select ingID "
                        + "from ingredient "
                        + "where ingredientName = ? ");
                
                if (exIngArray.length < 2) {
                    searchQuery.append(") ) )");
                } else {
                    for (int j = 1; j < exIngArray.length; j++) {
                        searchQuery.append("or ingredientName = ? ");
                    }
                    searchQuery.append(") ) )");
                }
            }
        }

        // tag component
        if (tagArray != null) {
            if (!tags.equals("")) {
                searchQuery.append("intersect "
                        + "(select r.recipeID "
                        + "from recipe r, recipe_tag "
                        + "where r.recipeID = recipe_tag.recipeID "
                        + "and recipe_tag.tagID in "
                        + "( select tagID "
                        + "from tag "
                        + "where tagName = ? ");

                if (tagArray.length < 2) {
                    searchQuery.append(") ) ");
                } else {
                    for (int j = 1; j < tagArray.length; j++) {
                        searchQuery.append("or tagName = ? ");
                    }
                    searchQuery.append(") ) ");
                }
            }
        }
        
        // exclude tag component
        if (exTagArray != null) {
            if (!exTags.equals("")) {
                searchQuery.append("intersect "
                        + "(select recipeID "
                        + "from recipe "
                        + "where recipeID not in "
                        + "(select r.recipeID "
                        + "from recipe r, recipe_tag rt "
                        + "where r.recipeID = rt.recipeID "
                        + "and rt.tagID in "
                        + "(select tagID "
                        + "from tag "
                        + "where tagName = ? ");

                if (exTagArray.length < 2) {
                    searchQuery.append(") ) )");
                } else {
                    for (int j = 1; j < exTagArray.length; j++) {
                        searchQuery.append("or tagName = ? ");
                    }
                    searchQuery.append(") ) )");
                }
            }
        }

        // category component
        if (categories != null) {
            if (categories.length > 0) {
                searchQuery.append("intersect "
                        + "(select r.recipeID "
                        + "from recipe r, recipe_cat "
                        + "where r.recipeID = recipe_cat.recipeID "
                        + "and recipe_cat.catID in "
                        + "( select catID "
                        + "from category "
                        + "where categoryName = ? ");

                if (categories.length < 2) {
                    searchQuery.append(") ) ");
                } else {
                    for (int j = 1; j < categories.length; j++) {
                        searchQuery.append("or categoryName = ? ");
                    }
                    searchQuery.append(") ) ");
                }
            }
        }

        // order-by component
        searchQuery.append(") order by ").append(orderBy).append(" ").append(order).append(" ");

        // create DB connection
        Connection conn = null;

        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@asuka.cs.ndsu.nodak.edu:1521:asuka", "jotan", "<password>");

            // create PreparedStatement
            PreparedStatement searchStatement = conn.prepareStatement(searchQuery.toString());

            // set search string values
            searchStatement.setString(1, searchString);

            // set ingredient values
            int i = 2;
            if (!ingredients.equals("")) {
                for (String ing : ingArray) {
                    searchStatement.setString(i++, ing);
                }
            }

            // set exclude ingredient values
            if (!exIngredients.equals("")) {
                for (String ing : exIngArray) {
                    searchStatement.setString(i++, ing);
                }
            }

            // set tag values
            if (!tags.equals("")) {
                for (String tag : tagArray) {
                    searchStatement.setString(i++, tag);
                }
            }

            // set exclude tag values
            if (!exTags.equals("")) {
                for (String tag : exTagArray) {
                    searchStatement.setString(i++, tag);
                }
            }

            // set category values
            if (categories != null && categories.length > 0) {
                for (String cat : categories) {
                    searchStatement.setString(i++, cat);
                }
            }

            // search database
            List<Recipe> results = new ArrayList<Recipe>();    
            ResultSet rset = searchStatement.executeQuery();

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

                results.add(recRow);
            }

            request.setAttribute("results", results);

            // close connection 
            conn.close();

            request.setAttribute("search", "searchstring = " + searchQuery); // for debugging

        } catch (ClassNotFoundException ex) {
            request.setAttribute("search", "searchstring = " + searchQuery);
            Logger.getLogger(SearchServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            request.setAttribute("search", "searchstring = " + searchQuery);
            Logger.getLogger(AddRecipeServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            request.getRequestDispatcher("/WEB-INF/searchResults.jsp").forward(request, response);
        }

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        doPost(request, response);

    }
}
package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.bean.User;
import model.dao.*;

/*
 * Adds a recipe to the database according to the parameters set by the user on 
 * the "add recipe" page.
 */
public class AddRecipeServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String buttonPressed = request.getParameter("button");
        User userLoggedIn = (User) request.getSession().getAttribute("user");

        if (buttonPressed == null || buttonPressed.equals("Cancel")) {
            response.sendRedirect("/~jotan/servlet/grub");
        } else if (userLoggedIn == null) {
            // redirect users that are not logged in to login page
            response.sendRedirect("/~jotan/servlet/grub/login");
        } else {
            // add recipe
            int recipeID = -1;
            try {
                String title = request.getParameter("title");
                String[] category = request.getParameterValues("category");
                double cost = Double.parseDouble(request.getParameter("cost"));
                int size = Integer.parseInt(request.getParameter("size"));
                int prep = Integer.parseInt(request.getParameter("prep"));
                String rating = request.getParameter("rating");
                String description = request.getParameter("description");

                // all ingredients and tags should be cast to lowercase
                String ingredients = request.getParameter("ingredients").toLowerCase();
                String tags = request.getParameter("tags").toLowerCase();

                // create database connection
                Connection conn = null;
                try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    conn = DriverManager.getConnection("jdbc:oracle:thin:@asuka.cs.ndsu.nodak.edu:1521:asuka", "<username>", "<password>");
                } catch (SQLException ex) {
                    Logger.getLogger(AddRecipeServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AddRecipeServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

                // parse ingredients and tags into list:
                // http://stackoverflow.com/questions/6096845/adding-comma-separated-strings-to-an-arraylist-and-visa-versa
                List<String> ingList = Arrays.asList(ingredients.split(","));
                List<String> tagList = Arrays.asList(tags.split(","));

                //remove leading and trailing whitespace; allow only single spaces inside
                //http://stackoverflow.com/questions/2932392/java-how-to-replace-2-or-more-spaces-with-single-space-in-string-and-delete-lead
                for (int i = 0; i < ingList.size(); i++) {
                    ingList.set(i, ingList.get(i).trim().replaceAll(" +", " "));
                }
                for (int i = 0; i < tagList.size(); i++) {
                    tagList.set(i, tagList.get(i).trim().replaceAll(" +", " "));
                }

                // ingredients, tags that need to be added to DB
                List<String> ingToCreate = new ArrayList<String>();
                List<String> tagToCreate = new ArrayList<String>();

                // ingredients, tags, cats that need to have dateLastUsed updated
                List<String> ingToUpdate = new ArrayList<String>();
                List<String> tagToUpdate = new ArrayList<String>();

                // test if ingredients exist. if don't, add to list for creation
                IngredientDAO ingDAO = IngredientDAO.getUserDAO();
                for (String ing : ingList) {
                    if (ingDAO.exists(ing, conn)) {
                        ingToUpdate.add(ing);
                    } else {
                        ingToCreate.add(ing);
                    }
                }
                // test if tags exist. if don't, add to list for creation
                TagDAO tagDAO = TagDAO.getTagDAO();
                for (String tag : tagList) {
                    if (tagDAO.exists(tag, conn)) {
                        tagToUpdate.add(tag);
                    } else {
                        tagToCreate.add(tag);
                    }
                }

                // insert/update ingredients
                ingDAO.insert(ingToCreate, conn);
                ingDAO.update(ingToUpdate, conn);

                // insert/update tags
                tagDAO.insert(tagToCreate, conn);
                tagDAO.update(tagToUpdate, conn);

                // update categories
                if (category != null) {
                    CategoryDAO.update(category, conn);
                }

                // insert recipe into database
                RecipeDAO recipeDAO = RecipeDAO.getRecipeDAO();
                recipeID = recipeDAO.insertRecipe(conn, userLoggedIn.getUserID(), title, cost, description, rating, size, prep);

                // insert entry into recipe_cat
                if (category != null) {
                    for (String cat : category) {
                        RecipeCatDAO.insert(recipeID, cat, conn);
                    }
                }

                // insert entry into recipe_ing
                for (String ing : ingList) {
                    if (!ing.equals("")) {
                        RecipeIngDAO.insert(recipeID, ing, conn);
                    }
                }

                // insert entry into recipe_tag
                for (String tag : tagList) {
                    if (!tag.equals("")) {
                        RecipeTagDAO.insert(recipeID, tag, conn);
                    }
                }

                // close connection 
                response.sendRedirect("/~jotan/servlet/grub");

            } catch (ClassNotFoundException ex) {
                request.setAttribute("error", ex.getMessage());
                request.getRequestDispatcher("/WEB-INF/addRecipe.jsp").forward(request, response);
            } catch (SQLException ex) {
                request.setAttribute("error", ex.getMessage());
                request.getRequestDispatcher("/WEB-INF/addRecipe.jsp").forward(request, response);
            } catch (NumberFormatException ex) {
                request.setAttribute("error", "Invalid number submitted. Please correct and resubmit.");
                request.getRequestDispatcher("/WEB-INF/addRecipe.jsp").forward(request, response);
            }
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        
        doPost(request, response);
        
    }
}

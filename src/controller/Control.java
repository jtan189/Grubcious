package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.bean.Recipe;
import model.dao.RecipeDAO;

/**
 * Builds index and then forwards user to it.
 */
public class Control extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        Connection conn;
        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");

            //create a connection to the database
            conn = DriverManager.getConnection("jdbc:oracle:thin:@asuka.cs.ndsu.nodak.edu:1521:asuka", "<username>", "<password>");

            // retrieve recipes
            List<Recipe> topRated = RecipeDAO.getRecipeDAO().getTopRecipes(conn);
            List<Recipe> newest = RecipeDAO.getRecipeDAO().getNewestRecipes(conn);

            request.setAttribute("topRated", topRated);
            request.setAttribute("newest", newest);

            // clean up
            conn.close();

        } catch (Exception ex) {
            request.setAttribute("error", ex.getMessage());
        }

        request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        doPost(request, response);

    }
}

package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.bean.User;

/*
 * Checks if user is logged in and, if so, forwards user to "add recipe" page.
 * Otherwise, redirects user to login page.
 */
public class AddRecipe extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        User userLoggedIn = (User) request.getSession().getAttribute("user");
        if (userLoggedIn == null) {
            response.sendRedirect("/~jotan/servlet/grub/login");
        } else {
            request.getRequestDispatcher("/WEB-INF/addRecipe.jsp").forward(request, response);
        }
    }
    
}
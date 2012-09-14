<%@page isELIgnored="false" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Grubcious - Search for Recipes</title>
        <style type="text/css">
            <%@ include file="/search.css" %>
        </style>
    </head>
    <body>
        <div id="wrap">
            
            <div id="header">
                <img class="centered" src="/~jotan/grubcious.png" />
            </div>
            
            <div id="nav">
                <ul>
                    <li><a href="/~jotan/servlet/grub">Main Menu</a></li>
                    <li><a href="/~jotan/servlet/grub/login">Login/Register</a></li>
                    <li><a href="/~jotan/servlet/grub/addrecipe">Add Recipe</a></li>
                    <li><a href="/~jotan/servlet/grub/search">Search</a></li>
                </ul>
                <c:choose>
                    <c:when test="${user == null}">
                        <jsp:include page="/WEB-INF/login.jsp" />
                    </c:when>
                    <c:otherwise>
                        <a href="http://www.youtube.com/watch?v=oHg5SJYRHA0"><c:out value="${user.userName}" /></a>
                        <a href="/~jotan/servlet/grub/logoutservlet"> logout </a>
                    </c:otherwise>
                </c:choose>
            </div>
            
            <div id="center">
                <h3>Search:</h3>
                <form action="/~jotan/servlet/grub/searchresults" method="post">

                    Search string: <input type="text" name="searchstring" value="<c:out value="${param.searchstring}" />" />
                    in:
                    <input type="radio" name="searchin" value="title" checked /> Titles
                    <input type="radio" name="searchin" value="description" /> Description<br />

                    Ingredients (comma-separated): <input type="text" name="ingredients" value="<c:out value="${param.ingredients}" />" /><br />
                    Exclude Ingredients (comma-separated): <input type="text" name="exingredients" value="<c:out value="${param.exingredients}" />" /><br />                    
                    Tags (comma-separated): <input type="text" name="tags" value="<c:out value="${param.tags}" />" /><br />
                    Exclude Tags (comma-separated): <input type="text" name="extags" value="<c:out value="${param.extags}" />" /><br />
                    Category:<br />
                    <table>
                        <tr>
                            <td><input type="checkbox" name="category" value="appetizers" /> Appetizers & Snacks<br /></td>
                            <td><input type="checkbox" name="category" value="ethnic" /> Ethnic Dishes<br /></td>
                        </tr><tr>
                            <td><input type="checkbox" name="category" value="breakfast" /> Breakfast<br /></td>
                            <td><input type="checkbox" name="category" value="desserts" /> Desserts<br /></td>
                        </tr><tr>
                            <td><input type="checkbox" name="category" value="lunches" /> Lunches<br /></td>
                            <td><input type="checkbox" name="category" value="main" /> Main Dishes<br /></td>
                        </tr><tr>
                            <td><input type="checkbox" name="category" value="salad" /> Salad<br /></td>
                            <td><input type="checkbox" name="category" value="soups" /> Soups<br /></td>
                        </tr><tr>
                            <td><input type="checkbox" name="category" value="side" /> Side Dishes<br /></td>
                            <td><input type="checkbox" name="category" value="drinks" /> Drinks<br /></td>
                        </tr>
                    </table>
                    Order by:
                    <select name="orderby" size="1">
                        <option value="rating" selected>Rating</option>
                        <option value="cost">Cost</option>
                        <option value="dateAdded">Date Added</option>
                        <option value="prepTime">Prep Time</option>
                        <option value="upper(title)">Title</option>
                    </select>
                    <select name="order" size="1">
                        <option value="desc" selected>Descending</option>
                        <option value="asc" >Ascending</option>
                    </select>
                    <br />
                    <input type="submit" name="button" value="Search"/>
                    <c:if test="${error != null}">
                        <p>
                            <font color="red">${error}</font>
                        </p>
                    </c:if>  
                </form>
            </div>
                    
            <div id="footer">
                <p>Coming Soon: Contact Info</p>
            </div>
                    
        </div>
    </body>
</html>
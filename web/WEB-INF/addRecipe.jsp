<%@page isELIgnored="false" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Grubcious - Add Recipe</title>
        <style type="text/css">
            <%@ include file="/addRecipe.css" %>
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
                <h3>Add Recipe:</h3>

                <form action="/~jotan/servlet/grub/addrecipeservlet" method="post">

                    Title: <input type="text" name="title" value="<c:out value="${param.title}" />" /><br />

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

                    Cost (in dollars): <input type="text" name="cost" value="<c:out value="${param.cost}" />" />(e.g. "15.99" or "3.50") <br />
                    Serving Size: <input type="text" name="size" value="<c:out value="${param.size}" />" />(e.g. "1" or "10")<br />
                    Prep Time (in minutes): <input type="text" name="prep" value="<c:out value="${param.prep}" />" />(e.g. "15" or "60")<br />

                    Rating:
                    <select name="rating" size="1">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5" selected>5</option>
                        <option value="6">6</option>
                        <option value="7">7</option>
                        <option value="8">8</option>
                        <option value="9">9</option>
                        <option value="10">10</option>
                    </select>
                    <br />
                    
                    Ingredients (comma-separated): <input type="text" name="ingredients" value="<c:out value="${param.ingredients}" />" /><br />
                    Tags (comma-separated): <input type="text" name="tags" value="<c:out value="${param.tags}" />" /><br />

                    Description: <br />
                    <textarea name="description" cols="40" rows="5" ></textarea>
                    <br />
                    <input type="submit" name="button" value="Submit"/>
                    <input type="submit" name="button" value="Cancel"/>

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
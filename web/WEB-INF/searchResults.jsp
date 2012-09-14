<%@page isELIgnored="false" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Grubcious - Search Results</title>
        <style type="text/css">
            <%@ include file="/searchResults.css" %>
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
                <h2>Search Results</h2>
                <table id="table-5" class="centered">
                    <tr><th>Title</th><th>Date Added</th><th>Description</th><th>Rating</th><th>Cost</th><th>Serving Size</th><th>Prep Time</th></tr>
                    <c:forEach var="recipe" items="${results}">
                        <tr>
                            <td>${recipe.title}</td>
                            <td>${recipe.dateAdded}</td>
                            <td>${recipe.description}</td>
                            <td>${recipe.rating}</td>
                            <td>${recipe.cost}</td>
                            <td>${recipe.servingSize}</td>
                            <td>${recipe.prepTime}</td>
                        </tr>
                    </c:forEach>
                </table>
                <p align="center">
                    <a href="/~jotan/servlet/grub/search">Return to Search</a>
                </p>
                <%-- For debugging:
                Searched: ${search} <br />
                Error: ${error}
                --%>
            </div>
            
            <div id="footer">
                <p>Coming Soon: Contact Info</p>
            </div>
            
        </div>
    </body>
</html>
<%-- http://www.456bereastreet.com/lab/developing_with_web_standards/csslayout/2-col/ --%>

<%@page isELIgnored="false" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Grubcious - Main Menu</title>
        <style type="text/css">
            <%@ include file="/index.css" %>
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
            
            <div id="left">
                <h2>Newest Recipes</h2>
                <table id="table-5">
                    <tr><th>Title</th><th>Added</th><th>Description</th><th>Rating</th></tr>
                    <c:forEach var="recipe" items="${newest}">
                        <tr>
                            <td>${recipe.title}</td>
                            <td>${recipe.dateAdded}</td>
                            <td>${recipe.description}</td>
                            <td>${recipe.rating}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            
            <div id="right">
                <h2>Top Rated Recipes</h2>
                <table id="table-5">
                    <tr><th>Title</th><th>Added</th><th>Description</th><th>Rating</th></tr>
                    <c:forEach var="recipe" items="${topRated}">
                        <tr>
                            <td>${recipe.title}</td>
                            <td>${recipe.dateAdded}</td>
                            <td>${recipe.description}</td>
                            <td>${recipe.rating}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            
            <div id="footer">
                <p>Coming Soon: Contact Info</p>
            </div>
            
        </div>
    </body>
</html>


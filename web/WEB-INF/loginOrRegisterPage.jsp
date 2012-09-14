<%-- http://www.456bereastreet.com/lab/developing_with_web_standards/csslayout/2-col/ --%>
<%-- http://stackoverflow.com/questions/2333586/java-5-html-escaping-to-prevent-xss --%>

<%@page isELIgnored="false" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Grubcious - Login/Register</title>
        <style type="text/css">
            <%@ include file="/loginOrRegisterPage.css" %>
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
                <h3>Register:</h3>
                <form action="/~jotan/servlet/grub/registerservlet" method="post">

                    Username: <input type="text" name="username" value="<c:out value="${param.username}" />" /><br />
                    Password: <input type="password" name="password" value="<c:out value="${param.password}" />" /><br />
                    Verify: <input type="password" name="verify" value="<c:out value="${param.verify}" />" /><br />
                    Email: <input type="text" name="email" value="<c:out value="${param.email}" />" /><br />

                    <input type="submit" name="button" value="Register"/>
                    <c:if test="${errorRegister != null}">
                        <p>
                            <font color="red">${errorRegister}</font>
                        </p>
                    </c:if>  
                </form>
            </div>
                    
            <div id="right">
                <h3>Login:</h3>
                <form action="/~jotan/servlet/grub/loginservlet" method="post">
                    Username: <input type="text" name="username" value="<c:out value="${param.username}" />" /><br />
                    Password: <input type="password" name="password" value="<c:out value="${param.password}" />" /><br />
                    <input type="submit" />
                    <c:if test="${errorLogin != null}">
                        <p>
                            <font color="red">${errorLogin}</font>
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


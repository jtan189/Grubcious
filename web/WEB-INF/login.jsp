<%-- http://stackoverflow.com/questions/1945377/authenticating-the-username-password-by-using-filters-in-java-contacting-with --%>

<%@page isELIgnored="false" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form action="/~jotan/servlet/grub/loginservlet" method="post">
    username: <input type="text" name="username" value="<c:out value="${param.username}" />" />
    password: <input type="password" name="password" value="<c:out value="${param.password}" />" />
    <input type="submit" />
</form>
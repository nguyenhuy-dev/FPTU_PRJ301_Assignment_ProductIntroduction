
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--<c:if test="${countError>3}">
    <jsp:forward page="LoginError.html"></jsp:forward>
</c:if>--%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="Style/login.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="alert" style="background-color: #00CCDD">
            <strong style="font-size: 40px; color: white">Login</strong>
        </div>
        <form action="login" method="POST">
            <div class="container">
                <label for="account"><b>Account:</b></label>
                <input type="text" placeholder="Ex: manager" value="${account}" name="account" required>

                <label for="pass"><b>Password:</b></label>
                <input type="password" placeholder="Ex: 123" value="${pass}" name="pass" required>
                <c:if test="${errorLogin != null}">
                    <p style="font-size: 17px; color: red; font-weight: bold">${errorLogin} ${countError}/3</p>
                    <p style="font-size: 17px; color: red; font-weight: bold">${errorLogin1}</p>
                </c:if>
                <button type="submit">Sign In</button>
            </div>
        </form>
    </body>
</html>

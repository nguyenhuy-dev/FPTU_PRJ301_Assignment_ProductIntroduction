<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Introduction</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="${pageContext.request.contextPath}/Style/home.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@include file="header.jspf" %>
        <div class="container-fluid bg-1-container">
            <div class="text text-center">
                <h1 class="margin" style="font-size: 100px">404</h1>
                <h1 class="margin">NOT FOUND!</h1>
                <a href="${pageContext.request.contextPath}/home" class="btn btn-default btn-lg">Back to Home</a>
            </div>
        </div>
    </body>
</html>

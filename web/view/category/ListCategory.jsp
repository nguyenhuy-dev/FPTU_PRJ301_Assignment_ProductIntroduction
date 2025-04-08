<%-- 
    Document   : ListCategory
    Created on : Feb 25, 2025, 10:21:44 PM
    Author     : quoch
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List Categories</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1">
    </head>
    <body>
        <%@include file="../../header.jspf" %>
        <div class="alert" style="background-color: #00CCDD">
            <strong style="font-size: 40px; color: white">List Categories</strong>
        </div>
        <div class="container">   
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Type ID</th>
                        <th>Category Name</th>
                        <th>Memo</th>
                        <th>Tools</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="x" items="${listCategory}">
                        <tr>
                            <td>${x.typeId}</td>
                            <td>${x.categoryName}</td>
                            <td>${x.memo}</td>
                            <td>
                                <c:url var="urlUpdate" value="update?typeId=${x.typeId}"></c:url>
                                <a href="${urlUpdate}"><button type="button" class="btn btn-primary">Update</button></a>
                                <c:url var="urlDelete" value="delete?typeId=${x.typeId}"></c:url>
                                <a href="${urlDelete}"><button type="button" class="btn btn-danger">Delete</button></a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>

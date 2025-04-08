<%-- 
    Document   : ListAccount
    Created on : Feb 27, 2025, 4:04:35 PM
    Author     : quoch
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List Accounts</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1">
    </head>
    <body>
        <%@include file="../../header.jspf" %>
        <div class="alert" style="background-color: #00CCDD">
            <strong style="font-size: 40px; color: white">List Accounts</strong>
        </div>
        <div class="container">   
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Account</th>
                        <th>Full Name</th>
                        <th>Birthday</th>
                        <th>Gender</th>
                        <th>Phone</th>
                        <th>Role In System</th>
                        <th>Tools</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="x" items="${listAccount}">
                        <tr>
                            <td>${x.account}</td>
                            <td>${x.firstName}, ${x.lastName}</td>
                            <td>${x.birthday}</td>
                            <td>${x.gender?"Male":"Female"}</td>
                            <td>${x.phone}</td>
                            <td>${x.roleInSystem==1?"Administrator":"Staff"}</td>
                            <td>
                                <c:url var="urlUpdate" value="update?account=${x.account}"></c:url>
                                <a href="${urlUpdate}"><button type="button" class="btn btn-primary">Update</button></a>
                                <c:url var="urlActive" value="status?account=${x.account}"></c:url>
                                <a href="${urlActive}"><button type="button" class="btn btn-success">${x.isUse?"Active":"Deactive"}</button></a>
                                <c:url var="urlDelete" value="delete?account=${x.account}"></c:url>
                                <a href="${urlDelete}"><button type="button" class="btn btn-danger">Delete</button></a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List Products</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1">
    </head>
    <body>
        <%@include file="../../header.jspf" %>
        <div class="alert alert-info">
            <strong style="font-size: 40px">List Products</strong>
        </div>
        <div class="container">   
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Product ID</th>
                        <th>Product Name</th>
                        <th>Product Image</th>
                        <th>Brief</th>
                        <th>Posted Date</th>
                        <th>Category Name</th>
                        <th>Account</th>
                        <th>Unit</th>
                        <th>Price</th>
                        <th>Discount</th>
                        <th>Tools</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="x" items="${listProduct}">
                        <tr>
                            <td>${x.productId}</td>
                            <td>${x.productName}</td>
                            <td><img src="${pageContext.request.contextPath}${x.productImage}" alt="Error Image" width="100"></td>
                            <td>${x.brief}</td>
                            <td>${x.postedDate}</td>
                            <td>${x.type.categoryName}</td>
                            <td>${x.account.account}</td>
                            <td>${x.unit}</td>
                            <td>${x.price}</td>
                            <td>${x.discount}</td>
                            <td>
                                <c:url var="urlUpdate" value="update?productId=${x.productId}"></c:url>
                                <a href="${urlUpdate}"><button type="button" class="btn btn-primary">Update</button></a>
                                <c:url var="urlDelete" value="delete?productId=${x.productId}"></c:url>
                                <a href="${urlDelete}"><button type="button" class="btn btn-danger">Delete</button></a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>

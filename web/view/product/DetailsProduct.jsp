
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${requestScope.pro.productName}</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <style>
            .details-text {
                margin-bottom: 10px;
            }
            .details-img img {
                border-radius: 10px;
                height: 400px;
                max-width: 400px;
                object-fit: contain;
            }
        </style>
    </head>
    <body>
        <%@include file="../../header.jspf" %>
        <div class="container" style="margin-top: 50px">
            <a href="javascript:history.back()" style="color: #00CCDD; font-size: 20px">Back</a>
            <div class="row">
                <div class="col-md-6">
                    <div class="text-center details-img"><img src="${pageContext.request.contextPath}${requestScope.pro.productImage}" alt="Error Image"></div>
                </div>
                <div class="col-md-6" style="border-left: 1px solid #ccc">
                    <h2 style="margin: 0; font-weight: 600">${requestScope.pro.productName}</h2>
                    <p style="margin-top: 5px; color: gray">ID: ${requestScope.pro.productId}</p>
                    <p style="font-size: 40px; font-weight: bold; margin: 0">${(requestScope.pro.price * (100 - requestScope.pro.discount) / 100).intValue()} <span style="text-decoration: underline">đ</span></p>
                    <p style="color: grey; font-size: 15px">
                        <span style="text-decoration: line-through">
                            <span>${requestScope.pro.price} </span><span style="text-decoration: underline">đ</span>
                        </span>&nbsp;
                        <span style="color: red; font-size: 17px; font-weight: 700">-${requestScope.pro.discount}%</span>
                    </p>
                    <div class="details-text">
                        <p style="font-size: 17px; margin: 0"><span style="font-weight: bold">Category Name:</span> ${requestScope.pro.type.categoryName}</p>
                        <hr style="margin: 0">
                    </div>
                    <div class="details-text">
                        <p style="font-size: 17px; margin: 0"><span style="font-weight: bold">Posted Day:</span> ${requestScope.pro.postedDate}</p>
                        <hr style="margin: 0">
                    </div>
                    <div class="details-text">
                        <p style="font-size: 17px; margin: 0"><span style="font-weight: bold">Unit:</span> ${requestScope.pro.unit}</p>
                        <hr style="margin: 0">
                    </div>
                    <div class="details-text">
                        <p style="font-size: 17px; margin: 0"><span style="font-weight: bold">Account:</span> ${requestScope.pro.account.account}</p>
                        <hr style="margin: 0">
                    </div>
                    <div class="details-text">
                        <p style="font-size: 17px; margin: 0"><span style="font-weight: bold">Brief:</span> <span style="font-size: 14px">${requestScope.pro.brief}</span> </p>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>

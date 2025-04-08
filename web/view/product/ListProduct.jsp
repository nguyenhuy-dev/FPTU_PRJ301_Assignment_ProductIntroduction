<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${sessionScope.report}</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        
        <style>
            .details {
                box-shadow: 4px 4px 10px rgba(0, 0, 0, 0.2);
                transition: 0.3s ease;
                border-radius: 10px;
                height: 345px;
                width: 280px;
                margin-right: 10px;
                margin-bottom: 10px;
                position: relative;
            }
            .details:hover {
                transform: scale(1.05);
            }
            a:hover,
            a:focus,
            a:active {
                text-decoration: none;
            }
            
            .details-button {
                display: none;
                position: absolute;
                transition: 0.3s ease;
                top: 10px;
                right: 10px;
            }
            .details-button button {
                width: 72px;
                margin-bottom: 3px;
                background-color: #00CCDD
            }
            .details:hover .details-button {
                display: block;
            }
            .details-button .btn:hover {
                background: #0099AA
            }
            
            .details .details-report {
                position: absolute;
                top: 10px;
                left: 10px;
                background: #00CCDD;
                color: white;
                padding: 5px;
                border-radius: 5px;
            }
            
            .details-img {
                margin: 5px;
                border-radius: 10px;
                height: 200px;
            }
            .details-img img {
                height: 200px; 
                max-width: 220px;
                object-fit: contain;
                border-radius: 5px;
            }
            
            .filter {
                height: 50px;
                background-color: #00CCDD;
                margin-bottom: 10px;
                border-radius: 10px;
                display: flex;
                align-items: center;
            }
   
            .filter * {
                margin: 0;
                margin-left: 15px;
            }
            
            .filter-price {
                margin: 0;
            }
            .filter select {
                border: none;
                border-radius: 5px;
                padding: 5px;
            }
            
            .filter-form {
                display: flex; 
                align-items: center; 
                margin-left: 300px
            }
            .filter-form input {
                border: none;
                border-radius: 5px;
                padding: 5px;
            }
            .filter-form button {
                border: none;
                border-radius: 5px;
                padding: 5px;
                background-color: white;
            }
            .filter-form button:hover {
                background-color: #f0f0f0;
            }
        </style>
    </head>
    <body>
        <%@include file="../../header.jspf" %>
        <div class="alert" style="background-color: #00CCDD">
            <strong style="font-size: 40px; color: white">${sessionScope.report}</strong>
        </div>
        <div class="container">
            <div class="row">
                <div class="filter">
                    <p style="color: white; font-size: 18px">Sort by</p>
                    <form class="filter-price" action="sort" method="GET">
                        <select name="price" onchange="this.form.submit()">
                            <option disabled ${requestScope.price == null ? "selected" : ""} hidden>Giá</option>
                            <option value="asc" ${requestScope.price eq "asc" ? "selected" : ""}>Giá: Tăng dần</option>
                            <option value="desc" ${requestScope.price eq "desc" ? "selected" : ""}>Giá: Giảm dần</option>
                        </select>
                    </form>
                    <form class="filter-price" action="sort" method="GET">
                        <select name="sale" onchange="this.form.submit()">
                            <option disabled ${requestScope.sale == null ? "selected" : ""} hidden>Giảm giá</option>
                            <option value="yes" ${requestScope.sale eq "yes" ? "selected" : ""}>Giảm giá: Có</option>
                            <option value="no" ${requestScope.sale eq "no" ? "selected" : ""}>Giảm giá: Không</option>
                        </select>
                    </form>
                    <form class="filter-form" action="sort" method="GET">
                        <input type="number" placeholder="Từ: đ" name="from" value="${requestScope.from}"/> 
                        <div style="background-color: white; width: 15px; height: 3px; border-radius: 2px"></div>
                        <input type="number" placeholder="Đến: đ" name="to" value="${requestScope.to}"/>
                        <button type="submit">Áp dụng</button>
                    </form>
                </div>
            </div>
            <div class="row">
                <c:forEach var="x" items="${sessionScope.listProduct}">
                    <div class="col-md-3 details">
                        <c:if test="${sessionScope.acc != null}">
                            <div class="details-button">
                                <a href="${pageContext.request.contextPath}/product/delete?productId=${x.productId}"><button class="btn" style="color: white">Delete</button></a> <br/>
                                <a href="${pageContext.request.contextPath}/product/update?productId=${x.productId}"><button class="btn" style="color: white">Update</button></a>
                            </div>
                        </c:if>
                        <c:forEach var="ck" items="${cookie}">
                            <c:if test="${ck.key eq 'viewed'}">
                                <c:set var="viewedIds" value="${fn:split(ck.value.value, '@')}"/>
                                <c:forEach var="id" items="${viewedIds}">
                                    <c:if test="${x.productId eq id}">
                                        <div class="details-report">Đã xem</div>
                                    </c:if>
                                </c:forEach>
                            </c:if>
                        </c:forEach>
                        <a href="${pageContext.request.contextPath}/product/details?productId=${x.productId}">
                            <div class="text-center details-img"><img src="${pageContext.request.contextPath}${x.productImage}" alt="Error Image"></div>
                            <div class="details-price-old">
                                <p style="color: grey; font-size: 15px">
                                    <span style="text-decoration: line-through">
                                        <span>${x.price} </span><span style="text-decoration: underline">đ</span>
                                    </span>&nbsp;
                                    <span style="color: red; font-size: 17px; font-weight: 700">-${x.discount}%</span>
                                </p>
                                <p style="color: green; font-size: 25px; font-weight: 700">${(x.price * (100 - x.discount) / 100).intValue()} <span style="text-decoration: underline">đ</span></p>
                                <p style="color: black; font-size: 15px">${x.productName}</p>
                            </div>
                        </a>
                    </div>
                </c:forEach>
            </div>
        </div>
    </body>
</html>

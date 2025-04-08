<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Product</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1">
    </head>
    <body>
        <%@include file="../../header.jspf" %>
        <div class="alert" style="background-color: #00CCDD">
            <strong style="font-size: 40px; color: white">Update Product</strong>
        </div>
        <div class="container">
            <form class="form-horizontal" action="update" method="POST">
                <div class="form-group">
                    <label class="control-label col-sm-2" for="productId">Product ID:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" readonly placeholder="Ex: 0947979716" value="${pro.productId}" name="productId">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="productName">Product Name:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" placeholder="Ex: Điện thoại iPhone 14 256GB" value="${pro.productName}" name="productName">
                    </div>
                </div> 
                <div class="form-group">
                    <label class="control-label col-sm-2" for="productImage">Product Image:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" placeholder="Ex: " value="${pro.productImage}" name="productImage">
                    </div>
                </div> 
                <div class="form-group">
                    <label class="control-label col-sm-2" for="brief">Brief:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" placeholder="Ex: Smartphone có tính năng faceID" value="${pro.brief}" name="brief">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="postedDate">Posted Date:</label>
                    <div class="col-sm-10">
                        <input type="date" class="form-control" value="${pro.postedDate}" name="postedDate">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="typeId">Category Name:</label>       
                    <div class="col-sm-10">
                        <select class="form-control" name="typeId">
                            <option value="-1">-- Select a type --</option>
                            <c:forEach var="x" items="${listCategory}">
                                <option value="${x.typeId}" ${x.typeId==pro.type.typeId?"selected":""}>${x.categoryName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="account">Account:</label>       
                    <div class="col-sm-10">
                        <select class="form-control" name="account">
                            <option value="-1">-- Select a account --</option>
                            <c:forEach var="x" items="${listAccount}">
                                <option style="background-color: ${x.isUse?"#dff0d8":"#f2dede"}" value="${x.account}" ${x.account==pro.account.account?"selected":""}>${x.account}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="unit">Unit:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" placeholder="Ex: Cái" value="${pro.unit}" name="unit">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="price">Price:</label>
                    <div class="col-sm-10">
                        <input type="number" class="form-control" placeholder="Ex: VND" value="${pro.price}" name="price">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="discount">Discount:</label>
                    <div class="col-sm-10">
                        <input type="number" class="form-control" placeholder="Ex: %" value="${pro.discount}" name="discount">
                    </div>
                </div>
                <div class="form-group">        
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">Save</button>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>

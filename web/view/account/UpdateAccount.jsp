
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Account</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </head>
    <body>
        <%@include file="../../header.jspf" %>
        <div class="alert" style="background-color: #00CCDD">
            <strong style="font-size: 40px; color: white">Update Account</strong>
        </div>
        <div class="container">
            <form class="form-horizontal" action="update" method="POST">
                <div class="form-group">
                    <label class="control-label col-sm-2" for="account">Account:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" value="${requestScope.acc.account}" name="account" readonly="">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="pass">Password:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" placeholder="Ex: Mem123@" value="${requestScope.acc.pass}" name="pass">
                    </div>
                </div>
               <div class="form-group">
                    <label class="control-label col-sm-2" for="firstName">First Name:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" placeholder="Ex: Như" value="${acc.firstName}" name="firstName">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="lastName">Last Name:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" placeholder="Ex: Hồ Quỳnh" value="${requestScope.acc.lastName}" name="lastName">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="birthday">Birthday:</label>
                    <div class="col-sm-10">
                        <input type="date" class="form-control" value="${requestScope.acc.birthday}" name="birthday">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="gender">Gender:</label>
                    <div class="col-sm-10">
                        <div class="radio">
                            <label style="margin-right: 15px"><input type="radio" name="gender" value="male" ${requestScope.acc.gender?"checked":""}> Male</label>
                            <label><input type="radio" name="gender" value="female" ${!requestScope.acc.gender?"checked":""}> Female</label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="phone">Phone:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" placeholder="Ex: 0947979716" value="${requestScope.acc.phone}" name="phone">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <div class="checkbox">
                            <label><input type="checkbox" name="isUse" ${requestScope.acc.isUse?"checked":""}> Is Active</label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="roleInSystem">Role In System:</label>
                    <div class="col-sm-10">
                        <select class="form-control" name="roleInSystem">
                            <option value="100" ${requestScope.acc.roleInSystem==100?"selected":""}>-- Select a role --</option>
                            <option value="1" ${requestScope.acc.roleInSystem==1?"selected":""}>Administrator</option>
                            <option value="2" ${requestScope.acc.roleInSystem==2?"selected":""}>Staff</option>
                        </select>
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

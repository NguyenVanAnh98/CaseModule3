<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Management Application</title>
    <!-- Thêm các tệp CSS của Bootstrap -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <center>
        <h1>User Management</h1>
        <h2>
            <a href="customer?action=create" class="btn btn-primary">Add New Customer</a>
        </h2>
    </center>
    <div class="row justify-content-center">
        <form method="post" class="col-lg-6 col-md-8 col-sm-10">
            <table class="table table-bordered">
                <caption>
                    <h2>Add New User</h2>
                </caption>
                <tr>
                    <th>User Name:</th>
                    <td>
                        <input type="text" name="name" value="${customer.name}" class="form-control" id="name" size="45"/>
                    </td>
                </tr>
                <tr>
                    <th>User Email:</th>
                    <td>
                        <input type="text" value="${customer.email}" name="email" class="form-control" id="email" size="45"/>
                    </td>
                </tr>
                <tr>
                    <th>Address:</th>
                    <td>
                        <input type="text" value="${customer.address}" name="address" class="form-control" id="address" size="15"/>
                    </td>
                </tr>
                <tr>
                    <th>Phone:</th>
                    <td>
                        <input type="text" name="phone" value="${customer.phone}" class="form-control" id="phone" size="15"/>
                    </td>
                </tr>
                <tr>
                    <th>Province:</th>
                    <td>
                        <select name="province" class="form-control">
                            <c:forEach items="${provinces}" var="p">
                                <option value="${p.id}" ${ customer.province.id == p.id ? "selected" : ""}>${p.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Save" class="btn btn-primary"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>

<!-- Thêm các tệp JavaScript của Bootstrap -->
<%--<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>--%>
<%--<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>--%>
<%--<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>--%>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Management Application</title>
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
    <div align="center">
        <form method="post">
            <table class="table table-bordered">
                <caption>
                    <h2>Add New User</h2>
                </caption>
                <tr>
                    <th>User Name:</th>
                    <td>
                        <input type="text" name="name" value="${customer.name}" id="name" class="form-control" size="45"/>
                    </td>
                </tr>
                <tr>
                    <th>User Email:</th>
                    <td>
                        <input type="text" value="${customer.email}" name="email" id="email" class="form-control" size="45"/>
                    </td>
                </tr>
                <tr>
                    <th>Address:</th>
                    <td>
                        <input type="text" value="${customer.address}" name="address" id="address" class="form-control" size="15"/>
                    </td>
                </tr>
                <tr>
                    <th>Phone:</th>
                    <td>
                        <input type="text" name="phone" value="${customer.phone}" id="phone" class="form-control" size="15"/>
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
</body>
</html>
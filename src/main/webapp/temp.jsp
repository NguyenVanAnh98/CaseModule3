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
            <a href="/customer?action=create" class="btn btn-primary">Add New Customer</a>
        </h2>
    </center>
    <div class="table-responsive">
        <table class="table table-bordered table-striped">
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Address</th>
                <th>Phone</th>
                <th>Province</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="c" items="${listCustomers}">
                <tr>
                    <td><c:out value="${c.id}"/></td>
                    <td><c:out value="${c.name}"/></td>
                    <td><c:out value="${c.email}"/></td>
                    <td><c:out value="${c.address}"/></td>
                    <td><c:out value="${c.phone}"/></td>
                    <td><c:out value="${c.province.name}"/></td>
                    <td>
                        <a href="/customer?action=edit&id=${c.id}" class="btn btn-primary">Edit</a>
                        <a onclick="return confirm('Are you sure you want to delete?')" href="/customer?action=delete&id=${c.id}" class="btn btn-danger">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<!-- Thêm các tệp JavaScript của Bootstrap -->
<%--<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>--%>
<%--<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>--%>
<%--<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>--%>
</body>
</html>
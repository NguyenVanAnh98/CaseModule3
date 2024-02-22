<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.3/css/bootstrap.min.css"
          integrity="sha512-jnSuA4Ss2PkkikSOLtYs8BlYIeeIK1h99ty4YfvRPAlzr377vr3CXDb7sb7eEEBYjDtcYj+AjBH3FLv5uSJuXg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
<div class="container">
    <div class="mt-5">
        <ul class="nav nav-underline">
            <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="#">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/customer">Customer</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/province">Province</a>
            </li>
        </ul>
    </div>
    <div class="mt-4 mb-4 d-flex justify-content-between">
        <a href="/customer?action=create"><button  class="btn btn-primary">Create</button></a>
        <form method="get" action="/customer">
            <input type="text" name="kw" class="text-field" placeholder="Search">
            <button class="btn btn-primary">Search</button>
        </form>
    </div>
    <div class="content">
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
</div>
</body>
</html>
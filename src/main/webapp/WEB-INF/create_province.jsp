<%--
  Created by IntelliJ IDEA.
  User: AnhKun
  Date: 2/21/2024
  Time: 2:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Create Province</title>
    <!-- Thêm các tệp CSS của Bootstrap -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .container {
            max-width: 500px;
            margin: 0 auto;
            margin-top: 50px;
        }

        .form-group label {
            font-weight: bold;
        }

        .btn-primary {
            margin-top: 20px;
        }
    </style>
</head>
<body>
<div class="container">
    <center>
        <h1>Create Province</h1>
    </center>
    <div align="center">
        <form action="/province?action=create" method="POST">
            <div class="form-group">
                <label for="name">Name:</label>
                <input type="text" name="name" required class="form-control">
            </div>
            <br>
            <input type="submit" value="Create" class="btn btn-primary">
        </form>
    </div>
</div>

<!-- Thêm các tệp JavaScript của Bootstrap -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
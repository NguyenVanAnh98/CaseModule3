<%--
  Created by IntelliJ IDEA.
  User: AnhKun
  Date: 2/21/2024
  Time: 2:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Province</title>
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
    <h1>Edit Province</h1>
    <form action="province?action=edit" method="POST">
        <input type="hidden" name="id" value="${province.id}">
        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" name="name" value="${province.name}" class="form-control">
        </div>
        <br>
        <input type="submit" value="Update" class="btn btn-primary">
    </form>
</div>

<%--<!-- Thêm các tệp JavaScript của Bootstrap -->--%>
<%--<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>--%>
<%--<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>--%>
<%--<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>--%>
</body>
</html>
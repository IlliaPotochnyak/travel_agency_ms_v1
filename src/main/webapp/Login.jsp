<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
    isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <!-- Latest compiled and minified CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">

        <!-- Latest compiled JavaScript -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    </head>
        <body>
            <div class="container">
                <h2>Login page</h2>
            </div>
            <div class="container">
                ${errorLogin}
            </div>
            <hr>
            <div class="container mt-5">
                <form action="LoginValidator" method="post">
                    <div class="mb-3">
                        <label class="form-label">Email : </label>
                        <input type="email" class="form-control" placeholder="Enter Email" name="email" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Password : </label>
                        <input type="password" class="form-control" placeholder="Enter Password" name="password" required>
                        <button type="submit" class="btn btn-primary">Login</button>
                    </div>

                </form>
            </div>
            <hr>

        </body>
</html>

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
            <form action="LoginValidator" method="post">
                    <div class="container">
                        <label>Email : </label>
                        <input type="email" placeholder="Enter Email" name="email" required>
                        <label>Password : </label>
                        <input type="password" placeholder="Enter Password" name="password" required>
                        <button type="submit">Login</button>

                    </div>
                </form>

            <hr>

        </body>
</html>

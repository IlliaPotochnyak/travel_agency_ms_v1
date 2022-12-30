<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
    isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <body>
        <h2>Login page</h2>
        ${errorLogin}
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

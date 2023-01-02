<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
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
            <h2>Hello, this is a TravelAgency MS!!!</h2>
        </div>
        <div class="container">
            ${UserFirstName} ${UserLastName}
        </div>
        <div class="container">
            <c:if test="${empty UserFirstName}">
                <a href="Register.jsp">Register</a>
                <a href="Login.jsp">Login</a>
            </c:if>
        <c:if test="${not empty UserFirstName}">
                <a href="Logout">Logout</a>
        </c:if>
        </div>

        <hr>


        <hr>

        <jsp:include page="TourList" flush="true"/>

    </body>
</html>

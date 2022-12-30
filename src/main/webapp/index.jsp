<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
    isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <body>
        <h2>Hello, this is a TravelAgency MS!!!</h2>
        ${UserFirstName} ${UserLastName}
        <c:if test="${empty UserFirstName}">
            <a href="Register.jsp">Register</a>
            <a href="Login.jsp">Login</a>
        </c:if>
        <c:if test="${not empty UserFirstName}">
                <a href="Logout">Logout</a>
        </c:if>

        <hr>


        <hr>

        <jsp:include page="TourList" flush="true"/>

    </body>
</html>

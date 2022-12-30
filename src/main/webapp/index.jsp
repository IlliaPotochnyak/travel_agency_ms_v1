<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
    isELIgnored = "false" %>

<html>
    <body>
        <h2>Hello, this is a TravelAgency MS!!!</h2>
        ${UserFirstName} ${UserLastName}
        <a href="Register.jsp">Register</a>
        <a href="Login.jsp">Login</a>
        <a href="Logout">Logout</a>
        <hr>

        <hr>

        <jsp:include page="HelloServlet?name=ILYA" flush="true" />
        <hr>

        <jsp:include page="TourList" flush="true"/>

    </body>
</html>

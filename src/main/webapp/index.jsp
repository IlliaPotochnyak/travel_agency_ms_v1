<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
    isELIgnored = "false" %>

<html>
    <body>
        <h2>Hello, this is a TravelAgency MS!!!</h2>
        <a href="roles">roles</a>
        <hr>
        ${pageContext}
        <hr>

        <jsp:include page="HelloServlet?name=ILYA" flush="true" />
        <hr>

        <jsp:include page="TourList" flush="true"/>

    </body>
</html>

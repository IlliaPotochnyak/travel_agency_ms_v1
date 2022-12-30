<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <body>
        <h2>Hello, this is a TravelAgency MS!!!</h2>
        <a href="roles">roles</a>
        <hr>
        <jsp:include page="HelloServlet?name=ILYA" flush="true" />
        <hr>
        <jsp:include page="TourList" flush="true" />



    </body>
</html>

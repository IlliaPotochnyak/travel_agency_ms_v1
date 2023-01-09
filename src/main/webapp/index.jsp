<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
    isELIgnored = "false" session = "true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <!-- Latest compiled and minified CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">

        <!-- Latest compiled JavaScript -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>

    </head>
    <body>
        <jsp:include page="WEB-INF/view/Header.jsp" flush="true"/>

        <div class="container">
            <h2>Hello, this is a TravelAgency MS!!!</h2>
        </div>




        <jsp:include page="TourList" flush="true"/>


        <%--For displaying Previous link except for the 1st page --%>
            <c:if test="${currentPage != 1}">
                <td><a href="index.jsp?page=${currentPage - 1}&tour_type=${param.tour_type}&price=${param.price}&people_amount=${param.people_amount}&hotel_stars=${param.hotel_stars}">Previous</a></td>
            </c:if>

            <%--For displaying Page numbers.
            The when condition does not display a link for the current page--%>
            <table border="1" cellpadding="5" cellspacing="5">
                <tr>
                    <c:forEach begin="1" end="${noOfPages}" var="i">
                        <c:choose>
                            <c:when test="${currentPage eq i}">
                                <td>${i}</td>
                            </c:when>
                            <c:otherwise>
                                <td><a href="index.jsp?page=${i}&tour_type=${param.tour_type}&price=${param.price}&people_amount=${param.people_amount}&hotel_stars=${param.hotel_stars}">${i}</a></td>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </tr>
            </table>

            <%--For displaying Next link --%>
            <c:if test="${currentPage lt noOfPages}">
                <td><a href="index.jsp?page=${currentPage + 1}&tour_type=${param.tour_type}&price=${param.price}&people_amount=${param.people_amount}&hotel_stars=${param.hotel_stars}">Next</a></td>
            </c:if>



    </body>
</html>

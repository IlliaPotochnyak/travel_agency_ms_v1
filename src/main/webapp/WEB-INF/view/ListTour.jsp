<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
    isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="languages/messages"/>
<html lang="${sessionScope.lang}">

<html>
    <head>
    <meta charset="UTF-8">
    <!-- Latest compiled and minified CSS -->
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Latest compiled JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <style>
            #table-desc {
                         max-width: 400px;
                         overflow: hidden;
                         text-overflow: ellipsis;
                         white-space: nowrap;
                        }
            #table-name {
                         max-width: 200px;
                         overflow: hidden;
                         text-overflow: ellipsis;
                         white-space: nowrap;
                        }
        </style>

    </head>
    <body>

    <jsp:include page="Header.jsp" flush="true"/>

     <hr>
        <div class="container">
            <h2><fmt:message key="listTour.header" /></h2>

        </div>


        <jsp:include page="SortTour.jsp" flush="true"/>



        <div class="container">
            <table class="table">
                <tbody>

                    <thead>
                        <td></td>
                        <td><fmt:message key="listTour.tableTour" /></td>
                        <td><fmt:message key="listTour.tableDescription" /></td>
                        <td><fmt:message key="listTour.tablePrice" /></td>
                        <td><fmt:message key="listTour.tablePersonsNumber" /></td>
                        <td><fmt:message key="listTour.tableType" /></td>
                        <td><fmt:message key="listTour.tableHotelStarRate" /></td>
                    </thead>
                    <c:forEach var="tour" items="${tourList}">
                        <tr>
                            <td>
                                <c:if test="${tour.hot eq 1}">
                                    <span class="badge bg-danger">
                                        <fmt:message key="listTour.hot" />
                                    </span>
                                </c:if>
                            </td>

                                <td id="table-name"><a href="TourPageServlet?tourId=${tour.id}" ><c:out value="${tour.name}"/></td>
                                <td id="table-desc"><c:out value="${tour.description}"/></a></td>
                                <td><c:out value="${tour.price}"/></td>
                                <td><c:out value="${tour.personsNumber}"/></td>
                                <td><c:out value="${tour.tourType}"/></td>
                                <td><c:out value="${tour.hotelType}"/></td>

                        </tr>
                    </c:forEach>
                </tbody>

            </table>
        <div>


        <%--For displaying Previous link except for the 1st page --%>
                    <c:if test="${currentPage != 1}">
                        <td><a href="controller?command=list_tour&page=${currentPage - 1}&tour_type=${param.tour_type}&price=${param.price}&people_amount=${param.people_amount}&hotel_stars=${param.hotel_stars}"><fmt:message key="main.mainPrevious" /></a></td>
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
                                        <td><a href="controller?command=list_tour&page=${i}&tour_type=${param.tour_type}&price=${param.price}&people_amount=${param.people_amount}&hotel_stars=${param.hotel_stars}">${i}</a></td>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </tr>
                    </table>

                    <%--For displaying Next link --%>
                    <c:if test="${currentPage lt noOfPages}">
                        <td><a href="controller?command=list_tour&page=${currentPage + 1}&tour_type=${param.tour_type}&price=${param.price}&people_amount=${param.people_amount}&hotel_stars=${param.hotel_stars}"><fmt:message key="main.mainNext" /></a></td>
                    </c:if>


        <hr>

    </body>
</html>

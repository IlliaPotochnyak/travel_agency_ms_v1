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
     <hr>
        <div class="container">
            <h2><fmt:message key="listTour.header" /></h2>

        </div>


        <jsp:include page="SortTour.jspf" flush="true"/>



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
                                        HOT!!!
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






        <hr>

    </body>
</html>

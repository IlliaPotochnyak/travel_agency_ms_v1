<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
    isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
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
        <div class="container">
            <h2>List of tours!!!</h2>
        </div>
        <hr>

        <hr>
        <div class="container">
            <table class="table">
                <tbody>

                    <thead>
                        <td></td>
                        <td>Tour</td>
                        <td>Description</td>
                        <td>Price</td>
                        <td>Persons<br>Number</td>
                        <td>Type</td>
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
                                <td><c:out value="${tour.tourTypeId}"/></td>

                        </tr>
                    </c:forEach>
                </tbody>

            </table>
        <div>
        <hr>

    </body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
    isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <style>

        </style>

    </head>
    <body>
        <div class="container mt-3">
            <h2>List of Receipts!!!</h2>
        </div>
        <hr>

        <hr>
        <div class="container">

                    <table class="table table-hover">
                        <tbody>

                            <thead>
                                <td>id</td>
                                <td>User id</td>
                                <td>User First Name</td>
                                <td>User Last Name</td>
                                <td>Tour</td>
                                <td>Tour Name</td>
                                <td>Amount</td>
                                <td>Receipt Status</td>
                                <td>Datetime</td>
                            </thead>
                            <c:forEach var="receipt" items="${receiptList}">
                                <tr>
                                        <td><c:out value="${receipt.id}"/></td>
                                        <td><c:out value="${receipt.userId}"/></td>
                                        <td><c:out value="${receipt.userFirstName}"/></td>
                                        <td><c:out value="${receipt.userLastName}"/></td>
                                        <td><c:out value="${receipt.tourId}"/></td>
                                        <td><c:out value="${receipt.tourName}"/></td>
                                        <td><c:out value="${receipt.amount}"/></td>
                                        <td><c:out value="${receipt.orderStatus}"/></td>
                                        <td><c:out value="${receipt.datetime}"/></td>

                                </tr>
                            </c:forEach>
                        </tbody>

                    </table>

        <div>

        <hr>

    </body>
</html>

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
                            <td>
                                <c:out value="${receipt.orderStatus}"/>
                                <c:if test="${UserRole == 'admin' or UserRole == 'manager'}">
                                    <form action="ReceiptStatusServlet" method="POST" >
                                        <input type="hidden" name="receiptId" value="${receipt.id}">
                                        <select id="orderStatus" class="form-select form-select-sm" name="orderStatus">
                                            <option value="registered" <c:if test="${receipt.orderStatus eq 'registered'}">hidden</c:if>>registered</option>
                                            <option value="paid" <c:if test="${receipt.orderStatus eq 'paid'}">hidden</c:if>>paid</option>
                                            <option value="canceled" <c:if test="${receipt.orderStatus eq 'canceled'}">hidden</c:if>>canceled</option>
                                        </select>
                                        <button type="submit" class="btn btn-primary btn-sm">Change</button>
                                    </form>
                                </c:if>
                            </td>
                            <td><c:out value="${receipt.datetime}"/></td>

                            <!-- The Modal -->
                            <div class="modal" id="myModal">
                              <div class="modal-dialog">
                                <div class="modal-content">

                                  <!-- Modal Header -->
                                  <div class="modal-header">
                                    <h4 class="modal-title">Modal Heading</h4>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                                  </div>

                                  <!-- Modal body -->
                                  <div class="modal-body">

                                  </div>

                                  <!-- Modal footer -->
                                  <div class="modal-footer">
                                    <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
                                  </div>

                                </div>
                              </div>
                            </div>

                        </tr>
                    </c:forEach>
                </tbody>

            </table>

        <div>

        <hr>

    </body>
</html>

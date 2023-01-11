<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
    isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <!-- Latest compiled and minified CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">

        <!-- Latest compiled JavaScript -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>

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
                        <c:if test="${UserRole == 'admin' or UserRole == 'manager'}">
                            <td>Discount</td>
                            <td>Price</td>
                        </c:if>
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
                            <c:if test="${UserRole == 'admin' or UserRole == 'manager'}">
                                <td>
                                    <c:out value="${receipt.discount}"/>


                                    <div class="dropdown">
                                      <button type="button" class="btn btn-primary dropdown-toggle btn-sm" data-bs-toggle="dropdown">
                                        Change
                                      </button>
                                      <ul class="dropdown-menu">
                                        <li><form class="dropdown-item form-control form-control-sm"action="SetDiscountServlet" method="POST" >
                                                <input type="hidden" name="receiptId" value="${receipt.id}">
                                                <input  type="number" class="form-control w-50 bg-light p-3" name="discount" required maxlength="2"
                                                    min="0" max="99">
                                                <button  type="submit" class="btn btn-primary btn-sm">Set Discount</button>
                                            </form></li>

                                      </ul>
                                    </div>

                                </td>
                                <td><c:out value="${receipt.price}"/></td>
                            </c:if>
                            <td><c:out value="${receipt.amount}"/></td>
                            <td>

                                <c:if test="${UserRole == 'admin' or UserRole == 'manager'}">
                                    <form action="ReceiptStatusServlet" method="POST" >
                                        <input type="hidden" name="receiptId" value="${receipt.id}">
                                        <select id="orderStatus" class="form-select form-select-sm" name="orderStatus" onchange="this.form.submit()">
                                            <option value="registered" <c:if test="${receipt.orderStatus eq 'registered'}">class="fw-bolder" selected</c:if>>registered</option>
                                            <option value="paid" <c:if test="${receipt.orderStatus eq 'paid'}">class="fw-bolder" selected</c:if>>paid</option>
                                            <option value="canceled" <c:if test="${receipt.orderStatus eq 'canceled'}">class="fw-bolder" selected</c:if>>canceled</option>
                                        </select>
                                    </form>
                                </c:if>
                                <c:if test="${UserRole == 'client'}">
                                    <c:out value="${receipt.orderStatus}"/>
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

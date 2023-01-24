<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
    isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="languages/messages"/>
<html lang="${sessionScope.lang}">

<html>
    <head>
        <!-- Latest compiled and minified CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">

        <!-- Latest compiled JavaScript -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>

    </head>
    <body>

        <jsp:include page="Header.jsp" flush="true"/>
        <jsp:include page="CabinetMenu.jsp" flush="true"/>

        <div class="container mt-3">
            <h2><fmt:message key="listReceipt.message" /></h2>
        </div>
        <hr>

        <hr>
        <div class="container">

            <table class="table table-hover">
                <tbody>

                    <thead>
                        <td>id</td>
                        <td><fmt:message key="listReceipt.tableUserId" /></td>
                        <td><fmt:message key="listReceipt.tableUserFirstName" /></td>
                        <td><fmt:message key="listReceipt.tableUserLastName" /></td>
                        <td><fmt:message key="listReceipt.tableTourId" /></td>
                        <td><fmt:message key="listReceipt.tableTourName" /></td>
                        <c:if test="${UserRole == 'admin' or UserRole == 'manager'}">
                            <td><fmt:message key="listReceipt.tableDiscount" /></td>
                            <td><fmt:message key="listReceipt.tablePrice" /></td>
                        </c:if>
                        <td><fmt:message key="listReceipt.tableAmount" /></td>
                        <td><fmt:message key="listReceipt.tableReceiptStatus" /></td>
                        <td><fmt:message key="listReceipt.tableDatetime" /></td>
                    </thead>
                    <c:forEach var="receipt" items="${receiptList}">
                        <tr>
                            <td><c:out value="${receipt.id}"/></td>
                            <td><c:out value="${receipt.userId}"/></td>
                            <td><c:out value="${receipt.userFirstName}"/></td>
                            <td><c:out value="${receipt.userLastName}"/></td>
                            <td><c:out value="${receipt.tourId}"/></td>
                            <td><a href="controller?command=tour_page&tourId=${receipt.tourId}" ><c:out value="${receipt.tourName}"/></a></td>
                            <c:if test="${UserRole == 'admin' or UserRole == 'manager'}">
                                <td>
                                    <c:out value="${receipt.discount}"/>
                                    <div class="dropdown">
                                      <button type="button" class="btn btn-primary dropdown-toggle btn-sm" data-bs-toggle="dropdown">
                                        <fmt:message key="listReceipt.tableChange" />
                                      </button>
                                      <ul class="dropdown-menu">
                                        <li><form class="dropdown-item form-control form-control-sm"action="controller" method="POST" >
                                                <input type="hidden"  name="command" value="set_discount">
                                                <input type="hidden" name="receiptId" value="${receipt.id}">
                                                <input type="hidden" name="tourId" value="${receipt.tourId}">
                                                <input  type="number" class="form-control w-50 bg-light p-3" name="discount" required maxlength="2"
                                                    min="0" max="99" value=0>
                                                <button  type="submit" class="btn btn-primary btn-sm"><fmt:message key="listReceipt.tableSetDiscount" /></button>
                                            </form>
                                        </li>
                                      </ul>
                                    </div>

                                </td>
                                <td><c:out value="${receipt.price}"/></td>
                            </c:if>
                            <td><c:out value="${receipt.amount}"/></td>
                            <td>

                                <c:if test="${UserRole == 'admin' or UserRole == 'manager'}">
                                    <form action="controller" method="POST" >
                                        <input type="hidden"  name="command" value="receipt_status_change">
                                        <input type="hidden" name="receiptId" value="${receipt.id}">
                                        <select id="orderStatus" class="form-select form-select-sm" name="orderStatus" onchange="this.form.submit()">
                                            <option value="registered" <c:if test="${receipt.orderStatus eq 'registered'}">class="fw-bolder" selected</c:if>><fmt:message key="listReceipt.tableStatRegistered" /></option>
                                            <option value="paid" <c:if test="${receipt.orderStatus eq 'paid'}">class="fw-bolder" selected</c:if>><fmt:message key="listReceipt.tableStatPaid" /></option>
                                            <option value="canceled" <c:if test="${receipt.orderStatus eq 'canceled'}">class="fw-bolder" selected</c:if>><fmt:message key="listReceipt.tableStatCanceled" /></option>
                                        </select>
                                    </form>
                                </c:if>
                                <c:if test="${UserRole == 'client'}">
                                    <c:out value="${receipt.orderStatus}"/>
                                </c:if>
                            </td>
                            <td><c:out value="${receipt.datetime}"/></td>


                        </tr>
                    </c:forEach>
                </tbody>

            </table>

            <%--For displaying Previous link except for the 1st page --%>
                                <c:if test="${currentPage != 1}">
                                    <td><a href="controller?command=receipt_list&page=${currentPage - 1}#receiptList"><fmt:message key="main.mainPrevious" /></a></td>
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
                                                    <td><a href="controller?command=receipt_list&page=${i}#receiptList">${i}</a></td>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </tr>
                                </table>

                                <%--For displaying Next link --%>
                                <c:if test="${currentPage lt noOfPages}">
                                    <td><a href="controller?command=receipt_list&page=${currentPage + 1}#receiptList"><fmt:message key="main.mainNext" /></a></td>
                                </c:if>


            <div>

        <hr>

        <div class="container">
            <jsp:include page="Footer.jsp" />
        </div>

    </body>
</html>

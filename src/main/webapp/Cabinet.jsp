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
            <jsp:include page="WEB-INF/view/Header.jsp" flush="true"/>

            <div class="container">
                <h2>Personal cabinet</h2>
            </div>

            <div class="container">
                <c:if test="${UserRole == 1 or UserRole == 2}">
                    <a href="#tourList">Tour List</a>
                </c:if>
                <a href="#receiptList">Receipt List</a>
            </div>

            <hr>
            <c:if test="${UserRole == 1 or UserRole == 2}">
                <a id="tourList">
                    <jsp:include page="TourList" flush="true"/>
                </a>
            </c:if>
            <a id="receiptList">
                <jsp:include page="ReceiptListServlet" flush="true"/>
            </a>
            <hr>
        </body>
</html>

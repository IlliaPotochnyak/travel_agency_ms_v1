<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
    isELIgnored = "false" session = "true"%>
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

    </head>
    <body>


        <div class="container">
                        <h2><fmt:message key="cabinet.message" /></h2>
                    </div>

                    <div class="container">
                        <a href="Cabinet.jsp"><fmt:message key="cabinet.userInfo" /></a>
                        <c:if test="${UserRole == 'admin'}">
                            <a href="AddTour.jsp"><fmt:message key="cabinet.addTour" /></a>
                        </c:if>
                        <c:if test="${UserRole == 'admin' or UserRole == 'manager'}">
                            <a href="controller?command=receipt_list"><fmt:message key="cabinet.receiptList" /></a>
                        </c:if>
                        <c:if test="${UserRole == 'admin'}">
                            <a href="#userList"><fmt:message key="cabinet.userList" /></a>
                        </c:if>

                    </div>

    </body>
<html>
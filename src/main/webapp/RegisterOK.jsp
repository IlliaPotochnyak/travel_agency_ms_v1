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
        <jsp:include page="WEB-INF/view/Header.jsp" flush="true"/>
        <div class="container">
            <h2><fmt:message key="registerOk.message" /></h2>
        </div>
        <div class="container">
            <p>
                <fmt:message key="registerOk.text" />
            </p>
        </div>
        <hr>
        <div class="container">
            <a href="Login.jsp"><fmt:message key="registerOk.signIn" /></a>
        </div>



    </body>
</html>

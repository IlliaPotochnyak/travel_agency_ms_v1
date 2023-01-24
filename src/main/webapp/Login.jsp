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
                <h2><fmt:message key="login.message" /></h2>
            </div>
            <div class="container">
                ${errorMessage}
            </div>
            <hr>
            <div class="container mt-5">
                <form action="controller" method="post">
                    <input type="hidden"  name="command" value="login">
                    <div class="mb-3">
                        <label class="form-label"><fmt:message key="login.email" />* : </label>
                        <input type="email" class="form-control" placeholder="Enter Email" name="email" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label"><fmt:message key="login.password" />* : </label>
                        <input type="password" class="form-control" placeholder="Enter Password" name="password" required>
                        <button type="submit" class="btn btn-primary"><fmt:message key="login.button" /></button>
                    </div>

                </form>
            </div>
            <hr>

            <div class="container">
                <jsp:include page="WEB-INF/view/Footer.jsp" />
            </div>

        </body>
</html>

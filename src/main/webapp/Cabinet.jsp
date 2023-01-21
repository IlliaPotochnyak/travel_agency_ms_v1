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
            <jsp:include page="WEB-INF/view/CabinetMenu.jsp" flush="true"/>



            <%--User Info block --%>
            <div class="container">
                <button data-bs-toggle="collapse" data-bs-target="#userInfo" class="btn btn-primary"><fmt:message key="cabinet.userInfo" /></button>
                <div id="userInfo" class="collapse show">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-sm-2 p-3 bg-primary text-white"><fmt:message key="cabinet.tableUserFirstName" /></div>
                            <div class="col-sm-6 p-3 bg-dark text-white">${UserFirstName}</div>
                        </div>
                        <div class="row">
                            <div class="col-sm-2 p-3 bg-primary text-white"><fmt:message key="cabinet.tableUserLastName" /></div>
                            <div class="col-sm-6 p-3 bg-dark text-white">${UserLastName}</div>
                        </div>
                        <div class="row">
                            <div class="col-sm-2 p-3 bg-primary text-white">Email</div>
                            <div class="col-sm-6 p-3 bg-dark text-white">${UserEmail}</div>
                        </div>
                        <div class="row">
                            <div class="col-sm-2 p-3 bg-primary text-white"><fmt:message key="cabinet.tableUserLastName" /></div>
                            <div class="col-sm-6 p-3 bg-dark text-white">${UserPhone}</div>
                        </div>

                    </div>
                </div>
            </div>


            <hr>
        </body>
</html>

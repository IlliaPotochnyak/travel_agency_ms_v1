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
                <h2><fmt:message key="cabinet.message" /></h2>
            </div>

            <div class="container">
                <c:if test="${UserRole == 'admin'}">
                    <a href="AddTour.jsp"><fmt:message key="cabinet.addTour" /></a>
                </c:if>
                <c:if test="${UserRole == 'admin' or UserRole == 'manager'}">
                    <a href="#receiptList"><fmt:message key="cabinet.receiptList" /></a>
                </c:if>
                <c:if test="${UserRole == 'admin'}">
                    <a href="#userList"><fmt:message key="cabinet.userList" /></a>
                </c:if>

            </div>

            <%--User Info block --%>
            <div class="container">
                <button data-bs-toggle="collapse" data-bs-target="#userInfo" class="btn btn-primary"><fmt:message key="cabinet.userInfo" /></button>
                <div id="userInfo" class="collapse">
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


            <%--List block --%>

            <a id="receiptList">
                <jsp:include page="ReceiptListServlet" flush="true"/>

                <%--For displaying Previous link except for the 1st page --%>
                    <c:if test="${currentPage != 1}">
                        <td><a href="Cabinet.jsp?page=${currentPage - 1}#receiptList"><fmt:message key="main.mainPrevious" /></a></td>
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
                                        <td><a href="Cabinet.jsp?page=${i}#receiptList">${i}</a></td>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </tr>
                    </table>

                    <%--For displaying Next link --%>
                    <c:if test="${currentPage lt noOfPages}">
                        <td><a href="Cabinet.jsp?page=${currentPage + 1}#receiptList"><fmt:message key="main.mainNext" /></a></td>
                    </c:if>

            </a>
            <hr>

            <c:if test="${UserRole == 'admin'}">
                <a id="userList">
                    <jsp:include page="UserListServlet" flush="true"/>

                    <%--For displaying Previous link except for the 1st page --%>
                    <c:if test="${currentPageUsers != 1}">
                        <td><a href="Cabinet.jsp?pageUsers=${currentPageUsers - 1}#userList"><fmt:message key="main.mainPrevious" /></a></td>
                    </c:if>

                    <%--For displaying Page numbers.
                    The when condition does not display a link for the current page--%>
                    <table border="1" cellpadding="5" cellspacing="5">
                        <tr>
                            <c:forEach begin="1" end="${noOfPagesUsers}" var="i">
                                <c:choose>
                                    <c:when test="${currentPageUsers eq i}">
                                        <td>${i}</td>
                                    </c:when>
                                    <c:otherwise>
                                        <td><a href="Cabinet.jsp?pageUsers=${i}#userList">${i}</a></td>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </tr>
                    </table>

                    <%--For displaying Next link --%>
                    <c:if test="${currentPageUsers lt noOfPagesUsers}">
                        <td><a href="Cabinet.jsp?pageUsers=${currentPageUsers + 1}#userList"><fmt:message key="main.mainNext" /></a></td>
                    </c:if>

                </a>
            </c:if>
            <hr>
        </body>
</html>

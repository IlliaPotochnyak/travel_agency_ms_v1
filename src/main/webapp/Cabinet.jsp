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
                <c:if test="${UserRole == 'admin'}">
                    <a href="AddTour.jsp">Add New Tour</a>
                </c:if>
                <c:if test="${UserRole == 'admin' or UserRole == 'manager'}">
                    <a href="#receiptList">Receipt List</a>
                </c:if>
                <c:if test="${UserRole == 'admin'}">
                    <a href="#userList">User List</a>
                </c:if>

            </div>

            <%--User Info block --%>
            <div class="container">
                <button data-bs-toggle="collapse" data-bs-target="#userInfo" class="btn btn-primary">User Info</button>
                <div id="userInfo" class="collapse">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-sm-2 p-3 bg-primary text-white">First Name</div>
                            <div class="col-sm-6 p-3 bg-dark text-white">${UserFirstName}</div>
                        </div>
                        <div class="row">
                            <div class="col-sm-2 p-3 bg-primary text-white">Last Name</div>
                            <div class="col-sm-6 p-3 bg-dark text-white">${UserLastName}</div>
                        </div>
                        <div class="row">
                            <div class="col-sm-2 p-3 bg-primary text-white">Email</div>
                            <div class="col-sm-6 p-3 bg-dark text-white">${UserEmail}</div>
                        </div>
                        <div class="row">
                            <div class="col-sm-2 p-3 bg-primary text-white">Phone</div>
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
                        <td><a href="Cabinet.jsp?page=${currentPage - 1}#receiptList">Previous</a></td>
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
                        <td><a href="Cabinet.jsp?page=${currentPage + 1}#receiptList">Next</a></td>
                    </c:if>

            </a>
            <hr>

            <c:if test="${UserRole == 'admin'}">
                <a id="userList">
                    <jsp:include page="UserListServlet" flush="true"/>

                    <%--For displaying Previous link except for the 1st page --%>
                    <c:if test="${currentPageUsers != 1}">
                        <td><a href="Cabinet.jsp?pageUsers=${currentPageUsers - 1}#userList">Previous</a></td>
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
                        <td><a href="Cabinet.jsp?pageUsers=${currentPageUsers + 1}#userList">Next</a></td>
                    </c:if>

                </a>
            </c:if>
            <hr>
        </body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
    isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="languages/messages"/>
<html lang="${sessionScope.lang}">

<html>
    <head>
        <style>

        </style>

    </head>
    <body>

        <jsp:include page="Header.jsp" flush="true"/>
        <jsp:include page="CabinetMenu.jsp" flush="true"/>

        <div class="container mt-3">
            <h2><fmt:message key="listUsers.message" /></h2>
        </div>
        <hr>

        <hr>
        <div class="container">

            <table class="table table-hover">
                <tbody>

                    <thead>
                        <td>id</td>
                        <td><fmt:message key="listUsers.tableFirstName" /></td>
                        <td><fmt:message key="listUsers.tableLastName" /></td>
                        <td>Email</td>
                        <td><fmt:message key="listUsers.tablePhone" /></td>
                        <td><fmt:message key="listUsers.tableActive" /></td>
                        <td><fmt:message key="listUsers.tableRole" /></td>
                    </thead>
                    <c:forEach var="user" items="${userList}">
                        <tr>
                            <td><c:out value="${user.id}"/></td>
                            <td><c:out value="${user.firstName}"/></td>
                            <td><c:out value="${user.lastName}"/></td>
                            <td><c:out value="${user.email}"/></td>
                            <td><c:out value="${user.phone}"/></td>
                            <td>

                                <form action="controller" method="POST" >
                                    <input type="hidden"  name="command" value="user_active">
                                    <input type="hidden" name="userId" value="${user.id}">
                                    <select id="userActive" class="form-select form-select-sm" name="userActive" onchange="this.form.submit()">

                                        <option value="1" <c:if test="${user.active eq 1}"> class="fw-bolder" selected</c:if>>
                                            <span class="fw-bolder"><fmt:message key="listUsers.tableActiveStatus" /></span>
                                        </option>
                                        <option value="0" <c:if test="${user.active eq 0}"> class="fw-bolder" selected</c:if>>
                                            <span class="text-danger fw-bolder"><fmt:message key="listUsers.tableBlockedStatus" /></span>
                                        </option>
                                    </select>
                                </form>

                            </td>
                            <td><c:out value="${user.role}"/></td>


                        </tr>
                    </c:forEach>
                </tbody>

            </table>

            <%--For displaying Previous link except for the 1st page --%>
                                <c:if test="${currentPageUsers != 1}">
                                    <td><a href="controller?command=user_list&pageUsers=${currentPageUsers - 1}#userList"><fmt:message key="main.mainPrevious" /></a></td>
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
                                                    <td><a href="controller?command=user_list&pageUsers=${i}#userList">${i}</a></td>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </tr>
                                </table>

                                <%--For displaying Next link --%>
                                <c:if test="${currentPageUsers lt noOfPagesUsers}">
                                    <td><a href="controller?command=user_list&pageUsers=${currentPageUsers + 1}#userList"><fmt:message key="main.mainNext" /></a></td>
                                </c:if>


        <div>

        <hr>

    </body>
</html>

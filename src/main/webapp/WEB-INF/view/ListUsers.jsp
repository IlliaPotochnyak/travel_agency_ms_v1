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

                                <form action="UserActiveServlet" method="POST" >
                                    <input type="hidden" name="userId" value="${user.id}">
                                    <select id="userActive" class="form-select form-select-sm" name="userActive" onchange="this.form.submit()">

                                        <option value="1" <c:if test="${user.active eq 1}"> class="fw-bolder" selected</c:if>>
                                            <span class="fw-bolder"><fmt:message key="listUsers.tableActiveStatus" /></span>
                                        </option>
                                        <option value="0" <c:if test="${user.active eq 0}"> class="fw-bolder" selected</c:if>>
                                            <span class="fw-bolder"><fmt:message key="listUsers.tableBlockedStatus" /></span>
                                        </option>
                                    </select>
                                </form>

                            </td>
                            <td><c:out value="${user.role}"/></td>


                        </tr>
                    </c:forEach>
                </tbody>

            </table>

        <div>

        <hr>

    </body>
</html>

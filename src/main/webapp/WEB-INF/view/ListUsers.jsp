<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
    isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <style>

        </style>

    </head>
    <body>
        <div class="container mt-3">
            <h2>List of Users!!!</h2>
        </div>
        <hr>

        <hr>
        <div class="container">

            <table class="table table-hover">
                <tbody>

                    <thead>
                        <td>id</td>
                        <td>User First Name</td>
                        <td>User Last Name</td>
                        <td>Email</td>
                        <td>Phone</td>
                        <td>Active</td>
                        <td>Role</td>
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
                                            <span class="fw-bolder">active</span>
                                        </option>
                                        <option value="0" <c:if test="${user.active eq 0}"> class="fw-bolder" selected</c:if>>
                                            <span class="fw-bolder">blocked</span>
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

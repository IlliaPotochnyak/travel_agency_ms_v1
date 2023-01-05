<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
    isELIgnored = "false" session = "true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <!-- Latest compiled and minified CSS -->
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">

        <!-- Latest compiled JavaScript -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>

    </head>
    <body>
        <div class="container">
            <nav class="navbar navbar-expand-sm bg-primary navbar-dark">
              <div class="container-fluid">
                <ul class="navbar-nav">
                  <li class="nav-item">
                    <a class="nav-link active" href="index.jsp">Home</a>
                  </li>
                  <c:if test="${empty UserId}">
                      <li class="nav-item">
                        <a class="nav-link active" href="Register.jsp">Register</a>
                      </li>
                      <li class="nav-item">
                        <a class="nav-link active" href="Login.jsp">Login</a>
                      </li>
                  </c:if>
                  <c:if test="${not empty UserId}">
                        <li class="nav-item">
                          <a class="nav-link active" href="Cabinet.jsp">Cabinet</a>
                        </li>
                        <li class="nav-item">
                          <a class="nav-link active" href="Logout">Logout</a>
                        </li>
                    </c:if>
                  </ul>
              </div>
              <div class="container-fluid">
                    <c:if test="${not empty UserId}">
                        <span class="navbar-text">Hello,
                            <span class="text-uppercase">${UserFirstName} ${UserLastName}</span>
                        </span>
                    </c:if>
              </div>

            </nav>
        </div>
    </body>
<html>
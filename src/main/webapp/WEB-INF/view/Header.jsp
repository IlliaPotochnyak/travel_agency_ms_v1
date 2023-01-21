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
            <nav class="navbar navbar-expand-sm bg-primary navbar-dark">
              <div class="container-fluid">
                <ul class="navbar-nav">
                  <li class="nav-item">
                    <a class="nav-link active" href="index.jsp"><fmt:message key="header.main" /></a>
                  </li>
                  <c:if test="${empty UserId}">
                      <li class="nav-item">
                        <a class="nav-link active" href="Register.jsp"><fmt:message key="header.register" /></a>
                      </li>
                      <li class="nav-item">
                        <a class="nav-link active" href="Login.jsp"><fmt:message key="header.login" /></a>
                      </li>
                  </c:if>
                  <c:if test="${not empty UserId}">
                        <li class="nav-item">
                          <a class="nav-link active" href="Cabinet.jsp"><fmt:message key="header.cabinet" /></a>
                        </li>
                        <li class="nav-item">
                          <a class="nav-link active" href="controller?command=logout"><fmt:message key="header.logout" /></a>
                        </li>
                    </c:if>
                  </ul>
              </div>
              <div class="container-fluid">
                    <c:if test="${not empty UserId}">
                        <span class="navbar-text"><fmt:message key="header.hello" />,
                            <span class="text-uppercase">${UserFirstName} ${UserLastName}</span>
                        </span>
                    </c:if>
              </div>

              <div class="container-fluid">
                  <form action="#" >

                      <select id="sessionLocale" class="form-select form-select-sm" name="sessionLocale" onchange="this.form.submit()">
                          <option value="en" <c:if test="${sessionScope.lang eq 'en'}">class="fw-bolder" selected</c:if>>EN</option>
                          <option value="ua" <c:if test="${sessionScope.lang eq 'ua'}">class="fw-bolder" selected</c:if>>UA</option>

                      </select>
                  </form>
              </div>

            </nav>
        </div>
    </body>
<html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
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
        <div class="container">
            <h2>Tour Page</h2>
        </div>
        <div class="container">
            ${UserFirstName} ${UserLastName}
        </div>
        <div class="container">
            <c:if test="${empty UserFirstName}">
                <a href="Register.jsp">Register</a>
                <a href="Login.jsp">Login</a>
            </c:if>
        <c:if test="${not empty UserFirstName}">
                <a href="Logout">Logout</a>
        </c:if>
        </div>
        <div class="container">
            <h3>${tour.name}</h3>
        </div>

        <div class="container mt-5">
            <div class="row">
                <div class="col-sm-2">
                    <p>
                        <c:if test="${tour.hot eq 1}">
                            <span class="badge bg-danger">
                                HOT!!!
                            </span>
                        </c:if>
                    </p>
                    <div class="border border-primary">
                        <p>Tour type - ${tour.tourTypeId}</p>
                        <p>for ${tour.personsNumber} persons</p>
                    </div>
                    <div class="border border-primary">
                        <p>Hotel type - ${tour.hotelTypeId}</p>
                    </div>

                </div>

                <div class="col-sm-8">
                    ${tour.description}
                </div>
            </div>
        </div>
        <div class="container p-3 my-3 bg-primary text-white" >
            <div class="text-center">
                <p class="border border-primary"><h4>Price - ${tour.price}</h4></p>
                <form>
                    <button type="submit" class="btn btn-warning">BOOK TOUR</button>
                </form>
            </div>
        </div>

    </body>
</html>

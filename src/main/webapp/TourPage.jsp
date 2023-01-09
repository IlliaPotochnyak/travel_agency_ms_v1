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
        <jsp:include page="WEB-INF/view/Header.jsp" flush="true"/>

        <div class="container">
            <h2>Tour Page</h2>
        </div>
        <div class="container">
            ${errorUpdateTour}
        </div>
        <div class="container">
            <c:if test="${UserRole == 'admin'}">
                <a type="button" class="btn btn-primary" href="EditTour.jsp?tourId=${tour.id}&tourName=${tour.name}&tourDesc=${tour.description}&tourHot=${tour.hot}&tourType=${tour.tourType}&hotelType=${tour.hotelType}&personsNumber=${tour.personsNumber}&tourPrice=${tour.price}">Edit tour</a>

                <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#myModal">
                    Delete tour
                  </button>

                <!-- The Modal -->
                <div class="modal" id="myModal">
                  <div class="modal-dialog modal-sm">
                    <div class="modal-content">

                      <!-- Modal Header -->
                      <div class="modal-header">
                        <h4 class="modal-title">Are you sure?</h4>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                      </div>
                      <!-- Modal footer -->
                      <div class="modal-footer">
                        <form action="DeleteTourServlet" method="post">
                            <input type="hidden" name="tourId" value="${tour.id}" />
                            <button type="submit" class="btn btn-danger">Delete tour</button>
                        </form>
                        <button type="button" class="btn btn-primary" data-bs-dismiss="modal">No!</button>
                      </div>

                    </div>
                  </div>
                </div>


            </c:if>
        </div>

        <div class="container mt-3">
            <c:if test="${UserRole == 'admin' or UserRole == 'manager'}">
                    <form action="EditTourServlet" method="post">
                        <input type="hidden" name="tourId" value="${tour.id}">
                        <input type="hidden" name="tourName" value="${tour.name}">
                        <input type="hidden" name="tourDescription" value="${tour.description}">
                        <input type="hidden" name="PersonNumber" value="${tour.personsNumber}">
                        <input type="hidden" name="tourPrice" value="${tour.price}">
                        <input type="hidden" name="tourType" value="${tour.tourType}">
                        <input type="hidden" name="hotelType" value="${tour.hotelType}">
                        <c:if test="${tour.hot ne 1}">
                            <input type="hidden" name="tourHot" value="1">
                            <div class="mb-3">
                                <button type="submit" class="btn btn-danger">Make Tour HOT</button>
                            </div>
                        </c:if>
                        <c:if test="${tour.hot eq 1}">
                            <button type="submit" class="btn btn-primary">Make Tour NOT HOT</button>
                        </c:if>

                    </form>
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
                        <p>Tour type - ${tour.tourType}</p>
                        <p>for ${tour.personsNumber} persons</p>
                    </div>
                    <div class="border border-primary">
                        <p>Hotel type - ${tour.hotelType}</p>
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
                <c:if test="${not empty UserFirstName}">
                    <form action="ReceiptRegister">
                        <input type="hidden" name="tourId" value="${tour.id}">
                        <button type="submit" class="btn btn-warning">BOOK TOUR</button>
                    </form>
                </c:if>
                <c:if test="${empty UserFirstName}">
                    <form>
                        To book this tour please <h3><a class="text-danger" href="Login.jsp">Login!</a></h3>
                    </form>
                </c:if>
            </div>
        </div>

    </body>
</html>

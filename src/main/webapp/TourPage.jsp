<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
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
            <h2><fmt:message key="tourPage.message" /></h2>
        </div>
        <div class="container">
            ${errorUpdateTour}
        </div>
        <div class="container">
            <c:if test="${UserRole == 'admin'}">
                <a type="button" class="btn btn-primary" href="EditTour.jsp?tourId=${tour.id}&tourName=${tour.name}&tourHot=${tour.hot}&tourType=${tour.tourType}&hotelType=${tour.hotelType}&personsNumber=${tour.personsNumber}&tourPrice=${tour.price}&maxDiscount=${tour.maxDiscount}&tourDesc=${tour.description}"><fmt:message key="tourPage.editTour" /></a>

                <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#myModal">
                    <fmt:message key="tourPage.deleteTour" />
                  </button>

                <!-- The Modal -->
                <div class="modal" id="myModal">
                  <div class="modal-dialog modal-sm">
                    <div class="modal-content">

                      <!-- Modal Header -->
                      <div class="modal-header">
                        <h4 class="modal-title"><fmt:message key="tourPage.modalText" /></h4>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                      </div>
                      <!-- Modal footer -->
                      <div class="modal-footer">
                        <form action="controller" method="post">
                            <input type="hidden"  name="command" value="delete_tour">
                            <input type="hidden" name="tourId" value="${tour.id}" />
                            <button type="submit" class="btn btn-danger"><fmt:message key="tourPage.deleteTour" /></button>
                        </form>
                        <button type="button" class="btn btn-primary" data-bs-dismiss="modal"><fmt:message key="tourPage.modalNo" /></button>
                      </div>

                    </div>
                  </div>
                </div>


            </c:if>
        </div>

        <div class="container mt-3">
            <c:if test="${UserRole == 'admin' or UserRole == 'manager'}">
                    <form action="controller" method="post">
                        <input type="hidden"  name="command" value="edit_tour">
                        <input type="hidden" name="tourId" value="${tour.id}">
                        <input type="hidden" name="tourName" value="${tour.name}">
                        <input type="hidden" name="tourDescription" value="${tour.description}">
                        <input type="hidden" name="PersonNumber" value="${tour.personsNumber}">
                        <input type="hidden" name="maxDiscount" value="${tour.maxDiscount}">
                        <input type="hidden" name="tourPrice" value="${tour.price}">
                        <input type="hidden" name="tourType" value="${tour.tourType}">
                        <input type="hidden" name="hotelType" value="${tour.hotelType}">
                        <c:if test="${tour.hot ne 1}">
                            <input type="hidden" name="tourHot" value="1">
                            <div class="mb-3">
                                <button type="submit" class="btn btn-danger"><fmt:message key="tourPage.makeTourHot" /></button>
                            </div>
                        </c:if>
                        <c:if test="${tour.hot eq 1}">
                            <button type="submit" class="btn btn-primary"><fmt:message key="tourPage.makeTourNotHot" /></button>
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
                                <fmt:message key="tourPage.hot" />
                            </span>
                        </c:if>
                    </p>
                    <div class="border border-primary">
                        <p><fmt:message key="tourPage.TourType" /> ${tour.tourType}</p>
                        <p><fmt:message key="tourPage.for" /> ${tour.personsNumber} <fmt:message key="tourPage.persons" /></p>
                    </div>
                    <div class="border border-primary">
                        <p><fmt:message key="tourPage.hotelType" /> - ${tour.hotelType}</p>
                    </div>
                    <c:if test="${UserRole == 'admin' or UserRole == 'manager'}">
                        <div class="border border-primary">
                            <p><fmt:message key="tourPage.maxDiscount" /> - ${tour.maxDiscount}</p>
                        </div>
                    </c:if>
                </div>

                <div class="col-sm-8">
                    ${tour.description}
                </div>
            </div>
        </div>
        <div class="container p-3 my-3 bg-primary text-white" >
            <div class="text-center">
                <p class="border border-primary"><h4><fmt:message key="tourPage.price" /> - ${tour.price}</h4></p>
                <c:if test="${not empty UserFirstName}">
                    <form action="controller" method="POST">
                        <input type="hidden"  name="command" value="receipt_register">
                        <input type="hidden" name="tourId" value="${tour.id}">
                        <button type="submit" class="btn btn-warning"><fmt:message key="tourPage.bookTour" /></button>
                    </form>
                </c:if>
                <c:if test="${empty UserFirstName }">
                    <form>
                        <fmt:message key="tourPage.bookTourText" /> <h3><a class="text-danger" href="Login.jsp"><fmt:message key="tourPage.login" />!</a></h3>
                    </form>
                </c:if>
            </div>
        </div>

        <div class="container">
            <jsp:include page="WEB-INF/view/Footer.jsp" />
        </div>

    </body>
</html>

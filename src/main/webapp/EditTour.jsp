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
            <h2>Edit Tour Page</h2>
        </div>
        <div class="container bg-secondary  mt-5">
            <form action="EditTourServlet" method="post">

                <input type="hidden" name="tourId" value="${param.tourId}">

                <div class="container">
                    <h3>${tour.name}</h3>
                </div>
                <div class="mb-3">
                    <label class="form-label">Tour Name </label>
                    <input type="text" class="form-control" name="tourName" required maxlength="64" value="${param.tourName}">
                </div>
                <div class="mb-3">
                    <label class="form-label">Description </label>
                    <textarea name="tourDescription" class="form-control" maxlength="65500" rows="5">
                        ${param.tourDesc}
                    </textarea>
                </div>

                <div class="row">
                    <div class="col">

                        <div class="mb-3">
                            <label class="form-label ">Persons Number </label>
                            <input type="number" class="form-control w-25" name="PersonNumber" required maxlength="3"
                                min="1" max="999" value="${param.personsNumber}">
                        </div>
                    </div>
                    <div class="col">
                        <div class="mb-3">
                            <label class="form-label ">Price </label>
                            <input type="number" class="form-control w-25" name="tourPrice" required maxlength="7"
                                min="1" max="9999999" value="${param.tourPrice}">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <div class="mb-3">
                            <label for="tour-type" class="col-sm-2 control-label">Tour type</label>
                            <select id="tour-type" class="form-control w-25" name="tourType">
                                <option value="rest" <c:if test="${param.tourType eq 'rest'}">selected="selected"</c:if>>rest</option>
                                <option value="excursion" <c:if test="${param.tourType eq 'excursion'}">selected="selected"</c:if>>excursion</option>
                                <option value="shopping" <c:if test="${param.tourType eq 'shopping'}">selected="selected"</c:if>>shopping</option>
                            </select>
                        </div>
                    </div>
                    <div class="col">
                        <div class="mb-3">
                            <label for="hotel-type" class="col-sm-2 control-label">Hotel type</label>
                            <select id="hotel-type" class="form-control w-25" name="hotelType">
                                <option value="5" <c:if test="${param.hotelType eq 5}">selected="selected"</c:if>>five stars</option>
                                <option value="4" <c:if test="${param.hotelType eq 4}">selected="selected"</c:if>>four stars</option>
                                <option value="3" <c:if test="${param.hotelType eq 3}">selected="selected"</c:if>>three stars</option>
                                <option value="2" <c:if test="${param.hotelType eq 2}">selected="selected"</c:if>>two stars</option>
                                <option value="1" <c:if test="${param.hotelType eq 1}">selected="selected"</c:if>>one star</option>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="mb-3 w-75">
                    <label for="tour-hot" class="col-sm-2 control-label">Make Tour HOT!</label>
                    <input class="form-check-input" type="checkbox" id="tour-hot" name="tourHot" value="1"
                        <c:if test="${param.tourHot eq 1}">checked </c:if>>
                </div>


                <div class="mb-3">
                    <button type="submit" class="btn btn-primary">Add Tour</button>
                </div>
            </form>
        </div>
    </body>
</html>

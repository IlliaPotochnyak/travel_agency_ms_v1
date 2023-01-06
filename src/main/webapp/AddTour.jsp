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
                <h2>Add New Tour</h2>
            </div>
            <div class="container">
                ${errorAddTour}
            </div>
            <hr>
            <div class="container bg-secondary  mt-5">
                <form action="AddTourServlet" method="post">
                    <div class="mb-3">
                        <label class="form-label">Tour Name </label>
                        <input type="text" class="form-control" name="tourName" required maxlength="64">
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Description </label>
                        <textarea name="tourDescription" class="form-control" maxlength="65500" rows="5"
                            placeholder="Maximum length - 65500 characters..."></textarea>
                    </div>
                    <div class="mb-3">
                        <label class="form-label ">Persons Number </label>
                        <input type="number" class="form-control w-25" name="PersonNumber" required maxlength="3"
                            min="1" max="999">
                    </div>
                    <div class="mb-3">
                        <label class="form-label ">Price </label>
                        <input type="number" class="form-control w-25" name="tourPrice" required maxlength="7"
                            min="1" max="9999999">
                    </div>
                    <div class="mb-3">
                        <label for="tour-type" class="col-sm-2 control-label">Tour type</label>
                        <select id="tour-type" class="form-control w-25" name="tourType">
                            <option value="rest">rest</option>
                            <option value="excursion">excursion</option>
                            <option value="shopping">shopping</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="hotel-type" class="col-sm-2 control-label">Hotel type</label>
                        <select id="hotel-type" class="form-control w-25" name="hotelType">
                            <option value="5">five stars</option>
                            <option value="4">four stars</option>
                            <option value="3" selected="selected">three stars</option>
                            <option value="2">two stars</option>
                            <option value="1">one star</option>
                        </select>
                    </div>
                    <div class="mb-3 w-75">
                        <label for="tour-hot" class="col-sm-2 control-label">Make Tour HOT!</label>
                        <input class="form-check-input" type="checkbox" id="tour-hot" name="tourHot" value="1">
                    </div>
                    <div class="mb-3">
                        <button type="submit" class="btn btn-primary">Add Tour</button>
                    </div>

                </form>
            </div>
            <hr>

        </body>
</html>

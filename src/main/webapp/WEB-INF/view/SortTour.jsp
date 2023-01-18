<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
    isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="languages/messages"/>
<html lang="${sessionScope.lang}">

<html>
    <body>
        <hr>
        <div class="container">
            <button data-bs-toggle="collapse" data-bs-target="#sortTour" class="btn btn-primary"><fmt:message key="sortTour.button" /></button>
            <div id="sortTour" class="collapse">
                <form action="index.jsp" method="get">

                    <div class="col p-2 bg-primary text-white">
                        <label for="tour-type" class="col-sm-2 control-label"><fmt:message key="sortTour.tourType" /></label>
                        <select id="tour-type" name="tour_type">
                            <option value="rest"><fmt:message key="sortTour.rest" /></option>
                            <option value="excursion"><fmt:message key="sortTour.excursion" /></option>
                            <option value="shopping"><fmt:message key="sortTour.shopping" /></option>
                        </select>
                    </div>
                    <div class="col p-2 bg-primary text-white">
                        <label for="price" class="col-sm-2 control-label"><fmt:message key="sortTour.priceLabel" /></label>
                        <input id="price" value="1000000000" type="text" name="price">
                    </div>
                    <div class="col p-2 bg-primary text-white">
                        <label for="people-amount" class="col-sm-2 control-label"><fmt:message key="sortTour.peopleAmount" /></label>
                        <input id="people-amount" value="2" type="text" name="people_amount">
                    </div>
                    <div class="col p-2 bg-primary text-white">
                        <label for="hotel-stars" class="col-sm-2 control-label"><fmt:message key="sortTour.hotelStars" /></label>
                        <select id="hotel-stars" name="hotel_stars">
                            <option value="5">5</option>
                            <option value="4">4</option>
                            <option value="3" selected="selected">3</option>
                            <option value="2">2</option>
                            <option value="1">1</option>
                        </select>
                    </div> <br>
                    <button type="submit" class="btn btn-primary"><fmt:message key="sortTour.sortButton" /></button>
                    <a href="index.jsp"><button type="button" class="btn btn-warning"><fmt:message key="sortTour.resetButton" /></button></a>

                </form>

            </div>
        </div>
        <hr>
    </body>
</html>



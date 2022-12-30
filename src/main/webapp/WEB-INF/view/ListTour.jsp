<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
    isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <body>
        List of tours!!!
        <hr>
        <form action="index.jsp" method="get">
            <label for="tour-type" class="col-sm-4 control-label">Tour type</label>
                <select id="tour-type" name="tour_type">
                    <option value="1">rest</option>
                    <option value="2">excursion</option>
                    <option value="3">shopping</option>
                </select>
                <label for="price" class="col-sm-4 control-label">Price is not more than</label>
                    <input id="price" value="10000000000" type="text" name="price"> dollars per person
                <label for="people-amount" class="col-sm-4 control-label">People amount</label>
                    <input id="people-amount" value="1" type="text" name="people_amount">
                <label for="hotel-stars" class="col-sm-4 control-label">Hotel stars</label>
                    <select id="hotel-stars" name="hotel_stars">
                        <option value="5">five</option>
                        <option value="4">four</option>
                        <option value="3">three</option>
                        <option value="2">two</option>
                        <option value="1">one</option>
                    </select>

            <button type="submit">Sort</button>
        </form>
        <hr>
        <c:forEach var="tour" items="${tourList}">

                    <c:out value="${tour.name}"/>
                    <hr>
        </c:forEach>
        <hr>

    </body>
</html>

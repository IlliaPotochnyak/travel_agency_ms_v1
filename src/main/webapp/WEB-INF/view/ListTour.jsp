<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
    isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <body>
        List of tours!!!
        <hr>
        <c:forEach var="tour" items="${tourList}">

                    <c:out value="${tour.name}"/>
                    <hr>
        </c:forEach>
        <hr>

    </body>
</html>

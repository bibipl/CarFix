<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: bibipl
  Date: 31.10.18
  Time: 23:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>USUŃ</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<%@ include file="../header.jspf" %>
<%@ include file="dateFormat.jspf" %>

<table class="table">
    <thead>
        <tr>
            <th scope="col">POZYCJA</th>
            <th scope="col">DANE ZLECENIA</th>
        </tr>
    </thead>
    <tr><td>ID</td><td>${orders.id}</td></tr>
    <tr><td>SAMOCHÓD</td><td>${cars.model} | ${cars.brand} | ${cars.registration}</td></tr>
    <tr><td>PRACOWNIK</td><td>${employees.name} | ${employees.surname}</td></tr>
    <tr>
        <td>STATUS NAPRAWY</td>
        <c:choose>
            <c:when test="${orders.status==1}">
                <td>| PRZYJĘTY |</td>
            </c:when>
            <c:when test="${orders.status==2}">
                <td>| ZATWIERDZONY |</td>
            </c:when>
            <c:when test="${orders.status==3}">
                <td>| NAPRAWIANY |</td>
            </c:when>
            <c:when test="${orders.status==4}">
                <td>| GOTOWY |</td>
            </c:when>
            <c:otherwise>
                <td>| REZYGNACJA |</td>
            </c:otherwise>
        </c:choose>
    </tr>
    <tr><td>OPIS PROBLEMU</td><td>${orders.problemDescript}</td></tr>
    <c:if test="${not empty orders.planStartDate}">
        <tr><td>PLANOWANE ROZPOCZĘCIE NAPRAWY</td><td>${newParsedPlanDate}</td></tr>
    </c:if>
    <c:if test="${empty orders.planStartDate}">
        <tr><td>PLANOWANE ROZPOCZĘCIE NAPRAWY</td><td>Brak</td></tr>
    </c:if>
    <c:if test="${not empty orders.realStartDate}">
        <tr><td>DATA ROZPOCZĘCIA NAPRAWY</td><td>${newParsedRealDate}</td></tr>
    </c:if>
    <c:if test="${empty orders.realStartDate}">
        <tr><td>DATA ROZPOCZĘCIA NAPRAWY</td><td>Brak</td></tr>
    </c:if>
    <c:if test="${not empty orders.realEndDate}">
        <tr><td>DATA ZAKOŃCZENIA NAPRAWY</td><td>${newParsedEndDate}</td></tr>
    </c:if>
    <c:if test="${empty orders.realEndDate}">
        <tr><td>DATA ZAKOŃCZENIA NAPRAWY</td><td>Brak</td></tr>
    </c:if>
    <tr><td>OPIS NAPRAWY</td><td>${orders.fixDescript}</td></tr>
    <tr><td>KOSZT NAPRAWY</td><td>${orders.valueServ}</td></tr>
    <tr><td>KOSZT CZĘŚCI</td><td>${orders.valueParts}</td></tr>
    <tr><td>STAWKA GODZINOWA</td><td>${orders.hourPrice}</td></tr>
    <tr><td>LICZBA GODZIN</td><td>${orders.numOfHours}</td></tr>
    <tr><td><b>NA PEWNO USUNĄĆ ZLECENIE ?</b></td><td><a href="OrderControl?opt=6&ident=${orders.id}">| TAK |</a><a href="OrderControl?opt=1"> NIE |</a></td></tr>
    <tr><td></td><td></td></tr>
</table>
<table>
    <td>
        <a href="OrderControl?opt=1">| POWRÓT ZLECENIA | </a>
        <a href="OrderControl?opt=4&ident=${orders.id}">MODYFIKUJ ZLECENIE | </a>
    </td>
</table>

<%@ include file="../footer.jspf" %>
</body>
</html>

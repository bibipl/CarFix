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
    <title>Szczegóły zlecenia</title>
</head>
<body>
<%@ include file="../header.jspf" %>

<c:if test="${not empty orders.planStartDate}">
    <fmt:parseDate value="${orders.planStartDate}" pattern="yyyy-MM-dd" var="parsedStartDate" type="date" />
    <fmt:formatDate value="${parsedStartDate}" var="newParsedPlanDate" type="date" pattern="dd.MM.yyyy" />
</c:if>
<c:if test="${not empty orders.realStartDate}">
    <fmt:parseDate value="${orders.realStartDate}" pattern="yyyy-MM-dd" var="parsedRealDate" type="date" />
    <fmt:formatDate value="${parsedRealDate}" var="newParsedRealDate" type="date" pattern="dd.MM.yyyy" />
</c:if>
<table>
    <tr><td>ID</td><td>${orders.id}</td></tr>
    <tr><td>Samochód</td><td>${cars.model} | ${cars.brand} | ${cars.registration}</td></tr>
    <tr><td>Pracownik</td><td>${employees.name} | ${employees.surname}</td></tr>
    <c:choose>
        <c:when test="${orders.status==1}">
            <tr><td>Status naprawy</td><td>| Przyjęty |</td></tr>
        </c:when>
        <c:when test="${orders.status==2}">
            <tr><td>Status naprawy</td><td>| Zatwierdzony |</td></tr>
        </c:when>
        <c:when test="${orders.status==3}">
            <tr><td>Status naprawy</td><td>| Naprawiany |</td></tr>
        </c:when>
        <c:when test="${orders.status==4}">
            <tr><td>Status naprawy</td><td>| Gotowy |</td></tr>
        </c:when>
        <c:otherwise>
            <tr><td>Status naprawy</td><td>| Rezygnacja |</td></tr>
        </c:otherwise>
    </c:choose>
    <tr><td>Opis problemu</td><td>${orders.problemDescript}</td></tr>
    <c:if test="${not empty orders.planStartDate}">
        <tr><td>Planowane rozpoczęcie naprawy</td><td>${newParsedPlanDate}</td></tr>
    </c:if>
    <c:if test="${empty orders.planStartDate}">
        <tr><td>Planowane rozpoczęcie naprawy</td><td>Brak</td></tr>
    </c:if>
    <c:if test="${not empty orders.realStartDate}">
        <tr><td>Data rozpoczęcia naprawy</td><td>${newParsedRealDate}</td></tr>
    </c:if>
    <c:if test="${empty orders.realStartDate}">
        <tr><td>Data rozpoczęcia naprawy</td><td>Brak</td></tr>
    </c:if>
    <tr><td>Opis naprawy</td><td>${orders.fixDescript}</td></tr>
    <tr><td>Koszt naprawy</td><td>${orders.valueServ}</td></tr>
    <tr><td>Koszt części</td><td>${orders.valueParts}</td></tr>
    <tr><td>Stawka godzinowa</td><td>${orders.hourPrice}</td></tr>
    <tr><td>Liczba godzin</td><td>${orders.numOfHours}</td></tr>
</table>
<table>
    <thead>
    <td><a href="OrderControl?opt=1">| Powrót do listy ZLECEŃ | </a></td>
    <td><a href="OrderControl?opt=4&ident=${orders.id}">Modyfikuj | </a></td>
    <td><a href="OrderControl?opt=5&ident=${orders.id}">Usuń |</a></td>
    </thead>
</table>

<%@ include file="../footer.jspf" %>
</body>
</html>

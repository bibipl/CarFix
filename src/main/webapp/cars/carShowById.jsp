<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: bibipl
  Date: 31.10.18
  Time: 10:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Szczegóły samochodu</title>
</head>
<body>
<%@ include file="../header.jspf" %>
<fmt:parseDate value="${cars.nextReview}" pattern="yyyy-MM-dd" var="parsedDate" type="date" />
<fmt:formatDate value="${parsedDate}" var="newParsedDate" type="date" pattern="dd.MM.yyyy" />
<table>
    <thead></thead>
    <tr><td>Model: </td><td>${cars.model}</td></tr>
    <tr><td>Marka: </td><td>${cars.brand}</td></tr>
    <tr><td>Rok produkcji: </td><td>${cars.yearProd}</td></tr>
    <tr><td>nr Rejestracyjny: </td><td>${cars.registration}</td></tr>
    <tr><td>Następny przegląd: </td><td>${newParsedDate}</td></tr>
    <tr><td>Właściciel: </td><td>${cl.name} ${cl.surname}</td></tr>
</table>

<table>
    <thead>
    <td><a href="CarControl?opt=8&ident=${cars.id}">| Historia | </a></td>
    <td><a href="CarControl?opt=4&ident=${cars.id}"> Modyfikuj | </a></td>
    <td><a href="CarControl?opt=5&ident=${cars.id}">Usuń |</a></td>
    <td><a href="CarControl?opt=1"> Powrót do listy Samochodów | </a></td>
    </thead>
</table>

<%@ include file="../footer.jspf" %>
</body>
</html>

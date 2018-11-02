<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: bibipl
  Date: 30.10.18
  Time: 23:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Samochody</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<%@ include file="../header.jspf" %>
<table class="table">
    <thead>
    <tr>
        <th scope="col"><a href="CarControl?opt=2">| + |</a></th>
        <th scope="col">MODEL</th>
        <th scope="col">MARKA</th>
        <th scope="col">REJESTRACJA</th>
        <th scope="col">PRZEGLĄD</th>
        <th scope="col">WŁAŚCICIEL</th>
        <th scope="col">SZCZEGÓŁY</th>
        <th scope="col">HISTORIA</th>
        <th scope="col">MODYFIKUJ</th>
        <th scope="col">USUŃ</th>
    </tr>
    </thead>
    <c:forEach items="${orClEmCa}" var="item">
        <fmt:parseDate value="${item.car.nextReview}" pattern="yyyy-MM-dd" var="parsedDate" type="date" />
        <fmt:formatDate value="${parsedDate}" var="newParsedDate" type="date" pattern="dd.MM.yyyy" />
        <tr>
            <td>${item.car.id}</td>
            <td>${item.car.model}</td>
            <td>${item.car.brand}</td>
            <td>${item.car.registration}</td>
            <td>${newParsedDate}</td>
            <td>${item.client.name} ${item.client.surname}</td>
            <td><a href="CarControl?opt=3&ident=${item.car.id}">| S |</a></td>
            <td><a href="CarControl?opt=3&ident=${item.car.id}">| H |</a></td> // tu zmienić jak zrobię historia.jsp
            <td><a href="CarControl?opt=4&ident=${item.car.id}">| M |</a></td>
            <td><a href="CarControl?opt=5&ident=${item.car.id}">| U |</a></td>
        </tr>
    </c:forEach>

</table>
<%@ include file="../footer.jspf" %>
</body>
</html>

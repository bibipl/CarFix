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
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

</head>
<body>
<%@ include file="../header.jspf" %>
<c:if test="${not empty cars.nextReview}">
    <fmt:parseDate value="${cars.nextReview}" pattern="yyyy-MM-dd" var="parsedDate" type="date" />
    <fmt:formatDate value="${parsedDate}" var="newParsedDate" type="date" pattern="dd.MM.yyyy" />
</c:if>
<table class="table">
    <thead>
        <tr>
            <th scope="col">OPIS SAMOCHODU</th>
            <th scope="col">DANE SAMOCHODU</th>
        </tr>
    </thead>
    <tr><td>MODEL</td><td>${cars.model}</td></tr>
    <tr><td>MARKA</td><td>${cars.brand}</td></tr>
    <tr><td>ROK PRODUKCJI</td><td>${cars.yearProd}</td></tr>
    <tr><td>NUMER REJESTRACYJNY</td><td>${cars.registration}</td></tr>
    <tr><td>DATA NASTĘPNEGO PRZEGLĄDU</td><td>${newParsedDate}</td></tr>
    <tr><td>WŁAŚCICIEL</td><td>${cl.name} ${cl.surname}</td></tr>

    <tr>
        <td>
            <a href="CarControl?opt=8&ident=${cars.id}">| HISTORIA | </a>
            <a href="CarControl?opt=7&ident=${cars.ownerId}">SAMOCHODY KLIENTA | </a>
            <a href="CarControl?opt=1">SAMOCHODY |</a>
        </td>
        <td><a href="CarControl?opt=4&ident=${cars.id}">| MODYFIKUJ | </a><a href="CarControl?opt=5&ident=${cars.id}">USUŃ |</a></td>
    </tr>
</table>

<%@ include file="../footer.jspf" %>
</body>
</html>

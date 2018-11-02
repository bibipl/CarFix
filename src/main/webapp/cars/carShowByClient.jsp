<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: bibipl
  Date: 01.11.18
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${cl.name} ${cl.surname}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<%@ include file="../header.jspf" %>
<table>
    <thead>
        <th scope="col">| ID: ${cl.id} | </th>
        <th scope="col"> IMIĘ: ${cl.name} | </th>
        <th scope="col"> NAZWISKO: ${cl.surname} |</th>
    </thead>
</table>
<table class="table">
    <thead>
    <tr>
        <th scope="col"><a href="CarControl?opt=2&ident=${cl.id}">| + |</a></th>
        <th scope="col">MODEL</th>
        <th scope="col">MARKA</th>
        <th scope="col">REJESTRACJA</th>
        <th scope="col">PRZEGLĄD</th>
        <th scope="col">SZCZEGÓŁY</th>
        <th scope="col">HISTORIA</th>
        <th scope="col">MODYFIKUJ</th>
        <th scope="col">USUŃ</th>
    </tr>
    </thead>
    <c:forEach items="${cars}" var="item">
        <fmt:parseDate value="${item.nextReview}" pattern="yyyy-MM-dd" var="parsedDate" type="date" />
        <fmt:formatDate value="${parsedDate}" var="newParsedDate" type="date" pattern="dd.MM.yyyy" />
        <tr>
            <td>${item.id}</td>
            <td>${item.model}</td>
            <td>${item.brand}</td>
            <td>${item.registration}</td>
            <td>${newParsedDate}</td>
            <td><a href="CarControl?opt=3&ident=${item.id}">| S |</a></td>
            <td><a href="CarControl?opt=3&ident=${item.id}">| H |</a></td> // tu zmienić jak zrobię historia.jsp
            <td><a href="CarControl?opt=4&ident=${item.id}">| M |</a></td>
            <td><a href="CarControl?opt=5&ident=${item.id}">| U |</a></td>
        </tr>
    </c:forEach>
</table>
<table>
    <thead></thead>
    <tr>
        <td><a href="ClientControl?opt=1">Powrót do listy Klientów</a></td>
    </tr>
</table>
<%@ include file="../footer.jspf" %>
</body>
</html>

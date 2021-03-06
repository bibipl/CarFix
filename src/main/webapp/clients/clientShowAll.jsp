<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: bibipl
  Date: 30.10.18
  Time: 13:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Klienci</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<%@ include file="../headerSrc.jspf" %>
<table class="table">
    <thead>
    <tr>
        <th scope="col"><a href="ClientControl?opt=2">| + |</a></th>
        <th scope="col">NAZWISKO</th>
        <th scope="col">IMIĘ</th>
        <th scope="col">URODZINY</th>
        <th scope="col">TELEFON</th>

        <th scope="col">DANE</th>
        <th scope="col">SAMOCHODY</th>
        <th scope="col">MODYFIKUJ</th>
        <th scope="col">USUŃ</th>
    </tr>
    </thead>
    <c:forEach items="${cl}" var="item">
        <fmt:parseDate value="${item.birthDate}" pattern="yyyy-MM-dd" var="parsedDate" type="date" />
        <fmt:formatDate value="${parsedDate}" var="newParsedDate" type="date" pattern="dd.MM.yyyy" />
        <tr>
            <td>${item.id}</td>
            <td>${item.surname}</td>
            <td>${item.name}</td>
            <td>${newParsedDate}</td>
            <td>${item.phone}</td>
            <td><a href="ClientControl?opt=3&ident=${item.id}">| D |</a></td>
            <td><a href="ClientControl?opt=7&ident=${item.id}">| S |</a></td>
            <td><a href="ClientControl?opt=4&ident=${item.id}">| M |</a></td>
            <td><a href="ClientControl?opt=5&ident=${item.id}">| U |</a></td>
        </tr>
    </c:forEach>
    <tr><td><a href="ClientControl?opt=1">| POWRÓT KLIENCI |</a></td></tr>
</table>
<%@ include file="../footer.jspf" %>
</body>
</html>

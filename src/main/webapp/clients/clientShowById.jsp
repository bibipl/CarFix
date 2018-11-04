<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: bibipl
  Date: 30.10.18
  Time: 18:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Klient-Szczegóły</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<%@ include file="../header.jspf" %>
<c:if test="${not empty cl.birthDate}">
    <fmt:parseDate value="${cl.birthDate}" pattern="yyyy-MM-dd" var="parsedDate" type="date" />
    <fmt:formatDate value="${parsedDate}" var="newParsedDate" type="date" pattern="dd.MM.yyyy" />
</c:if>
<table class="table">
    <thead>
    <tr>
        <th scope="col">POZYCJA</th>
        <th scope="col">DANE KLIENTA</th>
    </tr>
    </thead>
    <tr><td>NUMER</td><td>${cl.id}</td></tr>
    <tr><td>IMIĘ</td><td>${cl.name}</td></tr>
    <tr><td>NAZWISKO</td><td>${cl.surname}</td></tr>
    <tr><td>DATA URODZENIA</td><td>${newParsedDate}</td></tr>
    <tr><td>TELEFON</td><td>${cl.phone}</td></tr>

    <tr>
        <td>
            <a href="ClientControl?opt=1">| POWRÓT KLIENCI | </a>
            <a href="ClientControl?opt=4&ident=${cl.id}">MODYFIKACJA KLIENTA | </a>
            <a href="ClientControl?opt=5&ident=${cl.id}">USUŃ KLIENTA |</a>
        </td>
        <td>
            <a href="CarControl?opt=2&ident=${cl.id}">| DODAJ SAMOCHÓD | </a>
            <a href="ClientControl?opt=1">LISTA ZLECEŃ | </a>
            <a href="CarControl?opt=7&ident=${cl.id}">LISTA SAMOCHODÓW | </a>
        </td>
    </tr>
</table>
<%@ include file="../footer.jspf" %>
</body>
</html>

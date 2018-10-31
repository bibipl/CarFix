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
</head>
<body>
<%@ include file="header.jspf" %>
<fmt:parseDate value="${cl.birthDate}" pattern="yyyy-MM-dd" var="parsedDate" type="date" />
<fmt:formatDate value="${parsedDate}" var="newParsedDate" type="date" pattern="dd.MM.yyyy" />
<table>
    <thead></thead>
    <tr><td>Numer: </td><td>${cl.id}</td></tr>
    <tr><td>Imię: </td><td>${cl.name}</td></tr>
    <tr><td>Nazwisko: </td><td>${cl.surname}</td></tr>
    <tr><td>Data urodzin: </td><td>${newParsedDate}</td></tr>
    <tr><td>Telefon: </td><td>${cl.phone}</td></tr>
</table>
<table>
    <thead></thead>
    <tr>
        <td> <a href="CarControl?opt=2&ident=${cl.id}">Dodaj Samochód | </a>  </td>
        <td> <a href="ClientControl?opt=1">Lista zleceń | </a>  </td>
        <td> <a href="CarControl?opt=7&ident=${cl.id}">Lista samochodów | </a></td>
        <td> <a href="ClientControl?opt=1">Powrót do listy klientów | </a>  </td>
    </tr>

</table>

<%@ include file="footer.jspf" %>
</body>
</html>

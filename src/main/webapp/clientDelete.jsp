<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: bibipl
  Date: 30.10.18
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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
    <tr><td>Data Urodzenia: </td><td>${newParsedDate}</td></tr>
    <tr><td>Telefon: </td><td>${cl.phone}</td></tr>
</table>
<h4> Na pewno chcesz usunąć tego klienta z bazy danych ?</h4>
<h4> Usunięcie będzie możliwe, jeżeli nie posiada on żadnego samochodu ?</h4>
<h4> Jeżeli posiada to najpierw usuń lub zmień przynależność smochodów tego klienta</h4>
<table>
    <thead>
    <td><a href="ClientControl?opt=6&ident=${cl.id}">TAK</a></td>
    <td><a href="ClientControl?opt=1">NIE</a></td>
    </thead>
</table>
<br>
<table>
    <th></th>
    <tr><td><a href="ClientControl?opt=1">| Powrót do listy klientów |</a></td></tr>
</table>
<%@ include file="footer.jspf" %>
</body>
</html>

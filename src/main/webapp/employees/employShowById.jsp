<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%--
  Created by IntelliJ IDEA.
  User: bibipl
  Date: 29.10.18
  Time: 23:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ include file="../header.jspf" %>
<table>
    <thead></thead>
    <tr><td>Numer :</td><td>${empl.id}</td></tr>
    <tr><td>Imię :</td><td>${empl.name}</td></tr>
    <tr><td>Nazwisko :</td><td>${empl.surname}</td></tr>
    <tr><td>Adres :</td><td>${empl.address}</td></tr>
    <tr><td>Telefon :</td><td>${empl.phone}</td></tr>
    <tr><td>Uwagi :</td><td>${empl.note}</td></tr>
    <tr><td>Stawka godzinowa :</td><td>${empl.hourPrice}</td></tr>
</table>
<br>
<table>
    <th></th>
    <tr><td><a href="EmployeeControl?opt=1">Powrót do listy Serwisantów</a></td></tr>
</table>
<%@ include file="../footer.jspf" %>
</body>
</html>

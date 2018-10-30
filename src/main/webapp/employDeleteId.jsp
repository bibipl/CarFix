<%--
  Created by IntelliJ IDEA.
  User: bibipl
  Date: 30.10.18
  Time: 00:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DELETE</title>
</head>
<body>
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
<h2> Na pewno chcesz usunąć tego pracownika z bazy danych ?</h2>
<table>
    <thead>
    <td><a href="EmployeeControl?opt=6&ident=${empl.id}">TAK</a></td>
    <td><a href="EmployeeControl?opt=1">NIE</a></td>
    </thead>
</table>

</body>
</html>

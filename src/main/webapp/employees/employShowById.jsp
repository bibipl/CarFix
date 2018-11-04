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
    <title>SERWISANT</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<%@ include file="../header.jspf" %>
<table class="table">
    <thead>
    <tr>
        <th scope="col">POZYCJA</th>
        <th scope="col">DANE SERWISANTA</th>
    </tr>
    </thead>
    <tr><td>NUMER</td><td>${empl.id}</td></tr>
    <tr><td>IMIĘ</td><td>${empl.name}</td></tr>
    <tr><td>NAZWISKO</td><td>${empl.surname}</td></tr>
    <tr><td>ADRES</td><td>${empl.address}</td></tr>
    <tr><td>TELEFON</td><td>${empl.phone}</td></tr>
    <tr><td>UWAGI</td><td>${empl.note}</td></tr>
    <tr><td>STAWKA GODZINOWA</td><td>${empl.hourPrice}</td></tr>
    <br>
    <tr>
        <td><a href="EmployeeControl?opt=7&ident=${empl.id}">| POWRÓT ZLECENIA SERWISANTA |</a></td>
        <td><a href="EmployeeControl?opt=1">| POWRÓT SERWISANCI | </a><a href="OrderControl?opt=1">POWRÓT ZLECENIA |</a></td>
    </tr>
</table>
<%@ include file="../footer.jspf" %>
</body>
</html>

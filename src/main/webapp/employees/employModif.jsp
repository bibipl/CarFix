<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: bibipl
  Date: 30.10.18
  Time: 01:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MODYFIKUJ</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<%@ include file="../header.jspf" %>
<table>
    <th></th>
    <tr><td>ID : ${empl.id}</td></tr>
</table>
<table class="table">
    <thead>
    <tr>
        <th scope="col">POZYCJA</th>
        <th scope="col">DANE SERWISANTA</th>
    </tr>
    </thead>
    <form method="post" action="EmployeeControl">
        <input type="hidden" id="empId" name="empId" value=${empl.id}>

        <tr><td>IMIĘ</td><td><input type="text" name="name" value="${empl.name}"></td></tr>
        <tr><td>NAZWISKO</td><td><input type="text" name="surname" value="${empl.surname}"></td></tr>
        <tr><td>ADRES</td><td><input type="text" name="address" value="${empl.address}"></td></tr>
        <tr><td>NUMER TELEFONU</td><td><input type="text" name="phone" value="${empl.phone}"></td></tr>
        <tr><td>UWAGI</td><td><input type="text" name="note" value="${empl.note}"></td></tr>
        <tr><td>STAWKA GODZINOWA</td><td><input type="number" name="hourPrice" step="0.01" value="${empl.hourPrice}"></td></tr>
        <tr>
            <td><a href="EmployeeControl?opt=1">| POWRÓT DO LISTY SERWISANTÓW | </a></td>
            <td><input type="submit" value="MODYFIKUJ NOWEGO PRACOWNIKA"></td>
        </tr>

    </form>
</table>

<%@ include file="../footer.jspf" %>
</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: bibipl
  Date: 30.10.18
  Time: 23:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dodaj samochód</title>
</head>
<body>
<%@ include file="header.jspf" %>
<form method="post" action="CarControl">
    <input type="hidden" id="carId" name="carId" value=${car.id}>
    <label>Marka</label><input type="text" name="model"><br>
    <label>Model</label><input type="text" name="brand"><br>
    <label>Rok produkcji</label><input type="number" name="yearProd"><br>
    <label>Nr Rejestracyjny</label><input type="text" name="registration"><br>
    <label>Data następnego przeglądu</label><input type="date" name="nextReview"><br>
    <input type="hidden" id="ownerId" name="ownerId" value=${clId}>
    <input type="submit" value="Dodaj"><br>
</form>
<table>
    <thead>
        <td><a href="CarControl?opt=1">| Powrót do listy samochodów | </a></td>
        <td><a href="CarControl?opt=3&ident=${item.id}">Szczegóły | </a></td>
        <td><a href="CarControl?opt=4&ident=${item.id}">Modyfikuj | </a></td>
        <td><a href="CarControl?opt=5&ident=${item.id}">Usuń |</a></td>
    </thead>
</table>
<%@ include file="footer.jspf" %>
</body>
</html>

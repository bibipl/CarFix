<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: bibipl
  Date: 31.10.18
  Time: 18:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dodaj Zlecenie</title>
</head>
<body>
<%@ include file="../header.jspf" %>
<form method="post" action="OrderControl">
    <input type="hidden" name="ordId" value=${0}>
    <label>Identyfikator samochodu</label><input type="number" name="carId"><br>
    <label>Opis Problemu</label><input type="text" name="problemDescript"><br>
    <label>Planowane rozpoczęcie naprawy</label><input type="date" name="planStartDate"><br>
    <label>Pracownik</label><input type="number" name="employeeId"><br>
    <label for="status">Status</label>
    <select id="status" name="status" value="1">
        <option selected value="1">Przyjęty</option>
        <option value="2">Zatwierdzony koszt naprawy</option>
        <option value="3">W naprawie</option>
        <option value="4">Gotowy</option>
        <option value="5">Rezygnacja</option>
    </select>
    <input type="submit" value="Dodaj"><br>
</form>
<table>
    <thead>
    <td><a href="OrderControl?opt=1">| Powrót do listy zleceń | </a></td>
    <td><a href="OrderControl?opt=3&ident=${item.id}">Szczegóły | </a></td>
    <td><a href="OrderControl?opt=4&ident=${item.id}">Modyfikuj | </a></td>
    <td><a href="OrderControl?opt=5&ident=${item.id}">Usuń |</a></td>
    </thead>
</table>
<%@ include file="../footer.jspf" %>
</body>
</html>

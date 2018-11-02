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
<%@ include file="../header.jspf" %>
<c:if test="${cl.id!=0}">
    <table>
        <thead>
        <th scope="col">| ID: ${cl.id} | </th>
        <th scope="col"> Imię: ${cl.name} | </th>
        <th scope="col"> Nazwisko: ${cl.surname} |</th>
        </thead>
    </table>
</c:if>

<form method="post" action="CarControl">
    <input type="hidden" name="carId" value=""${carId}"">
    <label>Marka</label><input type="text" name="model"><br>
    <label>Model</label><input type="text" name="brand"><br>
    <label>Rok produkcji</label><input type="number" name="yearProd"><br>
    <label>Nr Rejestracyjny</label><input type="text" name="registration"><br>
    <label>Data następnego przeglądu</label><input type="date" name="nextReview"><br>
    <input type="hidden" name="ownerId" value="${cl.id}">
    <input type="submit" value="Dodaj"><br>
</form>
<table>
    <thead>
        <td><a href="ClientControl?opt=7&ident=${cl.id}">| Powrót do listy Samochodów | </a></td>
        <td><a href="CarControl?opt=3&ident=${car.id}">Szczegóły | </a></td>
        <td><a href="CarControl?opt=4&ident=${car.id}">Modyfikuj | </a></td>
        <td><a href="CarControl?opt=5&ident=${car.id}">Usuń |</a></td>
    </thead>
</table>
<%@ include file="../footer.jspf" %>
</body>
</html>

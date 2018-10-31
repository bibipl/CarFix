<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: bibipl
  Date: 31.10.18
  Time: 09:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Modyfikuj dane samochodu</title>
</head>
<body>
<%@ include file="header.jspf" %>
<fmt:parseDate value="${cars.nextReview}" pattern="yyyy-MM-dd" var="parsedDate" type="date" />
<fmt:formatDate value="${parsedDate}" var="newParsedDate" type="date" pattern="dd.MM.yyyy" />
<form method="post" action="CarControl">
    <input type="hidden" name="carId" value=${cars.id}>
    <label>Marka: </label><input type="text" name="model" value="${cars.model}"><br>
    <label>Model: </label><input type="text" name="brand" value="${cars.brand}"><br>
    <label>Rok produkcji: </label><input type="number" name="yearProd" value="${cars.yearProd}"><br>
    <label>Nr Rejestracyjny: </label><input type="text" name="registration" value="${cars.registration}"><br>
    <label>Data następnego przeglądu: </label><input type="date" name="nextReview" value="${cars.nextReview}"><br>
    <label>Właściciel: </label><input type="number" name="ownerId" value="${cars.ownerId}"><br>
    <input type="submit" value="Zmień"><br>
    <table>
        <thead></thead>
        <tr>
            <td><a href="CarControl?opt=1">Powrót do listy samochodów</a></td>
            <td><a href="CarControl?opt=5&ident=${cars.id}">Usuń</a></td>
        </tr>
    </table>

</form>



<%@ include file="footer.jspf" %>
</body>
</html>

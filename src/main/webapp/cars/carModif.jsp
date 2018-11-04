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
<%@ include file="../header.jspf" %>
<fmt:parseDate value="${cars.nextReview}" pattern="yyyy-MM-dd" var="parsedDate" type="date" />
<fmt:formatDate value="${parsedDate}" var="newParsedDate" type="date" pattern="dd.MM.yyyy" />
<table class="table">
    <thead>
    <tr>
        <th scope="col">OPIS SAMOCHODU</th>
        <th scope="col">DANE SAMOCHODU</th>
    </tr>
    </thead>
  <form method="post" action="CarControl">
    <input type="hidden" name="carId" value=${cars.id}>
    <tr><td>MARKA</td><td><input type="text" name="model" value="${cars.model}"></td></tr>
    <tr><td>MODEL</td><td><input type="text" name="brand" value="${cars.brand}"></td></tr>
    <tr><td>ROK PRODUKCJI</td><td><input type="number" name="yearProd" value="${cars.yearProd}"></td></tr>
    <tr><td>NUMER REJESTRACYJNY</td><td><input type="text" name="registration" value="${cars.registration}"></td></tr>
    <tr><td>DATA NASTĘPNEGO PRZEGLĄDU</td><td><input type="date" name="nextReview" value="${cars.nextReview}"</td></tr>
    <tr>
        <td>WŁAŚCICIEL</td>
        <td>
            <select id="clent" name="ownerId">
                <c:forEach items="${clients}" var="item">
                    <c:if test="${cars.ownerId==item.id}">
                        <option selected value="${item.id}">${item.name} ${item.surname}</option>
                    </c:if>
                    <c:if test="${cars.ownerId!=item.id}">
                        <option value="${item.id}">${item.name} ${item.surname}</option>
                    </c:if>
                </c:forEach>
            </select>
        </td>
    </tr>
    <tr>
        <td><a href="CarControl?opt=1">| POWRÓT SAMOCHODY | </a>
        <a href="CarControl?opt=7&ident=${cars.ownerId}">POWRÓT SAMOCHODY KLIENTA | </a>
        <a href="CarControl?opt=5&ident=${cars.id}">USUŃ |</a></td>
        <td><input type="submit" value="Zmień"></td>
    </tr>


  </form>
</table>


<%@ include file="../footer.jspf" %>
</body>
</html>

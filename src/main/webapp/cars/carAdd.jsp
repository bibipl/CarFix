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
    <title>DODAJ SAMOCHÓD</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<%@ include file="../header.jspf" %>
<c:if test="${cl.id!=0}">
    <table>
        <thead>
        <th scope="col">WŁAŚCICIEL: </th>
        <th scope="col">${cl.name} ${cl.surname}</th>
        </thead>
    </table>
</c:if>
<table class="table">
    <thead>
    <tr>
        <th scope="col">OPIS SAMOCHODU</th>
        <th scope="col">DANE SAMOCHODU</th>
    </tr>
    </thead>
  <form method="post" action="CarControl">
    <input type="hidden" name="carId" value=""${carId}"">
      <tr><td>MARKA</td><td><input type="text" name="model"></td></tr>
      <tr><td>MODEL</td><td><input type="text" name="brand"></td></tr>
      <tr><td>ROK PRODUKCJI</td><td><input type="number" name="yearProd"></td></tr>
      <tr><td>NUMER REJESTRACYJNY</td><td><input type="text" name="registration"></td></tr>
      <tr><td>DATA NASTĘPNEGO PRZEGLĄDU</td><td><input type="date" name="nextReview"></td></tr>
    <input type="hidden" name="ownerId" value="${cl.id}">
      <tr>
          <td><a href="CarControl?opt=1">| POWRÓT SAMOCHODY | </a>
              <a href="CarControl?opt=7&ident=${cars.ownerId}">SAMOCHODY KLIENTA | </a>
              <a href="CarControl?opt=5&ident=${cars.id}">USUŃ |</a></td>
          <td><input type="submit" value="DODAJ SAMOCHÓD"></td>
      </tr>
  </form>
<table>

<%@ include file="../footer.jspf" %>
</body>
</html>

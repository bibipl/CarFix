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
  <form method="post" action="OrderControl">
    <input type="hidden" name="ordId" value=${0}>
    <tr>
        <td>SAMOCHÓD</td>
        <td>
            <select id="car" name="carId">
                <c:forEach items="${cars}" var="item">
                    <option value="${item.id}">${item.model} ${item.brand} | ${item.registration}</option>
                </c:forEach>
            </select>
        </td>
    </tr>
    <tr><td>OPIS PROBLEMU</td><td><input type="text" name="problemDescript"></td></tr>
    <tr><td>PLANOWANE ROZPOCZĘCIE NAPRAWY</td><td><input type="date" name="planStartDate"></td></tr>
      <tr>
          <td>SERWISANT</td>
          <td>
              <select id="service" name="employeeId">
                  <c:forEach items="${employees}" var="item">
                      <option selected value="${item.id}">${item.name} ${item.surname}</option>
                  </c:forEach>
              </select>
          </td>
      </tr>
      <tr><td>CENA NAPRAWY</td><td><input type="number" name="valueServ" step="0.01"></td></tr>
      <tr>
          <td>STATUS NAPRAWY</td>
          <td>
              <select id="status" name="status" value="1">
                  <option selected value="1">PRZYJĘTY</option>
                  <option value="2">ZATWIERDZONY KOSZT</option>
                  <option value="3">W NAPRAWIE</option>
              </select>
          </td>
      </tr>
      <tr>
          <td>
              <a href="OrderControl?opt=1">| POWRÓT ZLECENIA | </a>
              <a href="CarControl?opt=2">DODAJ SAMOCHÓD | </a>
          </td>
          <td><input type="submit" value="DODAJ NOWE ZLECENIE"></td>
      </tr>
  </form>
</table>
<%@ include file="../footer.jspf" %>
</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: bibipl
  Date: 31.10.18
  Time: 17:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Zlecenia</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

</head>
<body>
<%@ include file="header.jspf" %>
<table class="table">
    <thead>
    <tr>
        <th scope="col"><a href="OrderControl?opt=2">Dodaj Zlecenie</a></th>
        <th scope="col">Samochód</th>
        <th scope="col">Pracownik</th>
        <th scope="col">Plan</th>
        <th scope="col">Start</th>
        <th scope="col">Status</th>
        <th scope="col">Szczegóły</th>
        <th scope="col">Modyfikuj</th>
        <th scope="col">Usuń</th>
    </tr>
    </thead>
    <c:forEach items="${orders}" var="item">

        <c:if test="${not empty item.planStartDate}">
            <fmt:parseDate value="${item.planStartDate}" pattern="yyyy-MM-dd" var="parsedStartDate" type="date" />
            <fmt:formatDate value="${parsedStartDate}" var="newParsedStartDate" type="date" pattern="dd.MM.yyyy" />
        </c:if>
        <c:if test="${not empty item.realStartDate}">
            <fmt:parseDate value="${item.realStartDate}" pattern="yyyy-MM-dd" var="parsedRealDate" type="date" />
            <fmt:formatDate value="${parsedRealDate}" var="newParsedRealDate" type="date" pattern="dd.MM.yyyy" />
        </c:if>

        <tr>
            <td>${item.id}</td>
            <td>${item.carId}</td>
            <td>${item.employeeId}</td>
            <c:if test="${not empty item.planStartDate}">
                <td>${newParsedStartDate}</td>
            </c:if>
            <c:if test="${empty item.planStartDate}">
                <td>Brak</td>
            </c:if>
            <c:if test="${not empty item.realStartDate}">
                <td>${newParsedRealDate}</td>
            </c:if>
            <c:if test="${empty item.realStartDate}">
                <td>Brak</td>
            </c:if>
            <td>${item.status}</td>
            <td><a href="OrderControl?opt=3&ident=${item.id}">Szczegóły</a></td>
            <td><a href="OrderControl?opt=4&ident=${item.id}">Modyfikuj</a></td>
            <td><a href="OrderControl?opt=5&ident=${item.id}">Usuń</a></td>
        </tr>
    </c:forEach>

</table>

<%@ include file="footer.jspf" %>
<%--<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>--%>
<%--<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>--%>
<%--<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>--%>

</body>
</html>

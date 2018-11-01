<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: bibipl
  Date: 01.11.18
  Time: 20:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${empl.name} ${empl.surname}</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

</head>
<body>
<%@ include file="../header.jspf" %>
<table>
    <thead>
    <th scope="col">| ID: ${empl.id} | </th>
    <th scope="col"> Imię: ${empl.name} | </th>
    <th scope="col"> Nazwisko: ${empl.surname} |</th>
    </thead>
</table>

<table class="table">
    <thead>
    <tr>
        <th scope="col"><a href="OrderControl?opt=2">Dodaj Zlecenie</a></th>
        <th scope="col">Marka</th>
        <th scope="col">Model</th>
        <th scope="col">Nr rejestracyjny</th>
        <th scope="col">Plan</th>
        <th scope="col">Start</th>
        <th scope="col">Status</th>
        <th scope="col">Szczegóły</th>
        <th scope="col">Modyfikuj</th>
        <th scope="col">Usuń</th>
    </tr>
    </thead>
    <c:forEach items="${ordCarEmpl}" var="item">

        <c:if test="${not empty item.order.planStartDate}">
            <fmt:parseDate value="${item.order.planStartDate}" pattern="yyyy-MM-dd" var="parsedStartDate" type="date" />
            <fmt:formatDate value="${parsedStartDate}" var="newParsedStartDate" type="date" pattern="dd.MM.yyyy" />
        </c:if>
        <c:if test="${not empty item.order.realStartDate}">
            <fmt:parseDate value="${item.order.realStartDate}" pattern="yyyy-MM-dd" var="parsedRealDate" type="date" />
            <fmt:formatDate value="${parsedRealDate}" var="newParsedRealDate" type="date" pattern="dd.MM.yyyy" />
        </c:if>

        <tr>
            <td>${item.order.id}</td>
            <td>${item.car.model}</td>
            <td>${item.car.brand}</td>
            <td>${item.car.registration}</td>
            <c:if test="${not empty item.order.planStartDate}">
                <td>${newParsedStartDate}</td>
            </c:if>
            <c:if test="${empty item.order.planStartDate}">
                <td>Brak</td>
            </c:if>
            <c:if test="${not empty item.order.realStartDate}">
                <td>${newParsedRealDate}</td>
            </c:if>
            <c:if test="${empty item.order.realStartDate}">
                <td>Brak</td>
            </c:if>
            <c:choose>
                <c:when test="${item.order.status==1}">
                    <td>| Przyjęty |</td>
                </c:when>
                <c:when test="${item.order.status==2}">
                    <td>| Zatwierdzony |</td>
                </c:when>
                <c:when test="${item.order.status==3}">
                    <td>| Naprawiany |</td>
                </c:when>
                <c:when test="${item.order.status==4}">
                    <td>| Gotowy |</td>
                </c:when>
                <c:otherwise>
                    <td>| Rezygnacja |</td>
                </c:otherwise>
            </c:choose>
            <td><a href="OrderControl?opt=3&ident=${item.order.id}">Szczegóły</a></td>
            <td><a href="OrderControl?opt=4&ident=${item.order.id}">Modyfikuj</a></td>
            <td><a href="OrderControl?opt=5&ident=${item.order.id}">Usuń</a></td>
        </tr>
    </c:forEach>
</table>
<table>
    <th></th>
    <tr><td><a href="EmployeeControl?opt=1">Powrót do listy pracowników</a></td></tr>
</table>

<%@ include file="../footer.jspf" %>
<%--<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>--%>
<%--<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>--%>
<%--<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>--%>

</body>
</html>

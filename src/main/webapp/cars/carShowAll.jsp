<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: bibipl
  Date: 30.10.18
  Time: 23:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Samochody użytkownika</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<%@ include file="../header.jspf" %>
<table class="table">
    <thead>
    <tr>
        <th scope="col"><a href="CarControl?opt=2">Dodaj Samochód</a></th>
        <th scope="col">model</th>
        <th scope="col">marka</th>
        <th scope="col">nr rej.</th>
        <th scope="col">przegląd</th>
        <th scope="col">Id właściciela</th>
        <th scope="col">Szczegóły - samochodu</th>
        <th scope="col">Modyfikuj dane Samochodu</th>
        <th scope="col">Usuń Samochód</th>
    </tr>
    </thead>
    <c:forEach items="${cars}" var="item">
        <fmt:parseDate value="${item.nextReview}" pattern="yyyy-MM-dd" var="parsedDate" type="date" />
        <fmt:formatDate value="${parsedDate}" var="newParsedDate" type="date" pattern="dd.MM.yyyy" />
        <tr>
            <td>${item.id}</td>
            <td>${item.model}</td>
            <td>${item.brand}</td>
            <td>${item.registration}</td>
            <td>${newParsedDate}</td>
            <td>${item.ownerId}</td>
            <td><a href="CarControl?opt=3&ident=${item.id}">Szczegóły</a></td>
            <td><a href="CarControl?opt=4&ident=${item.id}">Modyfikuj</a></td>
            <td><a href="CarControl?opt=5&ident=${item.id}">Usuń</a></td>
        </tr>
    </c:forEach>

</table>
<%@ include file="../footer.jspf" %>
</body>
</html>

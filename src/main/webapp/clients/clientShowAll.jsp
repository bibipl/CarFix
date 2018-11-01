<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: bibipl
  Date: 30.10.18
  Time: 13:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<%@ include file="../header.jspf" %>
<table class="table">
    <thead>
    <tr>
        <th scope="col"><a href="ClientControl?opt=2">Dodaj Klienta</a></th>
        <th scope="col">name</th>
        <th scope="col">surname</th>
        <th scope="col">birthday</th>
        <th scope="col">phone</th>

        <th scope="col">Szczegóły</th>
        <th scope="col">Samochody</th>
        <th scope="col">Modyfikuj dane Klienta</th>
        <th scope="col">Usuń Klienta</th>
    </tr>
    </thead>
    <c:forEach items="${cl}" var="item">
        <fmt:parseDate value="${item.birthDate}" pattern="yyyy-MM-dd" var="parsedDate" type="date" />
        <fmt:formatDate value="${parsedDate}" var="newParsedDate" type="date" pattern="dd.MM.yyyy" />
        <tr>
            <td>${item.id}</td>
            <td>${item.name}</td>
            <td>${item.surname}</td>
            <td>${newParsedDate}</td>
            <td>${item.phone}</td>
            <td><a href="ClientControl?opt=3&ident=${item.id}">Szczegóły</a></td>
            <td><a href="ClientControl?opt=7&ident=${item.id}">Samochody</a></td>
            <td><a href="ClientControl?opt=4&ident=${item.id}">Modyfikuj</a></td>
            <td><a href="ClientControl?opt=5&ident=${item.id}">Usuń</a></td>
        </tr>
    </c:forEach>

</table>
<%@ include file="../footer.jspf" %>
</body>
</html>

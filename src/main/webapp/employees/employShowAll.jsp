<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: bibipl
  Date: 29.10.18
  Time: 22:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Serwisanci</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<%@ include file="../header.jspf" %>
<table class="table">
    <thead>
    <tr>
        <th scope="col"><a href="EmployeeControl?opt=2">| + |</a></th>
        <th scope="col">IMIĘ</th>
        <th scope="col">NAZWISKO</th>
        <th scope="col">STAWKA</th>
        <th scope="col">NAPRAWIA</th>
        <th scope="col">HISTORIA</th>
        <th scope="col">DANE</th>
        <th scope="col">MODYFIKUJ</th>
        <th scope="col">USUN</th>

    </tr>
    </thead>
    <c:forEach items="${empl}" var="item">
        <tr>
            <td>${item.id}</td>
            <td>${item.name}</td>
            <td>${item.surname}</td>
            <td>${item.hourPrice}</td>
            <td><a href="EmployeeControl?opt=7&ident=${item.id}">| N |</a></td>
            <td><a href="EmployeeControl?opt=8&ident=${item.id}">| W |</a></td>
            <td><a href="EmployeeControl?opt=3&ident=${item.id}">| D |</a></td>
            <td><a href="EmployeeControl?opt=4&ident=${item.id}">| M |</a></td>
            <td><a href="EmployeeControl?opt=5&ident=${item.id}">| U |</a></td>
        </tr>
    </c:forEach>

</table>
<table>
    <th></th>
    <tr><td><a href="EmployeeControl?opt=1">LISTA SERWISANTÓW</a></td></tr>
</table>
<%@ include file="../footer.jspf" %>
<%--<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>--%>
<%--<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>--%>
<%--<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>--%>
</body>
</html>

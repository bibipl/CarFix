<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: bibipl
  Date: 02.11.18
  Time: 23:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CZAS PRACY</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<%@ include file="../header.jspf" %>
<table>
    <thead></thead>
    <tr><td>RAPORT LICZBA PRZEPRACOWANYCh GODZIN PRZEZ SERWISANTÓW :</td></tr>
    <tr><td>POCZĄTEK :</td><td>${start}</td></tr>
    <tr><td>KONIEC   :</td><td>${end}</td></tr>
</table>
<table class="table">
    <thead>
        <tr>
            <th scope="col">NAZWISKO</th>
            <th scope="col">LICZBA GODZIN</th>
        </tr>
    </thead>
    <c:forEach items="${repOne}" var="item">
        <tr>
            <td>${item.name}</td>
            <td>${item.numOfHours}</td>
        </tr>
    </c:forEach>
</table>
<table>
    <th></th>
    <tr><td><a href="ReportControl?opt=1">POWRÓT DO RAPORTÓW</a></td></tr>
</table>
<%@ include file="../footer.jspf" %>
</body>
</html>


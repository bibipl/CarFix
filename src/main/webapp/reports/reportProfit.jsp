<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: bibipl
  Date: 03.11.18
  Time: 12:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Wynik finansowy</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<%@ include file="../header.jspf" %>
<table>
    <thead></thead>
    <tr><td>RAPORT WYNIK FINANSOWY ZA OKRES</td></tr>
    <tr><td>POCZĄTEK OKRESU:</td><td>${start}</td></tr>
    <tr><td>KONIEC   OKRESU:</td><td>${end}</td></tr>
</table>
<table class="table">
    <thead>
    <tr>
        <th scope="col">POZYCJA</th>
        <th scope="col">WARTOŚĆ</th>
    </tr>
    </thead>
        <tr>
            <td>PRZYCHODY (+)</td>
            <td>${revenues}</td>
        </tr>
    <tr>
        <td>CZĘŚCI (-)</td>
        <td>${parts}</td>
    </tr>
    <tr>
        <td>SERWIS (-)</td>
        <td>${service}</td>
    </tr>
    <tr>
        <td>WYNIK (=)</td>
        <td>${profit}</td>
    </tr>
</table>
<table>
    <th></th>
    <tr><td><a href="ReportControl?opt=1">POWRÓT DO RAPORTÓW</a></td></tr>
</table>
<%@ include file="../footer.jspf" %>
</body>
</html>

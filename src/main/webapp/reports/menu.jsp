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
    <title>RAPORTY</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

</head>
<body>
<%@ include file="../header.jspf" %>
<table class="table">
    <thead>
    <tr>
        <th scope="col">RAPORT</th>
        <th scope="col">OPIS RAPORTU</th>
        <th scope="col">DATA POCZĄTKOWA RAPORTU</th>
        <th scope="col">DATA KOŃCOWA RAPORTU</th>
        <th scope="col">WYKONAJ RAPORT</th>
    </tr>
    </thead>
    <tr>
        <form method="post" action="ReportControl">
            <input type="hidden" name="repId" value=${1}>
            <td>Raport 1 : Roboczogodziny</td>
            <td>Raport oblicza liczbę przepracowanych godzin przez każdego pracownika w wybranym okresie</td>
            <td>
                <input type="date" name="start" value="${ldt}">
            </td><td>
                <input type="date" name="end" value="${ldt}">
            </td>
            <td><input type="submit" value="Raport 1 !"></td>
        </form>
    </tr>
    <tr>
        <form method="POST" action="ReportControl">
            <input type="hidden" name="repId" value=${2}>
            <td>Raport 2 : Wynik finansowy</td>
            <td>Raport oblicza uproszczony wynik finansowy wypracowany w wybranym okresie</td>
            <td>
                <input type="date" name="start" value="${ldt}">
            </td><td>
            <input type="date" name="end" value="${ldt}">
        </td>
            <td><input type="submit" value="Raport 2 !"></td>
        </form>
    </tr>
</table>
<table>
    <th></th>
    <tr><td><a href="../index.jsp">POWRÓT DO MENU</a></td></tr>
</table>
<%@ include file="../footer.jspf" %>
</body>
</html>

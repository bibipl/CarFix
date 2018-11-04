<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: bibipl
  Date: 30.10.18
  Time: 15:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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
        <th scope="col">DANE KLIENTA</th>
    </tr>
    </thead>
  <form method="post" action="ClientControl">
    <input type="hidden" id="clId" name="clId" value="${cl.id}">
    <tr><td>IMIĘ</td><td><input type="text" name="name" value="${cl.name}"></td></tr>
    <tr><td>NAZWISKO</td><td><input type="text" name="surname" value="${cl.surname}"></td></tr>
    <tr><td>DATA URODZENIA</td><td><input type="date" name="birthDate" value="${cl.birthDate}"></td></tr>
    <tr><td>NUMER TELEFONU</td><td><input type="text" name="phone" value="${cl.phone}"></td></tr>

    <tr>
        <td><a href="ClientControl?opt=1">| POWRÓT KLIENCI |</a></td>
        <td><input type="submit" value="MODYFIKUJ"><br></td>
    </tr>
  </form>
</table>
<%@ include file="../footer.jspf" %>
</body>
</html>

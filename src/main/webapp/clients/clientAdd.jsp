<%--
  Created by IntelliJ IDEA.
  User: bibipl
  Date: 30.10.18
  Time: 13:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>NOWY KLIENT</title>
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
    <input type="hidden" id="empId" name="empId" value=${empl.id}>
    <tr><td>IMIĘ</td><td><input type="text" name="name"></td></tr>
    <tr><td>NAZWISKO</td><td><input type="text" name="surname"></td></tr>
    <tr><td>DATA URODZENIA</td><td><input type="date" name="birthDate"></td></tr>
    <tr><td>NUMER TELEFONU</td><td><input type="text" name="phone"></td></tr>

    <tr>
        <td><a href="ClientControl?opt=1">| POWRÓT KLIENCI | </a></td>
        <td><input type="submit" value="Dodaj"></td>
    </tr>

  </form>
</table>
<%@ include file="../footer.jspf" %>
</body>
</html>

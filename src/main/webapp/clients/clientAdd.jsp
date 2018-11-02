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
    <title>Title</title>
</head>
<body>
<%@ include file="../header.jspf" %>
<form method="post" action="ClientControl">
    <input type="hidden" id="empId" name="empId" value=${empl.id}>
    <label>Imię</label><input type="text" name="name"><br>
    <label>Nazwisko</label><input type="text" name="surname"><br>
    <label>Data urodzenia</label><input type="date" name="birthDate"><br>
    <label>Nr telefonu</label><input type="text" name="phone"><br>
    <input type="submit" value="Dodaj"><br>
</form>
<table>
    <thead>
    <td><a href="ClientControl?opt=1">| Powrót do listy Klientów | </a></td>
    </thead>
</table>
<%@ include file="../footer.jspf" %>
</body>
</html>

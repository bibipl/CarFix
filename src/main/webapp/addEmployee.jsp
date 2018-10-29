<%--
  Created by IntelliJ IDEA.
  User: bibipl
  Date: 29.10.18
  Time: 20:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Employee</title>
</head>
<body>
<form method="post" action="EmployeeControl">
    <label>Imię</label><input type="text" name="name"><br>
    <label>Nazwisko</label><input type="text" name="surname"><br>
    <label>Adres</label><input type="text" name="address"><br>
    <label>Nr telefonu</label><input type="text" name="phone"><br>
    <label>Uwagi</label><input type="text" name="note"><br>
    <label>Stawka godzinowa</label><input type="number" name="hourPrice" step="0.01"><br>
    <input type="submit" value="Dodaj"><br>
</form>

</body>
</html>

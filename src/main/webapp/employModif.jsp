<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: bibipl
  Date: 30.10.18
  Time: 01:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MODIFY</title>
</head>
<body>
<form method="post" action="EmployeeControl">
    <label>Imię</label><input type="text" name="name" value=${empl.name}><br>
    <label>Nazwisko</label><input type="text" name="surname" value=${empl.surname}><br>
    <label>Adres</label><input type="text" name="address" value=${empl.address}><br>
    <label>Nr telefonu</label><input type="text" name="phone" value=${empl.phone}><br>
    <label>Uwagi</label><input type="text" name="note" value=${empl.note}><br>
    <label>Stawka godzinowa</label><input type="number" name="hourPrice" step="0.01" value=${empl.hourPrice}><br>
    <input type="submit" value="Modyfikuj"><br>
</form>
</body>
</html>

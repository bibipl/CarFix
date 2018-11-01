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
</head>
<body>
<%@ include file="header.jspf" %>
<fmt:parseDate value="${cl.birthDate}" pattern="yyyy-MM-dd" var="parsedDate" type="date" />
<fmt:formatDate value="${parsedDate}" var="newParsedDate" type="date" pattern="dd.MM.yyyy" />
<form method="post" action="ClientControl">
    <input type="hidden" id="clId" name="clId" value="${cl.id}">
    <label>Imię</label><input type="text" name="name" value="${cl.name}"><br>
    <label>Nazwisko</label><input type="text" name="surname" value="${cl.surname}"><br>
    <label>Data urodzenia</label><input type="date" name="birthDate" value="${cl.birthDate}"><br>
    <label>Nr telefonu</label><input type="text" name="phone" value="${cl.phone}"><br>
    <input type="submit" value="Modyfikuj"><br>
</form>
<br>
<table>
    <th></th>
    <tr><td><a href="ClientControl?opt=1">| Powrót do listy klientów |</a></td></tr>
</table>
<%@ include file="footer.jspf" %>
</body>
</html>

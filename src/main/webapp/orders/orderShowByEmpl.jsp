<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: bibipl
  Date: 01.11.18
  Time: 20:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${empl.name} ${empl.surname}</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

</head>
<body>
<%@ include file="../header.jspf" %>
<table>
    <thead>
    <th scope="col">| ID: ${empl.id} | </th>
    <th scope="col">${empl.name} </th>
    <th scope="col">${empl.surname} |</th>
    </thead>
</table>

<table class="table">
    <%@ include file="tableTHead.jspf" %>
    <c:forEach items="${ordCarEmpl}" var="item">
        <%@ include file="dateFormatItem.jspf" %>
        <tr>
            <td>${item.order.id}</td>
            <td>${item.car.model}</td>
            <td>${item.car.brand}</td>
            <td>${item.car.registration}</td>
            <td>${item.employee.name}  ${item.employee.surname}</td>
            <%@ include file="dateTDItem.jspf" %>
            <%@ include file="statusItem.jspf" %>
            <td><a href="OrderControl?opt=3&ident=${item.order.id}">| D |</a></td>
            <td><a href="OrderControl?opt=4&ident=${item.order.id}">| M |</a></td>
            <td><a href="OrderControl?opt=5&ident=${item.order.id}">| U |</a></td>
        </tr>
    </c:forEach>
</table>
<table>
    <th></th>
    <tr><td><a href="EmployeeControl?opt=1">POWRÓT SERWISANCI</a></td>
    <td><a href="EmployeeControl?opt=1">POWRÓT ZLECENIA</a></td></tr>
</table>

<%@ include file="../footer.jspf" %>
<%--<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>--%>
<%--<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>--%>
<%--<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>--%>

</body>
</html>

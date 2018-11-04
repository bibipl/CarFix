<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %><%--
  Created by IntelliJ IDEA.
  User: bibipl
  Date: 31.10.18
  Time: 23:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Modyfikacja Zlecenia</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<%@ include file="../header.jspf" %>
<%@ include file="dateFormat.jspf" %>
<table class="table">
    <thead>
    <tr>
        <th scope="col">POZYCJA</th>
        <th scope="col">DANE ZLECENIA</th>
    </tr>
    </thead>
    <form method="post" action="OrderControl">
        <input type="hidden" name="ordId" value="${orders.id}">
        <tr><td>NUMER ZLECENIA</td><td>${orders.id}</td></tr>
        <tr>
            <td>SAMOCHÓD</td>
            <td>
                <select id="car" name="carId">
                    <c:forEach items="${cars}" var="item">
                        <c:if test="${item.id==orders.carId}">
                            <option selected value="${item.id}">${item.model} ${item.brand} | ${item.registration}</option>
                        </c:if>
                        <c:if test="${item.id!=orders.employeeId}">
                            <option value="${item.id}">${item.model} ${item.brand} | ${item.registration}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>SERWISANT</td>
            <td>
                <select name="employeeId">
                    <c:forEach items="${employees}" var="item">
                        <c:if test="${item.id==orders.employeeId}">
                            <option selected value="${item.id}">${item.name} ${item.surname}</option>
                        </c:if>
                        <c:if test="${item.id!=orders.employeeId}">
                            <option value="${item.id}">${item.name} ${item.surname}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>STATUS NAPRAWY</td>
            <td>
                <select id="status" name="status" value="${orders.status}">
                    <c:if test="${orders.status==1}">
                        <option selected value=1>PRZYJĘTY</option>
                    </c:if>
                    <c:if test="${orders.status!=1}">
                        <option value=1>PRZYJĘTY</option>
                    </c:if>
                    <c:if test="${orders.status==2}">
                        <option selected value=2>ZATWIERDZONY KOSZT</option>
                    </c:if>
                    <c:if test="${orders.status!=2}">
                        <option value=2>ZATWIERDZONY KOSZT</option>
                    </c:if>
                    <c:if test="${orders.status==3}">
                        <option selected value=3>W NAPRAWIE</option>
                    </c:if>
                    <c:if test="${orders.status!=3}">
                        <option value=3>W NAPRAWIE</option>
                    </c:if>
                    <c:if test="${orders.status==4}">
                        <option selected value=4>GOTOWY</option>
                    </c:if>
                    <c:if test="${orders.status!=4}">
                        <option value=4>GOTOWY</option>
                    </c:if>
                    <c:if test="${orders.status==5}">
                        <option selected value=5>REZYGNACJA</option>
                    </c:if>
                    <c:if test="${orders.status!=5}">
                        <option value=5>REZYGNACJA</option>
                    </c:if>
                </select>
            </td>
        </tr>
        <tr><td>OPIS PROBLEMU</td><td><input type="text" name="problemDescript" value="${orders.problemDescript}"></td></tr>
        <tr><td>OPIS NAPRAWY</td><td><input type="text" name="fixDescript" value="${orders.fixDescript}"></td></tr>
        <tr>
            <td>PLANOWANE ROZPOCZĘCIE NAPRAWYy</td>
            <c:if test="${not empty orders.planStartDate}">
                <td><input type="date" name="planStartDate" value="${orders.planStartDate}"></td>
            </c:if>
            <c:if test="${empty orders.planStartDate}">
                <td><input type="date" name="planStartDate"></td>
            </c:if>
        </tr>
        <tr><td>CENA NAPRAWY</td><td><input type="number" name="valueServ" step="0.01" value="${orders.valueServ}" step="0.01"></td></tr>
        <tr><td>KOSZT CZĘŚĆI</td><td><input type="number" name="valueParts" value="${orders.valueParts}" step="0.01"></td></tr>
        <tr><td>LICZBA ROBOCZOGODZIN</td><td><input type="number" name="numOfHours" value="${orders.numOfHours}" step="0.1"></td></tr>
        <tr>
            <td>
                <a href="OrderControl?opt=1">| POWRÓT ZLECENIA | </a>
                <a href="OrderControl?opt=5&ident=${orders.id}">USUŃ |</a>
            </td>
            <td><input type="submit" value="MODYFIKUJ"></td>
        </tr>
        <tr><td></td><td></td></tr>
    </form>
</table>

<%@ include file="../footer.jspf" %>
</body>
</html>

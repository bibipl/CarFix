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
</head>
<body>
<%@ include file="../header.jspf" %>
<%@ include file="dateFormat.jspf" %>

<form method="post" action="OrderControl">
    <p>Numer Zlecenia : ${orders.id}</p>
    <input type="hidden" name="orderId" value="${orders.id}">
    <label for="car">Samochód</label>
    <select id="car" name="carId" value="${orders.carId}">
        <c:forEach items="${cars}" var="item">
            <c:if test="${item.id==orders.carId}">
                <option selected value="${item.id}">${item.model} ${item.brand} | ${item.registration}</option>
            </c:if>
            <c:if test="${item.id!=orders.employeeId}">
                <option value="${item.id}">${item.model} ${item.brand} | ${item.registration}</option>
            </c:if>
        </c:forEach>
    </select> <br>
    <label for="service">Serwisant</label>
    <select id="service" name="employeeId" value="${orders.employeeId}">
        <c:forEach items="${employees}" var="item">
            <c:if test="${item.id==orders.employeeId}">
                <option selected value="${item.id}">${item.name} ${item.surname}</option>
                <%--<c:set var="eId" value="${item.hourPrice}"/>--%>
            </c:if>
            <c:if test="${item.id!=orders.employeeId}">
                <option value="${item.id}">${item.name} ${item.surname}</option>
            </c:if>
        </c:forEach>
     </select> <br>
    <c:if test="${not empty orders.planStartDate}">
        <label>Planowane rozpoczęcie naprawy</label><input type="date" name="planStartDate" value="${orders.planStartDate}"><br>
    </c:if>
    <c:if test="${empty orders.planStartDate}">
        <label>Planowane rozpoczęcie naprawy</label><input type="date" name="planStartDate"><br>
    </c:if>
 <%--  konwersja niepotrzebna te daty będą z automatu.....
    <c:if test="${not empty orders.realStartDate}">
        <label>Rzeczywiste rozpoczęcie naprawy</label><input type="date" name="realStartDate" value="${orders.realStartDate}"><br>
    </c:if>
    <c:if test="${empty orders.realStartDate}">
        <label>Rzeczywiste rozpoczęcie naprawy</label><input type="date" name="realStartDate"><br>
    </c:if>
    <c:if test="${not empty orders.realEndDate}">
        <label>Zakończenie naprawy</label><input type="date" name="realEndDate" value="${orders.realEndDate}"><br>
    </c:if>
    <c:if test="${empty orders.realEndDate}">
        <label>Zakończenie naprawy</label><input type="date" name="realEndDate"><br>
    </c:if>
  --%>
    <label for="status">Status</label>
    <select id="status" name="status" value="${orders.status}">
        <c:if test="${orders.status==1}">
            <option selected value=1>Przyjęty</option>
        </c:if>
        <c:if test="${orders.status!=1}">
            <option selected value=1>Przyjęty</option>
        </c:if>
        <c:if test="${orders.status==2}">
            <option selected value=2>Zatwierdzony koszt naprawy</option>
        </c:if>
        <c:if test="${orders.status!=2}">
            <option value=2>Zatwierdzony koszt naprawy</option>
        </c:if>
        <c:if test="${orders.status==3}">
            <option selected value=3>W naprawie</option>
        </c:if>
        <c:if test="${orders.status!=3}">
            <option value=3>W naprawie</option>
        </c:if>
        <c:if test="${orders.status==4}">
            <option selected value=4>Gotowy</option>
        </c:if>
        <c:if test="${orders.status!=4}">
            <option value=4>Gotowy</option>
        </c:if>
        <c:if test="${orders.status==5}">
            <option selected value=5>Rezygnacja</option>
        </c:if>
        <c:if test="${orders.status!=5}">
            <option value=5>Rezygnacja</option>
        </c:if>
    </select> <br>
    <label>Opis Problemu</label><input type="text" name="problemDescript" value="${orders.problemDescript}"><br>
    <label>Opis Naprawy</label><input type="text" name="fixDescript" value="${orders.fixDescript}"><br>
    <label>Koszt części</label><input type="number" name="valueParts" value="${orders.valueParts}" step="0.01"><br>
    <label>Liczba roboczogodzin</label><input type="number" name="numOfHours" value="${orders.numOfHours}" step="0.1"><br>
    <%--<label>Stawka godzinowa</label><input type="number" name="hourPrice" value="${eId}" placeholder="${eId}" step="0.01"><br>--%>
    <input type="hidden" name="valueServ" value="${orders.valueServ}">
    <input type="submit" value="Zmień"><br>
</form>

<table>
    <thead>
    <td><a href="OrderControl?opt=1">| Powrót do listy ZLECEŃ | </a></td>
    <td><a href="OrderControl?opt=5&ident=${orders.id}">Usuń |</a></td>
    </thead>
</table>

<%@ include file="../footer.jspf" %>
</body>
</html>

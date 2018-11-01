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

<c:if test="${not empty orders.planStartDate}">
    <fmt:parseDate value="${orders.planStartDate}" pattern="yyyy-MM-dd" var="parsedStartDate" type="date" />
    <fmt:formatDate value="${parsedStartDate}" var="newParsedPlanDate" type="date" pattern="dd.MM.yyyy" />
</c:if>
<c:if test="${not empty orders.realStartDate}">
    <fmt:parseDate value="${orders.realStartDate}" pattern="yyyy-MM-dd" var="parsedRealDate" type="date" />
    <fmt:formatDate value="${parsedRealDate}" var="newParsedRealDate" type="date" pattern="dd.MM.yyyy" />
</c:if>
<form method="post" action="OrderControl">
    <p>Numer Zlecenia : ${orders.id}</p>
    <input type="hidden" name="orderId" value="${orders.id}">
    <label>Identyfikator samochodu</label><input type="number" name="carId" value="${orders.carId}"><br>
    <label>Pracownik</label><input type="number" name="employeeId" value=${orders.employeeId}><br>
    <c:if test="${not empty orders.planStartDate}">
        <label>Planowane rozpoczęcie naprawy</label><input type="date" name="planStartDate" value="${orders.planStartDate}"><br>
    </c:if>
    <c:if test="${empty orders.planStartDate}">
        <label>Planowane rozpoczęcie naprawy</label><input type="date" name="planStartDate"><br>
    </c:if>
    <c:if test="${not empty orders.realStartDate}">
        <label>Rzeczywiste rozpoczęcie naprawy</label><input type="date" name="realStartDate" value="${orders.realStartDate}"><br>
    </c:if>
    <c:if test="${empty orders.realStartDate}">
        <label>Rzeczywiste rozpoczęcie naprawy</label><input type="date" name="realStartDate"><br>
    </c:if>
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
    <label>Stawka godzinowa</label><input type="number" name="hourPrice" value="${orders.hourPrice}" step="0.01"><br>
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

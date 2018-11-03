<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: bibipl
  Date: 02.11.18
  Time: 17:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${cars.registration}</title>
</head>
<body>
<%@ include file="../header.jspf" %>
<table>
    <thead>
    <th scope="col">| ${cars.model} </th>
    <th scope="col">${cars.brand} | </th>
    <th scope="col">| ${cars.registration} | </th>
    <th scope="col"> WŁAŚCICIEL ${clients.name} </th>
    <th scope="col">${clients.surname} |</th>
    </thead>
</table>
<table class="table">
    <thead>
    <tr>
        <th scope="col"><a href="OrderControl?opt=2">| + |</a></th>
        <th scope="col">DATA ROZPOCZĘCIA NAPRAWY</th>
        <th scope="col">DATA ZAKOŃCZENIA NAPRAWY</th>
        <th scope="col">OPIS NAPRAWY</th>
        <th scope="col">DANE</th>
    </tr>
    </thead>
        <c:forEach items="${orders}" var="item">
            <c:if test="${not empty item.realStartDate}">
                <fmt:parseDate value="${item.realStartDate}" pattern="yyyy-MM-dd" var="parsedRealDate" type="date" />
                <fmt:formatDate value="${parsedRealDate}" var="newParsedRealDate" type="date" pattern="dd.MM.yyyy" />
            </c:if>
            <c:if test="${not empty item.realEndDate}">
                <fmt:parseDate value="${item.realEndDate}" pattern="yyyy-MM-dd" var="parsedEndDate" type="date" />
                <fmt:formatDate value="${parsedEndDate}" var="newParsedEndDate" type="date" pattern="dd.MM.yyyy" />
            </c:if>
            <tr>
                <td>${item.id}</td>
                <c:if test="${not empty item.realStartDate}">
                    <td>${newParsedRealDate}</td>
                </c:if>
                <c:if test="${empty item.realStartDate}">
                    <td>Brak</td>
                </c:if>
                <c:if test="${not empty item.realEndDate}">
                    <td>${newParsedEndDate}</td>
                </c:if>
                <c:if test="${empty item.realEndDate}">
                    <td>Brak</td>
                </c:if>
                <c:if test="${not empty item.fixDescript}">
                    <td>${item.fixDescript}</td>
                </c:if>
                <c:if test="${empty item.fixDescript}">
                    <td>Brak opisu</td>
                </c:if>
                <td><a href="OrderControl?opt=3&ident=${item.id}">| S |</a></td>
            </tr>
        </c:forEach>
</table>
<table>
    <thead></thead>
    <tr>
        <td><a href="CarControl?opt=7&ident=${clients.id}">| Powrót do Samochodów Klienta | </a></td>
        <td><a href="CarControl?opt=1"> Powrót do listy wszystkich samochodów |</a></td>
    </tr>
</table>
<%@ include file="../footer.jspf" %>
</body>
</html>

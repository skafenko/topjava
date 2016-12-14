<%--
  Created by IntelliJ IDEA.
  User: Mykhailo
  Date: 12.12.2016
  Time: 19:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Meals list</title>
    <style>
        .normal {color: green}
        .exceeded {color: red}
    </style>
</head>
<body>
    <h2><a href="index.html">Home</a></h2>
    <h2>Meals list</h2>
    <table border="1">
        <tr>
            <th width="500">Время</th>
            <th>Описание</th>
            <th>Калории</th>
            <th></th>
            <th></th>
        </tr>
        <c:forEach var="meal" items="${meals}">
            <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.model.MealWithExceed"/>
            <tr class="${meal.exceed ? 'exceeded' : 'normal'}">
                <th>
                    <fmt:parseDate value="${meal.dateTime}" pattern="y-M-dd'T'H:m" var="parsedDate"/>
                    <fmt:formatDate value="${parsedDate}" pattern="yyyy-MM-dd HH:mm"/>
                </th>
                <th>${meal.description}</th>
                <th>${meal.calories}</th>
                <th><a href="meals?action=update&id=${meal.id}">Update</a></th>
                <th><a href="meals?action=delete&id=${meal.id}">Delete</a></th>
            </tr>
        </c:forEach>
    </table>
</body>
</html>

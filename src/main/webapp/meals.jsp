<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %><%--
  Created by IntelliJ IDEA.
  User: комп
  Date: 11.06.2017
  Time: 11:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Meals list</title>
    <style>
        .green {
            color: green;
        }

        .red    {
            color: red;
        }
    </style>
</head>
<body>
<h2><a href="index.html">Home</a> </h2>
<h2>Meals list</h2>

<table style=" border-style: solid; border-width:1px; width: 600px; border-collapse: collapse;">
    <thead>
    <tr style="background-color: gray;">
        <td style="width: 80px;">Date/Time</td>
        <td style="width: 80px;">Description</td>
        <td style="width: 80px;">Calories</td>
    </tr>
    </thead>
    <c:forEach var="meal" items="${mealList}">

        <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.model.MealWithExceed"/>
        <tr class="${meal.exceed? 'red' : 'green'}">
            <td><%=TimeUtil.toString(meal.getDateTime())%></td>
            <td><c:out value="${meal.description}" /></td>
            <td>${meal.calories}</td>
        </tr>
    </c:forEach>

    </table>
</body>
</html>

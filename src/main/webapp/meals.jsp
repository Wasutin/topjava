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
<section>
<h2><a href="index.html">Home</a> </h2>
<h2>Meals list</h2>
    <a href="meals?action=create">Add meal</a>
    <hr>

<table border="1" cellpadding="8" cellspacing="0">
    <thead>
    <tr style="background-color: gray;">
        <th>Date/Time</th>
        <th>Description</th>
        <th>Calories</th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <c:forEach var="meal" items="${mealList}">

        <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.model.MealWithExceed"/>
        <tr class="${meal.exceed? 'red' : 'green'}">
            <td><%=TimeUtil.toString(meal.getDateTime())%></td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td><a href="meals?action=update&id=${meal.id}">Update</a> </td>
            <td><a href="meals?action=delete&id=${meal.id}">Delete</a> </td>
        </tr>
    </c:forEach>

    </table>
</section>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User</title>
</head>
<body>
<table>
    <c:set var="u" value="${user}"/>
    <tr>
        <td>Имя:</td> <td>${u.getName()}</td>
    </tr>
    <tr>
        <td>Фамилия:</td> <td>${u.getSurname()}</td>
    </tr>
    <tr>
        <td>Возраст:</td> <td>${u.getAge()}</td>
    </tr>
    <tr>
        <td>Роль: </td> <td>${u.getRole()}</td>
    </tr>
</table>
</body>
</html>

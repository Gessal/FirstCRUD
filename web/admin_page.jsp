<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<table border="1" align="center">
    <caption>Пользователи</caption>
    <tr>
        <td align="center">Имя</td> <td>Фамилия</td><td>Возраст</td><td>Изменить</td><td>Удалить</td>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.getName()}</td>
            <td>${user.getSurname()}</td>
            <td>${user.getAge()}</td>
            <form>
                <input type="hidden" name="id" value="${user.getId()}">
                <td><div align="center"><input type="submit" value="Изменить" formaction="/admin/change" formmethod="get"></div></td>
                <td><div align="center"><input type="submit" value="Удалить" formaction="/admin/delete" formmethod="post"></div></td>
            </form>
        </tr>
    </c:forEach>
</table>
<div align="center"><form action="/admin/add" method="get">
    <input type="submit" value="Добавить">
</form></div>
</body>
</html>
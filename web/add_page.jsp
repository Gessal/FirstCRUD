<%--
  Created by IntelliJ IDEA.
  User: Zver
  Date: 13.05.2020
  Time: 12:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add user</title>
</head>
    <div align="center"><form method="post">
        <table>
            <tr> <td>Имя:</td> <td><input type="text" name="name" size="25"> </td></tr>
            <tr> <td>Фамилия:</td> <td><input type="text" name="surname" size="25"> </td></tr>
            <tr> <td>Возраст:</td> <td><input type="number" name="age" size="8" value="18"> </td></tr>
        </table>
        <input type="submit" value="Добавить">
    </form></div>
<body>

</body>
</html>

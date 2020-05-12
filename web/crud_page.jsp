<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My_first-CRUD</title>
</head>
<body>
    <table border="1" align="center">
        <caption>Пользователи</caption>
        <tr>
            <td align="center"> Имя </td> <td> Фамилия </td> <td> Возраст </td> <td> Изменить </td> <td> Удалить </td>
        </tr>
        <% List<User> users = (List<User>) request.getAttribute("users");
           for (User user : users) { %>
            <tr>
                <form method="post">
                    <td><input type="text" name="name" value="<%=user.getName()%>" required></td>
                    <td><input type="text" name="surname" value="<%=user.getSurname()%>" required></td>
                    <td><input type="number" name="age" value="<%= user.getAge()%>" required></td>
                    <input type="hidden" name="id" value="<%= user.getId() %>">
                    <td><div align="center"><input type="submit" value="Изменить" formaction="/crud/change"></div></td>
                    <td><div align="center"><input type="submit" value="Удалить" formaction="/crud/delete"></div></td>
                </form>
            </tr>
        <%}%>
    </table>

    <div align="center"><form action="/crud/add" method="post">
        <table>
            <tr> <td>Имя:</td> <td><input type="text" name="name" size="25"> </td></tr>
            <tr> <td>Фамилия:</td> <td><input type="text" name="surname" size="25"> </td></tr>
            <tr> <td>Возраст:</td> <td><input type="number" name="age" size="8" value="18"> </td></tr>
        </table>
        <input type="submit" value="Добавить">
    </form></div>
</body>
</html>

package servlet;

import model.User;
import service.UsersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/crud/*")
public class CRUDServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = UsersService.getInstance().findAllUsers();
        req.setAttribute("users", users);
        req.getRequestDispatcher("/crud_page.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (req.getPathInfo() != null && req.getPathInfo().equals("/delete")) {
                String idString = req.getParameter("id");
                if (idString != null) {
                    UsersService.getInstance().deleteUser(Long.parseLong(idString));
                }
            } else if (req.getPathInfo() != null && req.getPathInfo().equals("/change")) {
                String idString = req.getParameter("id");
                if (idString != null) {
                    UsersService.getInstance().updateUser(new User(Long.parseLong(idString),
                            req.getParameter("name"),
                            req.getParameter("surname"),
                            Byte.parseByte(req.getParameter("age"))));
                }
            } else if (req.getPathInfo() != null && req.getPathInfo().equals("/add")) {
                User user = new User(req.getParameter("name"),
                        req.getParameter("surname"),
                        Byte.parseByte(req.getParameter("age")));
                UsersService.getInstance().addUser(user);
            }
            resp.setStatus(200);
        } catch (Exception e) {
            resp.setStatus(418);
        } finally {
            resp.sendRedirect("/crud");
        }
    }
}

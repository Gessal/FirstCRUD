package servlet;

import model.User;
import service.UsersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/crud/add")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/add_page.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User user = new User(req.getParameter("name"),
                    req.getParameter("surname"),
                    Byte.parseByte(req.getParameter("age")));
            UsersService.getInstance().addUser(user);
            resp.setStatus(200);
        } catch (Exception e) {
            resp.setStatus(418);
        } finally {
            resp.sendRedirect("/crud");
        }
    }
}
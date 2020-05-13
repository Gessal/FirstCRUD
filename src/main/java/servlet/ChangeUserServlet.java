package servlet;

import model.User;
import service.Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/crud/change")
public class ChangeUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User user = Service.getInstance().findUserById(Long.parseLong(req.getParameter("id")));
            req.setAttribute("user", user);
            req.getRequestDispatcher("/change_page.jsp").forward(req, resp);
        } catch (Exception e) {
            resp.setStatus(418);
            resp.sendRedirect("/crud");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String idString = req.getParameter("id");
            if (idString != null) {
                Service.getInstance().updateUser(new User(Long.parseLong(idString),
                        req.getParameter("name"),
                        req.getParameter("surname"),
                        Byte.parseByte(req.getParameter("age"))));
            }
            resp.setStatus(200);
        } catch (Exception e) {
            resp.setStatus(418);
        } finally {
            resp.sendRedirect("/crud");
        }
    }
}
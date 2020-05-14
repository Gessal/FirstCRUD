package servlet;

import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/crud/delete")
public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String idString = req.getParameter("id");
            if (idString != null) {
                UserService.getInstance().deleteUser(Long.parseLong(idString));
            }
            resp.setStatus(200);
        } catch (Exception e) {
            resp.setStatus(418);
        } finally {
            resp.sendRedirect("/crud");
        }
    }
}
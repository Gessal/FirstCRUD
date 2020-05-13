package servlet;

import model.User;
import service.UsersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/crud")
public class UsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            List<User> users = UsersService.getInstance().findAllUsers();
            req.setAttribute("users", users);
            req.getRequestDispatcher("/main_page.jsp").forward(req, resp);
    }
}

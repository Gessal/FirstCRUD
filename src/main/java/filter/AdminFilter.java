package filter;

import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/admin/*")
public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect("/login");
        } else if (((User) session.getAttribute("user")).getRole().equals("admin")){
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (((User) session.getAttribute("user")).getRole().equals("user")) {
            resp.sendRedirect("/user");
        }
    }

    @Override
    public void destroy() {

    }
}

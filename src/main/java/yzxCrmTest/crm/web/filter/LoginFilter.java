package yzxCrmTest.crm.web.filter;

import yzxCrmTest.crm.settings.domain.User;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "LoginFilter")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String path = request.getServletPath();
        if ("/login.jsp".equals(path) || "/settings/user/login.do".equals(path)){
            chain.doFilter(request, response);
        }else {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if (user != null){
                chain.doFilter(request, response);
            }else {
                response.sendRedirect(request.getContextPath()+"/login.jsp");
            }
        }

    }
}

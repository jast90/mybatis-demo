package cn.jastz.mybatis.demo.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebFilter
public class LoginFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        HttpSession session = httpServletRequest.getSession();
        if (session.getAttribute("currentUserId") == null) {
            if (!Objects.equals(httpServletRequest.getRequestURI(), "/user/login")
                    && httpServletRequest.getRequestURI().contains("/webjars") == false
                    && httpServletRequest.getRequestURI().contains("/favicon.ico") == false) {
                httpServletResponse.sendRedirect("/user/login");
                return;
            }
        } else {
            if (Objects.equals(httpServletRequest.getRequestURI(), "/user/login")) {
                httpServletResponse.sendRedirect("/");
                return;
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}

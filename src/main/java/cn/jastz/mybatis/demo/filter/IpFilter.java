package cn.jastz.mybatis.demo.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author zhiwen
 */
@WebFilter
public class IpFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println(request.getRemoteAddr());
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}

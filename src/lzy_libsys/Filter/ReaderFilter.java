package lzy_libsys.Filter;

import lzy_libsys.Entity.Reader;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class ReaderFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        HttpSession session = request.getSession();
        Reader reader = (Reader)session.getAttribute("reader");
        if(reader == null){
            response.sendRedirect("login.jsp");
        }else{
            filterChain.doFilter(servletRequest,servletResponse);
        }

    }

    @Override
    public void destroy() {

    }
}

package cz.sohlich.web.filter;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Radomír Sohlich (fg8cvg) on 27.9.2016.
 */
@WebFilter(urlPatterns = "/*")
public class AnnotationFilter implements javax.servlet.Filter {

    Logger log = LoggerFactory.getLogger(AnnotationFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        doLog(servletRequest);
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }


    private void doLog(ServletRequest request) {
        HttpServletRequest req = (HttpServletRequest) request;
        log.info("{} : {}", req.getMethod(), req.getRequestURL().toString());
    }
}

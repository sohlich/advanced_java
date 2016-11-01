/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.sohlich.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author sohlich
 */
public class InitializerFilter implements Filter {

    Logger log = LoggerFactory.getLogger(InitializerFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        log.info("Filtering parameter : " + req.getParameter("redirect"));
        if (req.getParameter("redirect") != null) {
            ((HttpServletResponse) response).sendRedirect(req.getContextPath() + "/redirected");
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

}

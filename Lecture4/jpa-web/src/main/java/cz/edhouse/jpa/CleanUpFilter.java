package cz.edhouse.jpa;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Needed for Entity Manager clean up at the end of request.
 *
 * @author Frantisek Spacek
 */
@WebFilter(filterName = "CleanUpFilter", urlPatterns = {"/*"})
public class CleanUpFilter implements Filter {

    @Override
    public void init(FilterConfig fc) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
        fc.doFilter(sr, sr1);
        EntityManagerFactory.clear();
    }

    @Override
    public void destroy() {
    }

}

package cz.sohlich.filter;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;
import cz.sohlich.config.Configuration;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

/**
 * Created by Radomir Sohlich on 9/24/16.
 */
@WebFilter(urlPatterns = "/secured/*")
public class SecurityFilter implements Filter {

    private final JWTVerifier verifier = new JWTVerifier(Configuration.JWT_SECRET);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        Cookie[] cookies = request.getCookies();
        Optional<Cookie> token = Arrays.stream(cookies).filter(p -> {
            return "token" .equals(p.getName());
        }).findFirst();

        if (!token.isPresent()) {
            ((HttpServletResponse) res).sendRedirect(((HttpServletRequest)
                    req).getContextPath() + "/login");
            return;
        }
        try {
            final Map<String, Object> claims = verifier.verify(token.get().getValue());
            String user = (String) claims.get("user");
            req.setAttribute("user", user);
            filterChain.doFilter(req, res);
        } catch (NoSuchAlgorithmException | InvalidKeyException |
                SignatureException | JWTVerifyException e) {
            ((HttpServletResponse) res).setStatus(500);
        } catch (IllegalStateException e) {
            ((HttpServletResponse) res).sendRedirect(((HttpServletRequest)
                    req).getContextPath() + "/login");
        }
    }

    @Override
    public void destroy() {

    }
}

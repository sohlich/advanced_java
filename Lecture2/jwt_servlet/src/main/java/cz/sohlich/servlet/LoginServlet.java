package cz.sohlich.servlet;

import cz.sohlich.commons.impl.JwtTokenFactory;
import cz.sohlich.config.Configuration;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Servlet to handle login.
 * <p>
 * Created by Radomir Sohlich on 9/23/16.
 */
@WebServlet(name = "Login", urlPatterns = "/login",
        loadOnStartup = 1)
public class LoginServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        writeForm(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Cookie cookie = new Cookie("token", generateToken(login, password));
        cookie.setHttpOnly(true);
        resp.addCookie(cookie);
        resp.sendRedirect("secured/welcome");
    }


    private String generateToken(String login, String password) {
        // Count expiration for 3 days from today.
        long expiration = System.currentTimeMillis() / 1000 + (3 *
                86400);


        // Generate JWT token.
        return new JwtTokenFactory(Configuration
                .JWT_SECRET)
                .addContent("user", login)
                .addContent("exp", expiration)
                .generateToken();
    }


    private void writeForm(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ServletContext context = this.getServletContext();
        try (InputStream is = context.getResourceAsStream("/templates/login.tmp")) {
            try (OutputStream os = resp.getOutputStream()) {
                IOUtils.copy(is, os);
            }
        }
    }
}

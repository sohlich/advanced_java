package cz.sohlich.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

/**
 * Created by radek on 7/8/16.
 */
@WebServlet(name = "Home", urlPatterns = "/login")
public class HomeServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("login");
        String password = request.getParameter("password");
        response.setHeader("token", Base64.getEncoder().encodeToString
                ((name + ";" + password).getBytes()));
        response.getWriter().write("OK");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String html = "<html>" +
                "<form action=\"login\" method=\"POST\">" +
                "Login: <input type=\"text\" name=\"login\"><br/>" +
                "Password: <input type=\"password\" name=\"password\"><br/>" +
                "<input type=\"submit\" value=\"Login\">" +
                "</form>" +
                "</html>";
        response.getWriter().write(html);
    }
}

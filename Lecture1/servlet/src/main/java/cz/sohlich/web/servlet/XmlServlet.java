package cz.sohlich.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

/**
 * This servlet is added via descriptor
 * web.xml located in WEB-INF folder.
 *
 * Created by radek on 7/8/16.
 */
public class XmlServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("login");
        String password = request.getParameter("password");
        response.setHeader("token", Base64.getEncoder().encodeToString
                ((name + ";" + password).getBytes()));
        response.getWriter().write("OK");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String html = "Xml";
        response.getWriter().write(html);
    }
}

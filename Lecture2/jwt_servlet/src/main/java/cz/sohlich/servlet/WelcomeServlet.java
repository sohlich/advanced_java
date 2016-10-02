package cz.sohlich.servlet;

import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Radomir Sohlich on 9/24/16.
 */
@WebServlet(name = "Welcome", urlPatterns = "/secured/welcome", loadOnStartup
        = 1)
public class WelcomeServlet extends SecuredServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Template tmp = new Template("welcome", readTemplate("welcome"), null);

        // Put data to template
        Map<String, Object> data = new HashMap<>();
        data.put("user", getUser(req));
        try {
            tmp.process(data, resp.getWriter());
        } catch (TemplateException e) {
            resp.setStatus(500);
        }
    }
}

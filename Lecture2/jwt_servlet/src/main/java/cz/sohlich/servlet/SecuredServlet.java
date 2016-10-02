package cz.sohlich.servlet;

import org.apache.commons.io.IOUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Map;

/**
 * Created by Radomir Sohlich on 9/23/16.
 */
public abstract class SecuredServlet extends HttpServlet {

    /**
     * Get the user attribute from request.
     *
     * @param request
     * @return username from the request.
     */
    public String getUser(HttpServletRequest request) {
        return (String) request.getAttribute("user");
    }


    protected void writePage(HttpServletResponse resp, String template,
                             Map<String, Object>
                                     data) {
        ServletContext context = this.getServletContext();
        try (InputStream is = context.getResourceAsStream("/templates/" +
                template + ".tmp")) {
            try (OutputStream os = resp.getOutputStream()) {
                IOUtils.copy(is, os);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    protected String readTemplate(String template) {
        ServletContext context = this.getServletContext();
        try (InputStreamReader is = new InputStreamReader(context.getResourceAsStream
                ("/templates/" +
                        template + ".tmp"))) {
            return IOUtils.toString(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}

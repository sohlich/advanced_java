package cz.edhouse.jdbc.web;

import com.google.gson.Gson;
import cz.edhouse.jdbc.DataSourceHolder;
import cz.edhouse.jdbc.dao.impl.AuthorDaoImpl;
import cz.edhouse.jdbc.dao.impl.PostDaoImpl;
import cz.edhouse.jdbc.dto.PostDto;
import cz.edhouse.jdbc.service.AuthorService;
import cz.edhouse.jdbc.service.AuthorServiceImpl;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Simple servlet which returns list of stored authors on GET and via POST request new posts for stored authors can be
 * created.
 *
 * @author Frantisek Spacek
 */
@WebServlet(name = "AuthorServlet", urlPatterns = {"/authors"})
public class AuthorServlet extends HttpServlet {

    private static final String JSON_MEDIA_TYPE = "application/json";

    private AuthorService authorService;

    @Override
    public void init() throws ServletException {
        this.authorService = new AuthorServiceImpl(
                new AuthorDaoImpl(DataSourceHolder.getDataSource()),
                new PostDaoImpl(DataSourceHolder.getDataSource()));
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType(JSON_MEDIA_TYPE);

        writeAsJson(response, authorService.getAuthors());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PostDto post = null;
        try (InputStream is = request.getInputStream()) {
            post = new Gson().fromJson(new InputStreamReader(is), PostDto.class);
        }

        if (post == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        post = authorService.createPostFor(Integer.valueOf(request.getParameter("authorId")), post);
        writeAsJson(response, post);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Authors Servlet";
    }

    private void writeAsJson(HttpServletResponse resp, Object obj) throws IOException {
        resp.setContentType(JSON_MEDIA_TYPE);

        try (PrintWriter out = resp.getWriter()) {
            new Gson().toJson(obj, out);
        }
    }
}

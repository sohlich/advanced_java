package cz.edhouse.jpa.web;

import com.google.gson.Gson;
import cz.edhouse.jpa.EntityManagerFactory;
import cz.edhouse.jpa.dao.impl.AuthorDaoImpl;
import cz.edhouse.jpa.dao.impl.PostDaoImpl;
import cz.edhouse.jpa.dto.PostDto;
import cz.edhouse.jpa.service.AuthorService;
import cz.edhouse.jpa.service.AuthorServiceImpl;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.persistence.EntityManager;
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
        final AuthorService authorService = createAuthorService();

        final String command = request.getParameter("command");
        if (command == null) {
            writeAsJson(response, authorService.getAuthors());
        } else if ("posts".equals(command)) {
            final String authorIdValue = request.getParameter("authorId");
            if (authorIdValue == null) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            } else {
                retrievePostsByAuthorId(authorService, authorIdValue, response);
            }

        }

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

        post = createAuthorService().createPostFor(Integer.valueOf(request.getParameter("authorId")), post);
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

    private void retrievePostsByAuthorId(AuthorService authorService,
            final String authorIdValue, HttpServletResponse response) throws IOException {
        final int authorId;
        try {
            authorId = Integer.valueOf(authorIdValue);
        } catch (NumberFormatException ex) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        writeAsJson(response, authorService.getPostFor(authorId));
    }

    private void writeAsJson(HttpServletResponse resp, Object obj) throws IOException {
        resp.setContentType(JSON_MEDIA_TYPE);

        try (PrintWriter out = resp.getWriter()) {
            new Gson().toJson(obj, out);
        }
    }

    private AuthorService createAuthorService() {
        final EntityManager em = EntityManagerFactory.getEntityManager();
        final AuthorService authorService = new AuthorServiceImpl(
                new AuthorDaoImpl(em),
                new PostDaoImpl(em));
        return authorService;
    }
}

package cz.fai.blog.web;

import cz.fai.blog.dto.AuthorDto;
import cz.fai.blog.manager.AuthorManager;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Radomir Sohlich on 10/24/16.
 */
@WebServlet(urlPatterns = "login")
public class LoginController extends HttpServlet {

    private AuthorManager authorManager;

    @Inject
    public void setAuthorManager(AuthorManager authorManager) {
        this.authorManager = authorManager;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/pages/login.jsp").forward
                (req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        AuthorDto authorDto = authorManager.getByEmailAndPassword(login,
                password);
        if (authorDto != null) {
            req.getSession(true).setAttribute("user", authorDto);
            resp.sendRedirect("index");
        } else {
            req.setAttribute("failed", true);
            getServletContext().getRequestDispatcher
                    ("/pages/login.jsp").forward(req, resp);
        }
    }

}

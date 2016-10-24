package cz.fai.blog.web;

import cz.fai.blog.dto.AuthorDto;
import cz.fai.blog.dto.PostDto;
import cz.fai.blog.manager.PostManager;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by Radomir Sohlich on 10/24/16.
 */
@WebServlet("createpost")
public class CreatePostController extends HttpServlet {

    @Inject
    PostManager postManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id == null) {
            req.setAttribute("post", new PostDto());
        } else {
            req.setAttribute("post", postManager.getOne(Integer.parseInt(id)));
        }
        getServletContext().getRequestDispatcher("/pages/post/edit.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer authorId = ((AuthorDto) req.getSession().getAttribute("user"))
                .getId();
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        boolean published = "on".equals(req.getParameter("published"));
        PostDto dto = new PostDto();
        dto.setContent(content);
        dto.setPublished(published);
        dto.setTitle(title);
        dto.setCreateDate(new Date());
        if (req.getParameter("id") == null || "".equals(req.getParameter("id").trim()
        )) {
            postManager.createPostFor(authorId, dto);
        } else {
            postManager.updatePost(Integer.parseInt(req.getParameter("id")),
                    dto);
        }

    }
}

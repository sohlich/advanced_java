/*
 */
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
import java.util.List;

/**
 * @author František Špaček
 */
@WebServlet("posts")
public class PostsController extends HttpServlet {


    private PostManager postManager;

    @Inject
    public PostsController(PostManager manager) {
        this.postManager = manager;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AuthorDto user
                = (AuthorDto) req.getSession(false).getAttribute("user");
        List<PostDto> postDtos = postManager.getPostsFor(user.getId());
        req.setAttribute("posts", postDtos);
        getServletContext().getRequestDispatcher("/pages/post/posts.jsp")
                .forward
                        (req, resp);
    }


}

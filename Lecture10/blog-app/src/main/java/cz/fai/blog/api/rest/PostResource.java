package cz.fai.blog.api.rest;

import cz.fai.blog.bean.SessionBean;
import cz.fai.blog.dto.PostDto;
import cz.fai.blog.manager.PostManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.ext.Providers;
import java.util.List;

/**
 * Created by Radomir Sohlich on 10/31/16.
 */
@Stateless
@Path("posts")
@Produces("application/json")
public class PostResource {

    Logger logger = LoggerFactory.getLogger(PostResource.class);

    @Inject
    PostManager postManager;

    @Inject
    SessionBean sessionBean;

    @Context
    Application app;

    @Context
    UriInfo uri;

    @Context
    HttpHeaders headers;

    @Context
    Request request;

    @Context
    SecurityContext security;

    @Context
    Providers providers;

    @GET
    public List<PostDto> getAll() {

        logger.info(headers.getHeaderString("Content-Type"));

        return postManager.getAllPublished();
    }

    @GET
    @Path("{id}") //Path params
    public PostDto getOne(@PathParam("id") Integer id) {
        return postManager.getOne(id);
    }

    @PUT
    public PostDto createOne(PostDto post, @QueryParam("author") Integer author) {
        return postManager.createPostFor(author, post);
    }

}

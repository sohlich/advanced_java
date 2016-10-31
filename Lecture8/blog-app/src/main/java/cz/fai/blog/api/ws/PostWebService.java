package cz.fai.blog.api.ws;

import cz.fai.blog.dto.PostDto;
import cz.fai.blog.manager.PostManager;

import javax.inject.Inject;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

/**
 * Created by Radomir Sohlich on 10/31/16.
 */
@WebService
public class PostWebService {

    @Inject
    PostManager postManager;

    public List<PostDto> getAll() {
        return postManager.getAllPublished();
    }


    public PostDto getOne(@WebParam(name = "id") Integer id) {
        return postManager.getOne(id);
    }

    public PostDto createOne(PostDto post, @WebParam(name = "author") Integer
            author) {
        return postManager.createPostFor(author, post);
    }

}

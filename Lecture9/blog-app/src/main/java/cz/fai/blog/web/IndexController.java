package cz.fai.blog.web;

import cz.fai.blog.dto.PostDto;
import cz.fai.blog.manager.PostManager;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by Radomir Sohlich on 10/29/16.
 */
@Named
@RequestScoped
public class IndexController {

    private PostManager postManager;

    @Inject
    public void setPostManager(PostManager postManager) {
        this.postManager = postManager;
    }


    public List<PostDto> getAllPosts() {
        return postManager.getAllPublished();
    }

}

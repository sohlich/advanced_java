package cz.fai.blog.web;

import cz.fai.blog.bean.SessionBean;
import cz.fai.blog.dto.PostDto;
import cz.fai.blog.manager.PostManager;
<<<<<<< HEAD
import cz.fai.utb.mail.client.EmailClient;
import cz.fai.utb.mail.client.EmailMessageBuilder;
=======
>>>>>>> 6f6ecc94441eedad94a281af9787ea4dfe0f84ef

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Map;

/**
 * Created by Radomir Sohlich on 10/31/16.
 */
@Named
@RequestScoped
public class PostController {

    @Inject
    PostManager postManager;

    @Inject
    SessionBean sessionBean;
<<<<<<< HEAD
    
    @Inject
    private EmailClient emailClient;
=======

>>>>>>> 6f6ecc94441eedad94a281af9787ea4dfe0f84ef

    PostDto postDto;


    @PostConstruct
    public void init() {
        Map<String, String> requestMap = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap();

        if (requestMap.get("id") != null && !requestMap.get("id").isEmpty()) {
            postDto = postManager.getOne(Integer.parseInt(requestMap.get
                    ("id")));
        } else {
            postDto = new PostDto();
        }
    }


    public PostDto getPost() {
        return postDto;
    }

    public String save() {
        if (postDto.getId() == null) {
            postManager.createPostFor(sessionBean.getUser().getId(), postDto);
        } else {
            postManager.updatePost(postDto.getId(), postDto);
        }
<<<<<<< HEAD
        
        if(postDto.isPublished()){
            emailClient.sendMessage(new EmailMessageBuilder()
                    .recipient("spacek33@gmail.com")
                    .subject(postDto.getTitle())
                    .text("New update in blog post...")
                    .build());
        }
=======
>>>>>>> 6f6ecc94441eedad94a281af9787ea4dfe0f84ef

        return "index.xhtml";
    }


    public List<PostDto> getUsersPosts() {
        return postManager.getPostsFor(sessionBean.getUser().getId());
    }

}

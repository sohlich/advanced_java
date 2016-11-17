package cz.fai.blog.bean;

import cz.fai.blog.dto.AuthorDto;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by Radomir Sohlich on 10/29/16.
 */
@Named(value = "appSession")
@SessionScoped
public class SessionBean implements Serializable {

    AuthorDto user;


    public AuthorDto getUser() {
        return user;
    }

    public void setUser(AuthorDto user) {
        this.user = user;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fai.blog.controller;

import cz.fai.blog.dto.AuthorDto;
import cz.fai.blog.manager.AuthorManager;
import cz.fai.blog.model.LoginForm;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sohlich
 */
@RequestScoped
@ManagedBean(name = "login")
public class LoginController {

    @Inject
    private AuthorManager authorManeger;

    @ManagedProperty(value = "#{loginForm}")
    private LoginForm loginForm;

    @ManagedProperty(value = "#{session}")
    private HttpSession session;


    public AuthorManager getAuthorManeger() {
        return authorManeger;
    }

    public void setAuthorManeger(AuthorManager authorManeger) {
        this.authorManeger = authorManeger;
    }

    public LoginForm getLoginForm() {
        return loginForm;
    }

    public void setLoginForm(LoginForm loginForm) {
        this.loginForm = loginForm;
    }

    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }

    public String doLogin() throws IOException {
        AuthorDto authorDto
                = authorManeger.findAuthorByEmailAndPassword(loginForm.getEmail(),
                        loginForm.getPassword());
        if (authorDto != null) {
            session.setAttribute("user", authorDto);
            return "welcome";
        }

        return "login";
    }

    public String doLogout() {
        session.invalidate();
        return "login";
    }

}

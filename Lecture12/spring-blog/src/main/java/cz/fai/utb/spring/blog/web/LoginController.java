package cz.fai.utb.spring.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Frantisek Spacek
 */
@Controller
public class LoginController {

    @RequestMapping(value = "/login")
    public String getLoginPage() {
        return "login.html";
    }
}

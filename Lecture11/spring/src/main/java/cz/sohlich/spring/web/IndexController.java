package cz.sohlich.spring.web;

import cz.sohlich.spring.bean.ChildDependentBean;
import cz.sohlich.spring.scan.IndexComponent;
import cz.sohlich.spring.scan.SingletonComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Map;

/**
 * Created by Radomir Sohlich on 11/19/16.
 */
@Controller
@RequestScope
public class IndexController {

    private final IndexComponent prototype;
    private final SingletonComponent singleton;


    @Autowired
    private ChildDependentBean childDependentBean;

    @Autowired
    public IndexController(SingletonComponent singleton,
                           IndexComponent prototype) {
        this.singleton = singleton;
        this.prototype = prototype;
    }

    @RequestMapping(value = "/index",
            method = RequestMethod.GET)
    public String getIndex(Map<String, Object> model) {
        model.put("prototype", prototype.getGreetings());
        model.put("singleton", singleton.getGreeting());
        model.put("child", childDependentBean.getInfo());
        return "index";
    }


}

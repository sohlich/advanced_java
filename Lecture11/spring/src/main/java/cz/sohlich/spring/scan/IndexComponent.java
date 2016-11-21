package cz.sohlich.spring.scan;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Created by Radomir Sohlich on 11/19/16.
 */
@Service
@Scope("prototype")
public class IndexComponent {

    private final String helloMessage;

    public IndexComponent() {
        helloMessage = "Greeting from IndexComponent created in " + System.currentTimeMillis();
    }

    public String getGreetings() {
        return helloMessage;
    }

}

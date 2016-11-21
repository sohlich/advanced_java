package cz.sohlich.spring.scan;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Created by Radomir Sohlich on 11/19/16.
 */
@Service
@Scope("singleton")
public class SingletonComponent {

    private final String helloMessage;

    public SingletonComponent() {
        helloMessage = String.format("Hello from singleton created in %d"
                , System.currentTimeMillis());
    }

    public String getGreeting() {
        return helloMessage;
    }
}

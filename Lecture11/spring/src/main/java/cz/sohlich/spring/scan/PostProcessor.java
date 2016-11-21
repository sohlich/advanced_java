package cz.sohlich.spring.scan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * Created by Radomir Sohlich on 11/19/16.
 */
@Component
public class PostProcessor implements BeanPostProcessor {

    Logger log = LoggerFactory.getLogger(PostProcessor.class);

    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        log.info("Instantiating " + s);
        return o;
    }

    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        log.info("Instantiating " + s + " DONE");
        return o;
    }
}

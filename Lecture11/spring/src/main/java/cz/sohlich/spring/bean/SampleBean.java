package cz.sohlich.spring.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Radomir Sohlich on 11/19/16.
 */
public class SampleBean {

    Logger log = LoggerFactory.getLogger(SampleBean.class);

    public SampleBean() {
        log.info("Instantiating SampleBean");
    }

    public long getTime() {
        return System.currentTimeMillis();
    }
}

package cz.sohlich.spring.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Radomir Sohlich on 11/19/16.
 */
public class DependentBean {

    Logger log = LoggerFactory.getLogger(DependentBean.class);

    private final SampleBean injectedBean;

    public DependentBean(SampleBean injectedBean) {
        this.injectedBean = injectedBean;
        log.info("Instantiating DependentBean");
    }
}

package cz.sohlich.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by Radomír Sohlich (fg8cvg) on 27.9.2016.
 */
public class AppContextListener implements ServletContextListener{

    Logger log = LoggerFactory.getLogger(AppContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        log.info("### Context is destroyed ###");
    }
}

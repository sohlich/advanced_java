package cz.sohlich.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.Set;

/**
 * The class must be registered as service under
 *  META-INF/services/javax.servlet.ServletContainerInitializer
 *
 * Created by Radomír Sohlich
 */
public class AppConfig implements ServletContainerInitializer {

    Logger log = LoggerFactory.getLogger(AppConfig.class);

    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        log.info("---- Application is initialized ----");

        // Register HomeServlet to servlet context.
        // Then it is mapped to url.
        ServletRegistration.Dynamic reg =
                servletContext.addServlet("InitializerServlet", "cz.sohlich.web.InitializerServlet");
        reg.addMapping("/initializer");
    }
}

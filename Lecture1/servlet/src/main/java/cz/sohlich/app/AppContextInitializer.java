package cz.sohlich.app;

import cz.sohlich.web.filter.InitializerFilter;
import java.util.Set;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class must be registered as service under
 *  META-INF/services/javax.servlet.ServletContainerInitializer
 *
 * Created by Radomír Sohlich
 */
public class AppContextInitializer implements ServletContainerInitializer {

    Logger log = LoggerFactory.getLogger(AppContextInitializer.class);

    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        log.info("---- Application is initialized ----");

        FilterRegistration.Dynamic filter
                = servletContext.addFilter("InitializerFilter", new InitializerFilter());
        filter.addMappingForUrlPatterns(null,
                true, "/*");

        // Register HomeServlet to servlet context.
        // Then it is mapped to url.
        ServletRegistration.Dynamic reg =
                servletContext.addServlet("InitializerServlet", "cz.sohlich.web.servlet.InitializerServlet");
        reg.addMapping("/initializer");


    }
}

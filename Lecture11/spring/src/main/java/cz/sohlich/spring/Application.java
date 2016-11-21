package cz.sohlich.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Created by Radomir Sohlich on 11/19/16.
 */
@Configuration
@ImportResource("classpath:application-context.xml")
public class Application {


    @Bean
    public InternalResourceViewResolver getResolver() {
        InternalResourceViewResolver resolver = new
                InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
}

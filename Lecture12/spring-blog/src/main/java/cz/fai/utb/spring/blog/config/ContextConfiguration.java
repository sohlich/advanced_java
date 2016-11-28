package cz.fai.utb.spring.blog.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 *
 * @author Frantisek Spacek
 */
@Configuration
@ComponentScan(basePackages = "cz.fai.utb.spring.blog")
@EnableWebMvc
public class ContextConfiguration {

    @Bean
    @DependsOn("entityManagerFactory")
    public PlatformTransactionManager txManager() {
        final JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory());
        return txManager;
    }

    @Bean
    @DependsOn("dataSource")
    public EntityManagerFactory entityManagerFactory() {
        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        final LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("cz.fai.utb.spring.blog.domain");
        factory.setDataSource(dataSource());
        final Properties props = new Properties();
        props.put("javax.persistence.schema-generation.database.action", "drop-and-create");
        factory.setJpaProperties(props);
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Bean
    public DataSource dataSource() {
        final HikariConfig config = new HikariConfig();
        config.setMaximumPoolSize(10);
        config.setDataSourceClassName("org.h2.jdbcx.JdbcDataSource");
        config.addDataSourceProperty("url", "jdbc:h2:mem:test");
        config.addDataSourceProperty("user", "sa");
        config.addDataSourceProperty("password", "sa");
        return new HikariDataSource(config);
    }

    @Bean
    public DataSourceInitializer dataSourceInitializer(DataSource dataSource) {
        final DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource);
        
        final ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.addScript(new ClassPathResource("data.sql"));
        dataSourceInitializer.setDatabasePopulator(databasePopulator);
        return dataSourceInitializer;
    }
}

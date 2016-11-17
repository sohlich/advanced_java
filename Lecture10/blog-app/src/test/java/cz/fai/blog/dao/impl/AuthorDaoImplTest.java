/*
 */
package cz.fai.blog.dao.impl;

import cz.fai.blog.ApplicationConfig;
import cz.fai.blog.dao.AuthorDao;
import javax.inject.Inject;
import static org.assertj.core.api.Assertions.assertThat;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author František Špaček
 */
@RunWith(Arquillian.class)
public class AuthorDaoImplTest {

    @Inject
    private AuthorDao authorDao;
    
    @Deployment
    public static WebArchive deployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackages(true, ApplicationConfig.class.getPackage())
                .addAsLibraries(Maven.resolver().loadPomFromFile("pom.xml")
                        .importRuntimeAndTestDependencies().resolve().withTransitivity().asFile())
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsResource("META-INF/data.sql", "META-INF/data.sql")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void testFetchAll() {
        assertThat(authorDao.fetchAll()).hasSize(2);
    }

    @Test
    public void testSomething() {
    }

}

/*
 */
package cz.fai.blog.api.rest;

import cz.fai.blog.ApplicationConfig;
import io.restassured.RestAssured;
import java.net.URL;
import org.hamcrest.Matchers;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author František Špaček
 */
@RunWith(Arquillian.class)
public class PostResourceTest {

    @ArquillianResource
    private URL base;

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
    
    @Before
    public void setUp(){
       // RestAssured.baseURI = base.toString();
    }

    @Test
    @RunAsClient
    public void testGetAll() throws Exception {
        System.out.println(base);
        RestAssured.when().get(new URL(base, "api/posts")).then().statusCode(200).body("firstName", 
                Matchers.equalTo("Jozef"));
    }

    @Test
    public void testGetOne() throws Exception {
    }

    @Test
    public void testCreateOne() throws Exception {
    }

}

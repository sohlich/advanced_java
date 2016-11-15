package cz.fai.blog.api.rest;

import cz.fai.blog.ApplicationConfig;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import java.net.URL;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author Frantisek Spacek
 */
@RunWith(Arquillian.class)
public class PostResourceIT {

    @ArquillianResource
    private URL base;

    @Deployment
    public static WebArchive createDeployment() {

        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackages(true, ApplicationConfig.class.getPackage())
                .addAsLibraries(Maven.resolver().loadPomFromFile("pom.xml")
                        .importRuntimeAndTestDependencies().resolve().withTransitivity().asFile())
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    @RunAsClient
    public void testGetAll() throws Exception {
       RestAssured.when().request(Method.GET, new URL(base, "api/posts")).then().statusCode(200);
    }

    @Test
    public void testGetOne() throws Exception {
    }

    @Test
    public void testCreateOne() throws Exception {
    }

}

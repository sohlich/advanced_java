package cz.fai.blog.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import org.junit.Before;

/**
 *
 * @author Frantisek Spacek
 */
public class BaseDaoTest {

    protected EntityManager entityManager;

    @Before
    public void setupEntityManager() {
//        entityManager = Persistence.createEntityManagerFactory("test-unit").createEntityManager();
    }
}

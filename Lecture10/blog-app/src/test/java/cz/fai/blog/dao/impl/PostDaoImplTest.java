package cz.fai.blog.dao.impl;

import cz.fai.blog.dao.PostDao;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 *
 * @author Frantisek Spacek
 */
public class PostDaoImplTest extends BaseDaoTest {

    private PostDao instance;

    @Before
    public void setUp() {
       // instance = new PostDaoImpl(entityManager);
    }

    @Test
    public void testGetPostsBy() {
        //assertEquals(1, instance.getPostsBy(1).size());
    }

    @Test
    public void testCreatePostFor() {
    }

    @Test
    public void testFetchAllPublished() {
    }

}

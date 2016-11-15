/*
 */
package cz.fai.blog.manager;

import cz.fai.blog.dao.AuthorDao;
import cz.fai.blog.domain.AuthorEntity;
import cz.fai.blog.dto.AuthorDto;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author František Špaček
 */
@RunWith(MockitoJUnitRunner.class)
public class AuthorManagerImplTest {

    private static final List<AuthorEntity> AUTHORS = Arrays.asList(
            author(1, "Jozef", "Mrkvicka", "jozef@mrkvicka.cz"),
            author(2, "Alfonz", "Tekvicka", "alfonz@tekvicka.cz"),
            author(3, "Alfred", "Nobel", "alfred@nobel.cz")
    );

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder(new File("D:\\"));

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    private AuthorDao authorDao;

    private AuthorManagerImpl instance;

    @Before
    public void setUp() throws IOException {
        instance = new AuthorManagerImpl();
        instance.setAuthorDao(authorDao);
        temporaryFolder.newFile("testfile");
    }

    @After
    public void tearDown() {
        temporaryFolder.delete();
    }

    @Test
    public void testGetAuthors() throws Exception {
        //prepare
        Mockito.when(authorDao.fetchAll()).thenReturn(AUTHORS);

        final InOrder inOrder = Mockito.inOrder(authorDao);
        //action
        final List<AuthorDto> dtos = instance.getAuthors();

        inOrder.verify(authorDao).something();
        inOrder.verify(authorDao).fetchAll();
        //validation
        //Assert.assertEquals(AUTHORS.size(), dtos.size());
        assertThat(dtos).hasSize(AUTHORS.size());

        for (int i = 0; i < dtos.size(); i++) {
            final AuthorEntity ref = AUTHORS.get(i);
            final AuthorDto get = dtos.get(i);
            assertAuthor(ref, get);
        }
    }

    @Test
    public void testCreateAuthor() throws Exception {
        Mockito.when(authorDao.insert(Mockito.any(AuthorEntity.class)))
                .thenReturn(author(1, "test", "test", "test@test.com"));

        final AuthorDto dto = new AuthorDto(1, "test", "test", "test@test.com");
        final ArgumentCaptor<AuthorEntity> author = ArgumentCaptor.forClass(AuthorEntity.class);

        instance.createAuthor(dto);

        Mockito.verify(authorDao).insert(author.capture());
        assertThat(author.getValue().getFirstName())
                .isEqualTo("test");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateAuthor_nullArg() {
        instance.createAuthor(null);
    }

    @Test
    public void testCreateAuthor_nullArg_2() {
        try {
            instance.createAuthor(null);
        } catch (IllegalArgumentException expected) {
            assertThat(expected).hasMessageContaining("authorDto");
        }
    }
    
    @Test
    public void testCreateAuthor_nullArg_3(){
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("authorDto");
        instance.createAuthor(null);
    }

    @Test
    public void testGetByEmailAndPassword() throws Exception {
    }

    private static AuthorEntity author(int id, String firstname, String lastname, String email) {
        final AuthorEntity entity = new AuthorEntity();
        entity.setId(id);
        entity.setFirstName(firstname);
        entity.setLastName(lastname);
        entity.setEmail(email);
        return entity;
    }

    private void assertAuthor(final AuthorEntity ref, final AuthorDto get) {
        Assert.assertEquals(ref.getFirstName(), get.getFirstName());
        Assert.assertEquals(ref.getLastName(), get.getLastName());
        Assert.assertEquals(ref.getEmail(), get.getEmail());
    }

}

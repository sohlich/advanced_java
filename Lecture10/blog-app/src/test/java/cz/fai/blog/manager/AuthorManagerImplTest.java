<<<<<<< HEAD
=======
/*
 */
>>>>>>> 6f6ecc94441eedad94a281af9787ea4dfe0f84ef
package cz.fai.blog.manager;

import cz.fai.blog.dao.AuthorDao;
import cz.fai.blog.domain.AuthorEntity;
import cz.fai.blog.dto.AuthorDto;
<<<<<<< HEAD
import java.io.IOException;
import static java.util.Arrays.asList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.assertj.core.api.Assertions.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

/**
 *
 * @author Frantisek Spacek
 */
public class AuthorManagerImplTest {

    private static final List<AuthorEntity> AUTHORS = asList(
            author(1, "Jozef", "Mrkvicka", "jozef@mrkvicka.cz"),
            author(1, "Jozef", "Novak", "jozef@novak.cz")
    );
    private static final AuthorDto DTO = dto("Jan", "Novak", "jan@novak.cz");
    
    @Rule
    public TemporaryFolder temp = new TemporaryFolder();

    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    private AuthorDao authorDao;
=======
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

>>>>>>> 6f6ecc94441eedad94a281af9787ea4dfe0f84ef
    private AuthorManagerImpl instance;

    @Before
    public void setUp() throws IOException {
<<<<<<< HEAD
        temp.newFile();
        prepareMocks();
        instance = new AuthorManagerImpl();
        instance.setAuthorDao(authorDao);
=======
        instance = new AuthorManagerImpl();
        instance.setAuthorDao(authorDao);
        temporaryFolder.newFile("testfile");
    }

    @After
    public void tearDown() {
        temporaryFolder.delete();
>>>>>>> 6f6ecc94441eedad94a281af9787ea4dfe0f84ef
    }

    @Test
    public void testGetAuthors() throws Exception {
<<<<<<< HEAD
        final List<AuthorDto> dtos = instance.getAuthors();
        assertEquals(AUTHORS.size(), dtos.size());

        for (int i = 0; i < dtos.size(); i++) {
            AuthorDto get = dtos.get(i);
            AuthorEntity ref = AUTHORS.get(i);
            assertMapping(get, ref);
        }

=======
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
>>>>>>> 6f6ecc94441eedad94a281af9787ea4dfe0f84ef
    }

    @Test
    public void testCreateAuthor() throws Exception {
<<<<<<< HEAD
        Mockito.doReturn(author(1, DTO.getFirstName(), DTO.getLastName(), DTO.getEmail()))
                .when(authorDao).insert(any(AuthorEntity.class));

        final AuthorDto created = instance.createAuthor(DTO);
        assertEquals(1L, created.getId(), 0);
        assertEquals(DTO.getFirstName(), created.getFirstName());
        assertEquals(DTO.getLastName(), created.getLastName());
        assertEquals(DTO.getEmail(), created.getEmail());
    }
    
    @Test
    public void testCreateAuthor_nullArg(){
        exception.expect(IllegalArgumentException.class);
=======
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
>>>>>>> 6f6ecc94441eedad94a281af9787ea4dfe0f84ef
        instance.createAuthor(null);
    }

    @Test
<<<<<<< HEAD
    public void testGetByEmailAndPassword() throws Exception {
        Mockito.when(authorDao.findByEmailAndPassword(eq("jozef@novak.cz"), eq("heslo")))
                .thenReturn(author(1, "Jozef", "Novak", "jozef@novak.cz"));

        final AuthorDto dto = instance.getByEmailAndPassword("jozef@novak.cz", "heslo");
        assertNotNull(dto);
        assertThat(dto.getFirstName()).isEqualTo("Jozef");
    }

    private void prepareMocks() {
        authorDao = mock(AuthorDao.class);
        Mockito.when(authorDao.fetchAll()).thenReturn(AUTHORS);
    }

    private static AuthorEntity author(int id, String firstName, String lastName, String email) {
        final AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setFirstName(firstName);
        authorEntity.setLastName(lastName);
        authorEntity.setEmail(email);
        authorEntity.setId(id);
        return authorEntity;
    }

    private static AuthorDto dto(String firstName, String lastName, String email) {
        return new AuthorDto(null, firstName, lastName, email);
    }

    private void assertMapping(AuthorDto dto, AuthorEntity entity) {
        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getFirstName(), entity.getFirstName());
        assertEquals(dto.getLastName(), entity.getLastName());
        assertEquals(dto.getEmail(), entity.getEmail());
=======
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
>>>>>>> 6f6ecc94441eedad94a281af9787ea4dfe0f84ef
    }

}

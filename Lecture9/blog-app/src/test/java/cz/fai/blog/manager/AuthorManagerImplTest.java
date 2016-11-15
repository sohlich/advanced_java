package cz.fai.blog.manager;

import cz.fai.blog.dao.AuthorDao;
import cz.fai.blog.domain.AuthorEntity;
import cz.fai.blog.dto.AuthorDto;
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
    private AuthorManagerImpl instance;

    @Before
    public void setUp() throws IOException {
        temp.newFile();
        prepareMocks();
        instance = new AuthorManagerImpl();
        instance.setAuthorDao(authorDao);
    }

    @Test
    public void testGetAuthors() throws Exception {
        final List<AuthorDto> dtos = instance.getAuthors();
        assertEquals(AUTHORS.size(), dtos.size());

        for (int i = 0; i < dtos.size(); i++) {
            AuthorDto get = dtos.get(i);
            AuthorEntity ref = AUTHORS.get(i);
            assertMapping(get, ref);
        }

    }

    @Test
    public void testCreateAuthor() throws Exception {
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
        instance.createAuthor(null);
    }

    @Test
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
    }

}

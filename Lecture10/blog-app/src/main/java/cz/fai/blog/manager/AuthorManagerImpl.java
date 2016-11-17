package cz.fai.blog.manager;

import cz.fai.blog.dao.AuthorDao;
import cz.fai.blog.domain.AuthorEntity;
import cz.fai.blog.dto.AuthorDto;
import cz.fai.blog.logging.LogAround;

import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

/**
 * Implementation of {@link AuthorManager}.
 *
 * @author Frantisek Spacek
 */
@LogAround
@Stateless
public class AuthorManagerImpl implements AuthorManager {

    private AuthorDao authorDao;

    @Inject
    public void setAuthorDao(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    public List<AuthorDto> getAuthors() {
        authorDao.something();
        return authorDao.fetchAll().stream()
                .map(AuthorDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public AuthorDto createAuthor(AuthorDto authorDto) {
        if(authorDto == null){
            throw new IllegalArgumentException("authorDto cannot be null");
        }
        final AuthorEntity entity = new AuthorEntity();
        entity.setEmail(authorDto.getEmail());
        entity.setFirstName(authorDto.getFirstName());
        entity.setLastName(authorDto.getLastName());
        return AuthorDto.from(authorDao.insert(entity));
    }

    @Override
    public AuthorDto getByEmailAndPassword(String email, String password) {
        AuthorEntity entity = null;
        try {
            entity = authorDao.findByEmailAndPassword(email,
                    password);
            return AuthorDto.from(entity);
        } catch (NoResultException nrex) {
            return null;
        }
    }

}

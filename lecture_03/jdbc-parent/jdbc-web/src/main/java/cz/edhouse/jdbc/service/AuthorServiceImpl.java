package cz.edhouse.jdbc.service;

import cz.edhouse.jdbc.dao.AuthorDao;
import cz.edhouse.jdbc.dao.PostDao;
import cz.edhouse.jdbc.dto.AuthorDto;
import cz.edhouse.jdbc.dto.PostDto;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Implementation of {@link AuthorService}.
 *
 * @author Frantisek Spacek
 */
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;
    private final PostDao postDao;

    public AuthorServiceImpl(AuthorDao authorDao, PostDao postDao) {
        this.authorDao = Objects.requireNonNull(authorDao, "authorDao cannot be null");
        this.postDao = Objects.requireNonNull(postDao, "postDao cannot be null");
    }

    @Override
    public List<AuthorDto> getAuthors() {
        return authorDao.fetchAll().stream()
                .map(AuthorDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public PostDto createPostFor(int authorId, PostDto post) {
        return PostDto.from(postDao.createPostFor(authorId, PostDto.to(post)));
    }
    

}

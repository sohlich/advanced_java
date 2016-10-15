package cz.edhouse.jpa.service;

import cz.edhouse.jpa.dao.AuthorDao;
import cz.edhouse.jpa.dao.PostDao;
import cz.edhouse.jpa.dto.AuthorDto;
import cz.edhouse.jpa.dto.PostDto;
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

    @Override
    public List<PostDto> getPostFor(int authorId) {
        return postDao.getPostsBy(authorId).stream()
                .map(PostDto::from)
                .collect(Collectors.toList());
    }

}

/*
 */
package cz.fai.blog.manager;

import cz.fai.blog.dao.PostDao;
import cz.fai.blog.domain.PostEntity;
import cz.fai.blog.dto.PostDto;
import cz.fai.blog.exception.RecordNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Implementation of {@link PostManager}.
 *
 * @author František Špaček
 */
@Stateless
public class PostManagerImpl implements PostManager {

    private PostDao postDao;

    @Inject
    public void setPostDao(PostDao postDao) {
        this.postDao = postDao;
    }

    @Override
    public List<PostDto> getAllPublished() {
        return postDao.fetchAllPublished().stream()
                .map(PostDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public PostDto createPostFor(int authorId, PostDto post) {
        return PostDto.from(postDao.createPostFor(authorId, PostDto.to(post)));
    }

    @Override
    public List<PostDto> getPostsFor(int authorId) {
        return postDao.getPostsBy(authorId).stream()
                .map(PostDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public PostDto updatePost(int postId, PostDto post) {
        final PostEntity entity = postDao.fetchOne(postId);
        if (entity == null) {
            throw new RecordNotFoundException("Record with id %d not found", postId);
        }

        entity.setTitle(post.getTitle());
        entity.setContent(post.getContent());
        entity.setPublished(post.isPublished());
        return PostDto.from(postDao.update(entity));
    }

}

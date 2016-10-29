/*
 */
package cz.fai.blog.manager;

import cz.fai.blog.dao.PostDao;
import cz.fai.blog.domain.PostEntity;
import cz.fai.blog.dto.PostDto;
import cz.fai.blog.exception.RecordNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of {@link PostManager}.
 *
 * @author František Špaček
 */
@Stateless
public class PostManagerImpl implements PostManager {

    Logger log = LoggerFactory.getLogger(PostManagerImpl.class);

    private PostDao postDao;

    @Inject
    public void setPostDao(PostDao postDao) {
        this.postDao = postDao;
    }

    @Override
    public PostDto getOne(Integer id) {
        return PostDto.from(postDao.fetchOne(id));
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

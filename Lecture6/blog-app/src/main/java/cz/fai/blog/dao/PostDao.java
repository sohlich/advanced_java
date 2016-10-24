package cz.fai.blog.dao;

import cz.fai.blog.domain.PostEntity;
import java.util.List;

/**
 *
 * @author Frantisek Spacek
 */
public interface PostDao extends CrudDao<PostEntity, Integer>{
    
    List<PostEntity> getPostsBy(int authorId);
    
    PostEntity createPostFor(int authorId, PostEntity postEntity);
    
    List<PostEntity> fetchAllPublished();
}

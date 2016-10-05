package cz.edhouse.jdbc.dao;

import cz.edhouse.jdbc.domain.PostEntity;
import java.util.List;

/**
 *
 * @author Frantisek Spacek
 */
public interface PostDao {
    
    List<PostEntity> getPostsBy(int authorId);
    
    PostEntity createPostFor(int authorId, PostEntity postEntity);
}

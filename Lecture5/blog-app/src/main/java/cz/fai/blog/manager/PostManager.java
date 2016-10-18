/*
 */
package cz.fai.blog.manager;

import cz.fai.blog.dto.PostDto;
import java.util.List;

/**
 * Defines management over stored posts.
 *
 * @author František Špaček
 */
public interface PostManager {

    List<PostDto> getAllPublished();

    PostDto createPostFor(int authorId, PostDto post);

    List<PostDto> getPostsFor(int authorId);

    PostDto updatePost(int postId, PostDto post);
}

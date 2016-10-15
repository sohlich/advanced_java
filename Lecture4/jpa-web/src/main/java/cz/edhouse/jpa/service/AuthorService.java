package cz.edhouse.jpa.service;

import cz.edhouse.jpa.dto.AuthorDto;
import cz.edhouse.jpa.dto.PostDto;
import java.util.List;

/**
 *
 * @author Frantisek Spacek
 */
public interface AuthorService {
    
    List<AuthorDto> getAuthors();
    
    PostDto createPostFor(int authorId, PostDto post);
    
    List<PostDto> getPostFor(int authorId);
}

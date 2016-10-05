package cz.edhouse.jdbc.service;

import cz.edhouse.jdbc.dto.AuthorDto;
import cz.edhouse.jdbc.dto.PostDto;
import java.util.List;

/**
 *
 * @author Frantisek Spacek
 */
public interface AuthorService {
    
    List<AuthorDto> getAuthors();
    
    PostDto createPostFor(int authorId, PostDto post);
}

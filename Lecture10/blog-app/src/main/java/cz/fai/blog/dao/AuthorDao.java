package cz.fai.blog.dao;

import cz.fai.blog.domain.AuthorEntity;

/**
 * @author Frantisek Spacek
 */
public interface AuthorDao extends CrudDao<AuthorEntity, Integer> {

    public AuthorEntity findByEmailAndPassword(String email, String password);
    
    public void something();

}

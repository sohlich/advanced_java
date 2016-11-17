package cz.fai.blog.dao;

import cz.fai.blog.domain.AuthorEntity;

/**
 * @author Frantisek Spacek
 */
public interface AuthorDao extends CrudDao<AuthorEntity, Integer> {

    public AuthorEntity findByEmailAndPassword(String email, String password);
<<<<<<< HEAD
=======
    
    public void something();
>>>>>>> 6f6ecc94441eedad94a281af9787ea4dfe0f84ef

}

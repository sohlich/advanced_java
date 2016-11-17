package cz.fai.blog.dao.impl;

import cz.fai.blog.dao.AuthorDao;
import cz.fai.blog.domain.AuthorEntity;
<<<<<<< HEAD

=======
>>>>>>> 6f6ecc94441eedad94a281af9787ea4dfe0f84ef
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

@Named
@RequestScoped
public class AuthorDaoImpl extends SimpleCrudDao<AuthorEntity, Integer> implements AuthorDao {

    @Inject
    public AuthorDaoImpl(EntityManager entityManager) {
        super(entityManager, AuthorEntity.class);
    }


<<<<<<< HEAD
    @Override
=======
>>>>>>> 6f6ecc94441eedad94a281af9787ea4dfe0f84ef
    public AuthorEntity findByEmailAndPassword(String email, String password) {
        return entityManager.createQuery("select a from AuthorEntity a where a.email=:email and a.password=:password",
                AuthorEntity.class)
                .setParameter("email", email)
                .setParameter("password", password)
                .getSingleResult();
    }
<<<<<<< HEAD
=======

    @Override
    public void something() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
>>>>>>> 6f6ecc94441eedad94a281af9787ea4dfe0f84ef
}

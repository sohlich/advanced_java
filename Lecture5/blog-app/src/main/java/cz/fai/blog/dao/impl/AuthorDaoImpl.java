package cz.fai.blog.dao.impl;

import cz.fai.blog.dao.AuthorDao;
import cz.fai.blog.domain.AuthorEntity;
import java.util.List;
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

    @Override
    public AuthorEntity byEmailAndPassword(String email, String password) {
        List<AuthorEntity> authors
                = entityManager.createNamedQuery("authorByEmailAndPassword", entityClass)
                .setParameter("email", email)
                .setParameter("pass", password)
                .getResultList();
        if (authors.size() > 0) {
            return authors.get(0);
        }
        return null;
    }

}

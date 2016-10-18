package cz.fai.blog.dao.impl;

import cz.fai.blog.dao.AuthorDao;
import cz.fai.blog.domain.AuthorEntity;
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

}

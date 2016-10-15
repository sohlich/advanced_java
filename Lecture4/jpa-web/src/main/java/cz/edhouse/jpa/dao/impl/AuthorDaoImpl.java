package cz.edhouse.jpa.dao.impl;

import cz.edhouse.jpa.dao.AuthorDao;
import cz.edhouse.jpa.domain.AuthorEntity;
import javax.persistence.EntityManager;

public class AuthorDaoImpl extends SimpleCrudDao<AuthorEntity, Integer> implements AuthorDao {

    public AuthorDaoImpl(EntityManager entityManager) {
        super(entityManager, AuthorEntity.class);
    }

}

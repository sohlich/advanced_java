package cz.edhouse.jpa.dao.impl;

import cz.edhouse.jpa.dao.PostDao;
import cz.edhouse.jpa.domain.AuthorEntity;
import cz.edhouse.jpa.domain.PostEntity;
import cz.edhouse.jpa.exception.InternalStorageException;
import cz.edhouse.jpa.exception.RecordNotFoundException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class PostDaoImpl extends SimpleCrudDao<PostEntity, Integer> implements PostDao {

    public PostDaoImpl(EntityManager entityManager) {
        super(entityManager, PostEntity.class);
    }

    @Override
    public List<PostEntity> getPostsBy(int authorId) {
        return entityManager.createNamedQuery("getByAuthor").setParameter("authorId", authorId).getResultList();
    }

    @Override
    public PostEntity createPostFor(int authorId, PostEntity postEntity) {
        EntityTransaction tx = null;
        try {
            tx = entityManager.getTransaction();
            tx.begin();
            final AuthorEntity authorEntity = entityManager.find(AuthorEntity.class, authorId);

            if (authorEntity == null) {
                throw new RecordNotFoundException("author with id %s not found", authorId);
            }
            authorEntity.addPost(postEntity);
            entityManager.persist(authorEntity);
            tx.commit();
        } catch (Exception ex) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw new InternalStorageException("Unable to create post", ex);
        }

        return postEntity;
    }
}

package cz.fai.blog.dao.impl;

import cz.fai.blog.dao.PostDao;
import cz.fai.blog.domain.AuthorEntity;
import cz.fai.blog.domain.PostEntity;
import cz.fai.blog.exception.RecordNotFoundException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Named
@RequestScoped
public class PostDaoImpl extends SimpleCrudDao<PostEntity, Integer> implements PostDao {

    @Inject
    public PostDaoImpl(EntityManager entityManager) {
        super(entityManager, PostEntity.class);
    }

    @Override
    public List<PostEntity> getPostsBy(int authorId) {
        return entityManager.createNamedQuery("getByAuthor", PostEntity.class)
                .setParameter("authorId", authorId)
                .getResultList();
    }

    @Override
    public PostEntity createPostFor(int authorId, PostEntity postEntity) {
        final AuthorEntity authorEntity = entityManager.find(AuthorEntity.class, authorId);
        if (authorEntity == null) {
            throw new RecordNotFoundException("author with id %s not found", authorId);
        }

        authorEntity.addPost(postEntity);
        entityManager.persist(authorEntity);

        return postEntity;
    }

    @Override
    public List<PostEntity> fetchAllPublished() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PostEntity> query = cb.createQuery(PostEntity.class);
        Root<PostEntity> c = query.from(PostEntity.class);
        query.where(cb.equal(c.get("published"), true));
        return entityManager.createQuery(query).getResultList();
    }
}

package cz.fai.blog.dao.impl;

import cz.fai.blog.dao.CrudDao;
import cz.fai.blog.domain.BaseEntity;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author František Špaček
 * @param <EntityType>
 * @param <PrimaryKeyType>
 */
public abstract class SimpleCrudDao<EntityType extends BaseEntity, PrimaryKeyType extends Serializable>
        implements CrudDao<EntityType, PrimaryKeyType> {

    protected final Class<EntityType> entityClass;

    protected final EntityManager entityManager;

    public SimpleCrudDao(EntityManager entityManager, Class<EntityType> entityClass) {
        this.entityManager = Objects.requireNonNull(entityManager,
                "entityManager cannot be null");
        this.entityClass = Objects.requireNonNull(entityClass,
                "entityClass cannot be null");
    }

    @Override
    public List<EntityType> fetchAll() {
        final CriteriaQuery<EntityType> findAllQuery = entityManager
                .getCriteriaBuilder().createQuery(entityClass);
        final Root<EntityType> c = findAllQuery.from(entityClass);
        findAllQuery.select(c);

        return entityManager.createQuery(findAllQuery)
                .getResultList();
    }

    @Override
    public EntityType insert(EntityType t) {
        entityManager.persist(t);
        return t;
    }

    @Override
    public EntityType fetchOne(PrimaryKeyType id) {
        return entityManager.find(entityClass, id);
    }

    @Override
    public EntityType update(EntityType t) {
        return entityManager.merge(t);
    }

    @Override
    public void delete(EntityType t) {
        t = entityManager.merge(t);
        entityManager.remove(t);
    }
}

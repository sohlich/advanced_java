package cz.edhouse.jdbc.dao;

import java.util.List;

/**
 *
 * @author Frantisek Spacek
 * @param <EntityType>
 * @param <PrimaryKeyType>
 */
public interface CrudDao<EntityType,PrimaryKeyType> {
    
    List<EntityType> fetchAll();

    EntityType fetchOne(PrimaryKeyType id);

    EntityType insert(EntityType entity);

    EntityType update(EntityType entity);

    void delete(EntityType entity);
}

package cz.edhouse.jdbc.dao.impl;

import cz.edhouse.jdbc.dao.CrudDao;
import cz.edhouse.jdbc.domain.BaseEntity;
import java.util.Objects;
import javax.sql.DataSource;

/**
 * Abstract implementation of {@link CrudDao} which provides default constructor for all implementations.
 *
 * @author Frantisek Spacek
 * @param <EntityType> type of entity
 * @param <PrimaryKeyType> primary key type
 */
public abstract class BaseDao<EntityType extends BaseEntity, PrimaryKeyType extends Number> {

    protected final DataSource dataSource;

    public BaseDao(DataSource dataSource) {
        this.dataSource = Objects.requireNonNull(dataSource, "dataSource cannot be null");
    }
}

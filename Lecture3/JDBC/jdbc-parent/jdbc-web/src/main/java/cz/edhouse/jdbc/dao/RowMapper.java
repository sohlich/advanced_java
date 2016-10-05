package cz.edhouse.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Frantisek Spacek
 * @param <EntityType>
 */
public interface RowMapper<EntityType> {

    EntityType processRow(ResultSet resultSet) throws SQLException;
}

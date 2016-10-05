package cz.edhouse.jdbc.dao.impl;

import cz.edhouse.jdbc.dao.AuthorDao;
import cz.edhouse.jdbc.dao.RowMapper;
import cz.edhouse.jdbc.domain.AuthorEntity;
import cz.edhouse.persistence.exception.InternalStorageException;
import cz.edhouse.persistence.exception.RecordNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

/**
 * Implementation of {@link AuthorDao}.
 *
 * @author Frantisek Spacek
 */
public class AuthorDaoImpl extends BaseDao<AuthorEntity, Integer> implements AuthorDao {

    private static final String SELECT_ALL_QUERY = "SELECT id, firstname, lastname, email from AUTHOR ";
    private static final String SELECT_ONE_QUERY = SELECT_ALL_QUERY + " where id = ?";
    private static final String DELETE_QUERY = "DELETE FROM AUTHOR where id = ?";
    private static final String INSERT_QUERY = "INSERT INTO AUTHOR(firstname,lastname, email) VALUES (?,?,?)";
    private static final String UPDATE_QUERY = "UPDATE AUTHOR SET firstname = ?, lastname=?, email = ? WHERE id = ?";

    public AuthorDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<AuthorEntity> fetchAll() {
        final List<AuthorEntity> authors = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement(SELECT_ALL_QUERY);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                authors.add(new AuthorMapper().processRow(rs));
            }

        } catch (SQLException ex) {
            throw new InternalStorageException("unable to fetch all authors", ex);
        }
        return authors;
    }

    @Override
    public AuthorEntity fetchOne(Integer id) {
        try (Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement(SELECT_ONE_QUERY)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new AuthorMapper().processRow(rs);
                }
            }

        } catch (SQLException ex) {
            throw new InternalStorageException("unable to fetch one author", ex);
        }
        throw new RecordNotFoundException("record with id %s not found ", id);
    }

    @Override
    public AuthorEntity insert(AuthorEntity entity) {
        try (Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement(INSERT_QUERY, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, entity.getFirstName());
            ps.setString(2, entity.getLastName());
            ps.setString(3, entity.getEmail());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    entity.setId(rs.getInt(1));
                }
            }

        } catch (SQLException ex) {
            throw new InternalStorageException("unable to fetch one author", ex);
        }
        return entity;
    }

    @Override
    public AuthorEntity update(AuthorEntity entity) {
        try (Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY)) {
            ps.setString(1, entity.getFirstName());
            ps.setString(2, entity.getLastName());
            ps.setString(3, entity.getEmail());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new InternalStorageException("unable to fetch one author", ex);
        }
        return entity;
    }

    @Override
    public void delete(AuthorEntity entity) {
        try (Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement(DELETE_QUERY)) {
            ps.setInt(1, entity.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new InternalStorageException("unable to delete author", ex);
        }
    }

    private static class AuthorMapper implements RowMapper<AuthorEntity> {

        @Override
        public AuthorEntity processRow(ResultSet rs) throws SQLException {
            final AuthorEntity author = new AuthorEntity();
            author.setId(rs.getInt("id"));
            author.setFirstName(rs.getString("firstname"));
            author.setLastName(rs.getString("lastname"));
            author.setEmail(rs.getString("email"));
            return author;
        }
    }
}

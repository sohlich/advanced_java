package cz.edhouse.jdbc.dao.impl;

import cz.edhouse.jdbc.dao.PostDao;
import cz.edhouse.jdbc.dao.RowMapper;
import cz.edhouse.jdbc.domain.PostEntity;
import cz.edhouse.persistence.exception.InternalStorageException;
import static java.lang.String.format;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public class PostDaoImpl extends BaseDao<PostEntity, Integer> implements PostDao {

    private static final String SELECT_BY_AUTHOR_QUERY = "SELECT * FROM POST WHERE author_id = ?";
    private static final String CREATE_POST_FOR_QUERY = "INSERT INTO POST(title, content, published, author_id) VALUES(?,?,?,?)";

    public PostDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<PostEntity> getPostsBy(int authorId) {
        final List<PostEntity> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement(SELECT_BY_AUTHOR_QUERY);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                result.add(new PostEntityMapper().processRow(rs));
            }
        } catch (SQLException ex) {
            throw new InternalStorageException(format("Unable to fetch posts for author with id %s", authorId), ex);
        }
        return result;
    }

    @Override
    public PostEntity createPostFor(int authorId, PostEntity post) {
        try (Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement(CREATE_POST_FOR_QUERY, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, post.getTitle());
            ps.setString(2, post.getContent());
            ps.setBoolean(3, post.isPublished());
            ps.setInt(4, authorId);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    post.setId(rs.getInt(1));
                }
            }
        } catch (SQLException ex) {
            throw new InternalStorageException(format("Unable to create post for author %s", authorId), ex);
        }
        return post;
    }

    private static class PostEntityMapper implements RowMapper<PostEntity> {

        @Override
        public PostEntity processRow(ResultSet resultSet) throws SQLException {
            final PostEntity postEntity = new PostEntity();
            postEntity.setAuthorId(resultSet.getInt("author_id"));
            postEntity.setTitle(resultSet.getString("title"));
            postEntity.setContent(resultSet.getString("content"));
            postEntity.setPublished(resultSet.getBoolean("published"));
            return postEntity;
        }

    }
}

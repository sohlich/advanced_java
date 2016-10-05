package cz.edhouse.jdbc.simple;

import static cz.edhouse.jdbc.simple.Constants.JBDC_URL;
import static cz.edhouse.jdbc.simple.Constants.SELECT_NOW_QUERY;
import static java.lang.String.format;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.h2.jdbcx.JdbcDataSource;

/**
 * DataSource runner show usage of DataSource API.
 *
 * @author Frantisek Spacek
 */
public class DataSourceRunner {

    /**
     * Main method of runner.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        final JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setUrl(JBDC_URL);
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(SELECT_NOW_QUERY);
                ResultSet rs = statement.executeQuery()) {
            if (rs.next()) {
                System.out.println(format("Current time %s", rs.getDate(1)));
            }

        } catch (SQLException ex) {
            System.err.println(ex);
            throw new Error(ex);
        }
    }

}

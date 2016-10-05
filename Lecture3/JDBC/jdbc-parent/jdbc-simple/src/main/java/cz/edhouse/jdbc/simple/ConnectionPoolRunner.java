package cz.edhouse.jdbc.simple;

import static java.lang.String.format;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.h2.jdbcx.JdbcConnectionPool;

/**
 * Runner which shows usage of DJBC driver manager.
 *
 * @author Frantisek Spacek
 */
public class ConnectionPoolRunner {

    /**
     * Main method of runner.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Usage of connection pool which is bundled in H2 JDBC driver
        final JdbcConnectionPool connectionPool = JdbcConnectionPool.create(Constants.JBDC_URL, "", "");
        try {
            try (Connection connection = connectionPool.getConnection();
                    PreparedStatement statement = connection.prepareStatement(Constants.SELECT_NOW_QUERY);
                    ResultSet rs = statement.executeQuery()) {

                if (rs.next()) {
                    System.out.println(format("Current time %s", rs.getDate(1)));
                }

            } catch (SQLException ex) {
                System.err.println(ex);
                throw new Error(ex);
            }
        } finally {
            connectionPool.dispose();
        }

    }
}

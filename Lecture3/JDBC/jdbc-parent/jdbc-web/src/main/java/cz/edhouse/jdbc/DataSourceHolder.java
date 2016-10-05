package cz.edhouse.jdbc;

import static cz.edhouse.jdbc.config.Constants.JBDC_URL;
import static cz.edhouse.jdbc.config.Constants.JDBC_PASSWORD;
import static cz.edhouse.jdbc.config.Constants.JDBC_USERNAME;
import javax.sql.DataSource;
import org.h2.jdbcx.JdbcDataSource;

/**
 *
 * @author Frantisek Spacek
 */
public enum DataSourceHolder {

    INSTANCE;

    private final DataSource dataSource;

    private DataSourceHolder() {
        this.dataSource = createDataSource();
    }

    public static DataSource getDataSource() {
        return INSTANCE.dataSource;
    }

    private DataSource createDataSource() {
        final JdbcDataSource ds = new JdbcDataSource();
        ds.setUrl(JBDC_URL);
        ds.setUser(JDBC_USERNAME);
        ds.setPassword(JDBC_PASSWORD);
        return ds;
    }
}

package cz.edhouse.jdbc.simple;

import static cz.edhouse.jdbc.simple.Constants.JBDC_URL;
import static cz.edhouse.jdbc.simple.Constants.SELECT_NOW_QUERY;
import static java.lang.String.format;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Runner which shows usage of DJBC driver manager.
 *
 * @author Frantisek Spacek
 */
public class DriverManagerRunner {

    /**
     * Main method of runner.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(JBDC_URL);
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

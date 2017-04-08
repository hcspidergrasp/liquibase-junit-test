package liquibase.ext.tests.integration;

import liquibase.ext.tests.annotations.LiquibaseTest;
import liquibase.ext.tests.runners.LiquibaseTestRunner;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

@RunWith(LiquibaseTestRunner.class)
@LiquibaseTest
public class ClassScopeMigrationTest {

    private static final String QUERY_COUNT_TAGS = "select count(*) from tags";

    private static Connection connection;

    @BeforeClass
    public static void init() throws Exception {
        Class.forName("org.h2.Driver");
        connection = DriverManager.getConnection("jdbc:h2:./test", "root", "root");
    }

    @Test
    public void shouldRunWithoutExceptions() throws SQLException {
        ResultSet resultSet = connection.createStatement()
                .executeQuery(QUERY_COUNT_TAGS);
        resultSet.next();
        Assert.assertEquals(1, resultSet.getInt(1));
    }

    @Test
    public void alsoShouldRunWithoutExceptions() throws SQLException {
        ResultSet resultSet = connection.createStatement()
                .executeQuery(QUERY_COUNT_TAGS);
        resultSet.next();
        Assert.assertEquals(1, resultSet.getInt(1));
    }

    @Test
    public void anotherOneShouldRunWithoutExceptions() throws SQLException {
        ResultSet resultSet = connection.createStatement()
                .executeQuery(QUERY_COUNT_TAGS);
        resultSet.next();
        Assert.assertEquals(1, resultSet.getInt(1));
    }

    @AfterClass
    public static void finish() throws SQLException {
        boolean error = false;
        try {
            connection.createStatement()
                    .executeQuery(QUERY_COUNT_TAGS);
        } catch (SQLException e) {
            error = true;
        }
        Assert.assertTrue(error);
        connection.close();
    }
}

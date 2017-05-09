package liquibase.ext.tests.examples;

import liquibase.ext.tests.runners.LiquibaseTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

@RunWith(LiquibaseTestRunner.class)
public class TestClassWithoutMigrations {

    @Test
    public void example() {
        assertTrue(true);
    }
}

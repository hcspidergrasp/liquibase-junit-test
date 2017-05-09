package liquibase.ext.tests.examples;

import liquibase.ext.tests.annotations.LiquibaseTest;
import liquibase.ext.tests.runners.LiquibaseTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

@RunWith(LiquibaseTestRunner.class)
@LiquibaseTest(changeLogFile = "src/test/resources/changelog.xml")
public class ClassScopeDefinedChangeLog {

    @Test
    public void example() {
        assertTrue(true);
    }
}

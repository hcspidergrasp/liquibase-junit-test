package liquibase.ext.tests.examples;

import liquibase.ext.tests.annotations.LiquibaseTest;
import liquibase.ext.tests.runners.LiquibaseTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

@RunWith(LiquibaseTestRunner.class)
@LiquibaseTest(contexts = {"test", "foo", "moo"})
public class ClassScopeDefinedContexts {

    @Test
    public void example() {
        assertTrue(true);
    }

}

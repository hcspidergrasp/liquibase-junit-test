package liquibase.ext.tests.listeners;

import liquibase.ext.tests.annotations.LiquibaseTest;
import liquibase.integration.commandline.Main;
import org.junit.runner.Description;
import org.junit.runner.notification.RunListener;

/**
 * JUnit test listener which invokes Liquibase commands during test lifecycle.
 */
public class LiquibaseJUnitTestListener extends RunListener {

    /** Flag to indicate that schema was updated before atomic test was started */
    private boolean updated = false;

    /**
     * Runs before atomic test start.
     * Checks if test has Liquibase specific annotations and runs
     * Liquibase with given command line arguments.
     *
     * @param description - Test description.
     */
    @Override
    public void testStarted(Description description) throws Exception {
        LiquibaseTest liquibaseTest = description.getAnnotation(LiquibaseTest.class);
        if (liquibaseTest != null) {
            Main.run(new String[]{"update"});
            this.updated = true;
        }
    }

    /**
     * Runs after atomic test finished.
     * If update flag is up - drops all database objects in schema.
     *
     * @param description - Test description.
     */
    @Override
    public void testFinished(Description description) throws Exception {
        if (updated) {
            Main.run(new String[]{"dropAll"});
            updated = false;
        }
    }
}

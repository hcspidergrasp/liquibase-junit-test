package liquibase.ext.tests.listeners;

import liquibase.ext.tests.LiquibaseTaskLauncher;
import liquibase.ext.tests.annotations.LiquibaseTest;
import org.junit.runner.Description;
import org.junit.runner.notification.RunListener;

/**
 * JUnit test listener which invokes Liquibase commands during test lifecycle.
 */
public class LiquibaseJUnitTestListener extends RunListener {

    /** Flag to indicate that schema was updated before atomic test was started. */
    private boolean updated = false;
    private int index;
    private int count;

    public LiquibaseJUnitTestListener() {
    }

    public LiquibaseJUnitTestListener(boolean updated, int count) {
        this.updated = updated;
        this.count = count;
    }

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
            LiquibaseTaskLauncher.update();
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
        index++;
        if ((updated && count == 1) || (updated && count > 1 && index >= count)) {
            LiquibaseTaskLauncher.dropAll();
            updated = false;
        }
    }
}

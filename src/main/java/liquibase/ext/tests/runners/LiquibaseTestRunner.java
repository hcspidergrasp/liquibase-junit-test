package liquibase.ext.tests.runners;

import liquibase.ext.tests.utils.LiquibaseTaskLauncher;
import liquibase.ext.tests.annotations.LiquibaseTest;
import liquibase.ext.tests.listeners.LiquibaseJUnitTestListener;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

/**
 * JUnit test runner which allows to run tests with Liquibase migrations.
 * It watches if test marked with {@link liquibase.ext.tests.annotations.LiquibaseTest}
 * annotation. All marked tests run with fresh database schema created with Liquibase.
 */
public class LiquibaseTestRunner extends BlockJUnit4ClassRunner {

    /**
     * Creates a BlockJUnit4ClassRunner to run {@code klass}
     *
     * @param klass - JUnit test class.
     * @throws InitializationError if the test class is malformed.
     */
    public LiquibaseTestRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }

    /**
     * Runs atomic test with {@link LiquibaseJUnitTestListener}.
     *
     * @param notifier - Notifies about test running steps.
     */
    @Override
    public void run(RunNotifier notifier) {
        LiquibaseTest liquibaseTest = getTestClass().getAnnotation(LiquibaseTest.class);
        if (liquibaseTest == null) {
            notifier.addListener(new LiquibaseJUnitTestListener());
        } else {
            LiquibaseTaskLauncher.update(liquibaseTest);
        }
        super.run(notifier);
        if (liquibaseTest != null) {
            LiquibaseTaskLauncher.dropAll();
        }
    }
}

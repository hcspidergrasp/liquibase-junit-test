package liquibase.ext.tests.runners;

import liquibase.ext.tests.LiquibaseTaskLauncher;
import liquibase.ext.tests.annotations.LiquibaseTest;
import liquibase.ext.tests.examples.ClassScopeDefinedChangeLog;
import liquibase.ext.tests.examples.ClassScopeMigrationTest;
import liquibase.ext.tests.examples.TestClassWithoutMigrations;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;
import org.mockito.ArgumentMatcher;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

@RunWith(PowerMockRunner.class)
@PrepareForTest(LiquibaseTaskLauncher.class)
public class LiquibaseTestRunnerTest {

    private LiquibaseTestRunner runner;

    @Test
    public void shouldNotRunMigrationsWhenNoAnnotations() throws Exception {
        PowerMockito.mockStatic(LiquibaseTaskLauncher.class);

        runner = new LiquibaseTestRunner(TestClassWithoutMigrations.class);
        runner.run(new RunNotifier());

        PowerMockito.verifyStatic(never());
        LiquibaseTaskLauncher.update();

        PowerMockito.verifyStatic(never());
        LiquibaseTaskLauncher.dropAll();
    }

    @Test
    public void shouldRunMigrationsForClass() throws InitializationError {
        PowerMockito.mockStatic(LiquibaseTaskLauncher.class);

        runner = new LiquibaseTestRunner(ClassScopeMigrationTest.class);
        runner.run(new RunNotifier());

        PowerMockito.verifyStatic(times(1));
        LiquibaseTaskLauncher.update(argThat(new ArgumentMatcher<LiquibaseTest>() {
            @Override
            public boolean matches(Object argument) {
                LiquibaseTest annotation = (LiquibaseTest) argument;
                return annotation.changeLogFile().length() == 0;
            }
        }));

        PowerMockito.verifyStatic(times(1));
        LiquibaseTaskLauncher.dropAll();
    }

    @Test
    public void shouldRunMigrationsForClassWithDefinedChangeLog() throws Exception {
        PowerMockito.mockStatic(LiquibaseTaskLauncher.class);

        runner = new LiquibaseTestRunner(ClassScopeDefinedChangeLog.class);
        runner.run(new RunNotifier());

        PowerMockito.verifyStatic(times(1));
        LiquibaseTaskLauncher.update(argThat(new ArgumentMatcher<LiquibaseTest>() {
            @Override
            public boolean matches(Object argument) {
                LiquibaseTest annotation = (LiquibaseTest) argument;
                return annotation.changeLogFile().equals("src/test/resources/changelog.xml");
            }
        }));

        PowerMockito.verifyStatic(times(1));
        LiquibaseTaskLauncher.dropAll();
    }
}

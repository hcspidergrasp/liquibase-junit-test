package liquibase.ext.tests.listeners;

import liquibase.ext.tests.LiquibaseTaskLauncher;
import liquibase.ext.tests.annotations.LiquibaseTest;
import liquibase.ext.tests.examples.TestClassWithoutMigrations;
import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.lang.annotation.Annotation;

import static org.mockito.Matchers.any;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(PowerMockRunner.class)
@PrepareForTest(LiquibaseTaskLauncher.class)
public class LiquibaseJUnitTestListenerTest {

    @Test
    public void shouldRunUpdateIfMethodIsAnnotated() throws Exception {
        LiquibaseTest annotation = new LiquibaseTest() {

            @Override
            public String changeLogFile() {
                return "";
            }

            @Override
            public Class<? extends Annotation> annotationType() {
                return LiquibaseTest.class;
            }

        };
        Description description = Description.createSuiteDescription("Foo", annotation);

        LiquibaseJUnitTestListener listener = new LiquibaseJUnitTestListener();
        PowerMockito.mockStatic(LiquibaseTaskLauncher.class);

        listener.testStarted(description);

        PowerMockito.verifyStatic(times(1));
        LiquibaseTaskLauncher.update(annotation);
    }

    @Test
    public void shouldRunDropAllIfMethodIsAnnotated() throws Exception {
        LiquibaseTest annotation = new LiquibaseTest() {

            @Override
            public String changeLogFile() {
                return "";
            }

            @Override
            public Class<? extends Annotation> annotationType() {
                return LiquibaseTest.class;
            }

        };
        Description description = Description.createSuiteDescription("Foo", annotation);

        LiquibaseJUnitTestListener listener = new LiquibaseJUnitTestListener();
        PowerMockito.mockStatic(LiquibaseTaskLauncher.class);
        Whitebox.setInternalState(listener, "updated", true);

        listener.testFinished(description);

        PowerMockito.verifyStatic(times(1));
        LiquibaseTaskLauncher.dropAll();
    }

    @Test
    public void shouldRunUpdateIfMethodIsAnnotatedWithSpecifiedChangeLog() throws Exception {
        LiquibaseTest annotation = new LiquibaseTest() {

            @Override
            public String changeLogFile() {
                return "src/test/resources/changelog.xml";
            }

            @Override
            public Class<? extends Annotation> annotationType() {
                return LiquibaseTest.class;
            }

        };
        Description description = Description.createSuiteDescription("Foo", annotation);

        LiquibaseJUnitTestListener listener = new LiquibaseJUnitTestListener();
        PowerMockito.mockStatic(LiquibaseTaskLauncher.class);

        listener.testStarted(description);

        PowerMockito.verifyStatic(times(1));
        LiquibaseTaskLauncher.update(annotation);
    }
}

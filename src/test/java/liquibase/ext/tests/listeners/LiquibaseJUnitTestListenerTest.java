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

    private LiquibaseTest annotation = new LiquibaseTest() {

        @Override
        public String changeLogFile() {
            return "";
        }

        @Override
        public Class<? extends Annotation> annotationType() {
            return LiquibaseTest.class;
        }

    };

    private Description description = Description.createSuiteDescription("Foo", annotation);

    @Test
    public void shouldRunUpdateIfMethodIsAnnotated() throws Exception {
        LiquibaseJUnitTestListener listener = new LiquibaseJUnitTestListener();
        PowerMockito.mockStatic(LiquibaseTaskLauncher.class);

        listener.testStarted(description);

        PowerMockito.verifyStatic(times(1));
        LiquibaseTaskLauncher.update();
    }

    @Test
    public void shouldRunDropAllIfMethodIsAnnotated() throws Exception {
        LiquibaseJUnitTestListener listener = new LiquibaseJUnitTestListener();
        PowerMockito.mockStatic(LiquibaseTaskLauncher.class);
        Whitebox.setInternalState(listener, "updated", true);

        listener.testFinished(description);

        PowerMockito.verifyStatic(times(1));
        LiquibaseTaskLauncher.dropAll();
    }
}

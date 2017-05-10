package liquibase.ext.tests;

import liquibase.exception.LiquibaseException;
import liquibase.ext.tests.annotations.LiquibaseTest;
import liquibase.ext.tests.exceptions.LiquibaseMigrationRunException;
import liquibase.ext.tests.utils.LiquibaseTaskLauncher;
import liquibase.integration.commandline.Main;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.lang.annotation.Annotation;

import static org.mockito.Matchers.any;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Main.class)
public class LiquibaseTaskLauncherTest {

    @Test
    public void shouldLaunchLiquibaseUpdate() throws Exception {
        PowerMockito.mockStatic(Main.class);

        LiquibaseTaskLauncher.update();

        PowerMockito.verifyStatic(times(1));
        Main.run(any());
    }

    @Test
    public void shouldLaunchLiquibaseUpdateWithSpecified() throws Exception {
        PowerMockito.mockStatic(Main.class);

        LiquibaseTest annotation = new LiquibaseTest() {
            @Override
            public Class<? extends Annotation> annotationType() {
                return LiquibaseTest.class;
            }

            @Override
            public String changeLogFile() {
                return "test";
            }

            @Override
            public String[] contexts() {
                return new String[0];
            }
        };

        LiquibaseTaskLauncher.update(annotation);

        PowerMockito.verifyStatic(times(1));
        Main.run(new String[] {
                "--changeLogFile=test",
                "update"
        });

    }

    @Test
    public void shouldLaunchLiquibaseDropAll() throws Exception {
        PowerMockito.mockStatic(Main.class);

        LiquibaseTaskLauncher.dropAll();

        PowerMockito.verifyStatic(times(1));
        Main.run(any());
    }

    @Test(expected = LiquibaseMigrationRunException.class)
    public void shouldThrowExceptionIfUpdateFails() throws Exception {
        PowerMockito.mockStatic(Main.class);
        PowerMockito.doThrow(new LiquibaseException("Test")).when(Main.class, "run", any());

        LiquibaseTaskLauncher.update();
    }

    @Test(expected = LiquibaseMigrationRunException.class)
    public void shouldThrowExceptionIfDropFails() throws Exception {
        PowerMockito.mockStatic(Main.class);
        PowerMockito.doThrow(new LiquibaseException("Test")).when(Main.class, "run", any());

        LiquibaseTaskLauncher.dropAll();
    }

}

package liquibase.ext.tests;

import liquibase.exception.LiquibaseException;
import liquibase.ext.tests.exceptions.LiquibaseMigrationRunException;
import liquibase.integration.commandline.Main;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

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

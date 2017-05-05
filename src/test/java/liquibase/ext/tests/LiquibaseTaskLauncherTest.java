package liquibase.ext.tests;

import liquibase.exception.CommandLineParsingException;
import liquibase.exception.LiquibaseException;
import liquibase.ext.tests.exceptions.LiquibaseMigrationRunException;
import liquibase.integration.commandline.Main;
import mockit.Expectations;
import mockit.Mocked;
import mockit.Verifications;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(JMockit.class)
public class LiquibaseTaskLauncherTest {

    @Test
    public void shouldLaunchLiquibaseUpdate(@Mocked Main mock) throws Exception {
        new Expectations() {{
            Main.run(withInstanceOf(String[].class)); times = 1;
        }};

        LiquibaseTaskLauncher.update();

        new Verifications() {{
            Main.run(withInstanceOf(String[].class)); times = 1;
        }};
    }

    @Test
    public void shouldLaunchLiquibaseDropAll(@Mocked Main mock) throws Exception {
        new Expectations() {{
            Main.run(withInstanceOf(String[].class)); times = 1;
        }};

        LiquibaseTaskLauncher.dropAll();

        new Verifications() {{
            Main.run(withInstanceOf(String[].class)); times = 1;
        }};
    }

    @Test(expected = LiquibaseMigrationRunException.class)
    public void shouldThrowExceptionIfUpdateFails(@Mocked Main mock) throws Exception {
        new Expectations() {{
            Main.run(withInstanceOf(String[].class)); result = new LiquibaseMigrationRunException(anyString);
        }};

        LiquibaseTaskLauncher.update();
    }

    @Test(expected = LiquibaseMigrationRunException.class)
    public void shouldThrowExceptionIfDropFails(@Mocked Main mock) throws Exception {
        new Expectations() {{
            Main.run(withInstanceOf(String[].class)); result = new LiquibaseMigrationRunException(anyString);
        }};

        LiquibaseTaskLauncher.dropAll();
    }

}

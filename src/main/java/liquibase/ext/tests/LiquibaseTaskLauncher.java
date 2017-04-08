package liquibase.ext.tests;

import liquibase.ext.tests.exceptions.LiquibaseMigrationRunException;
import liquibase.integration.commandline.Main;

public final class LiquibaseTaskLauncher {

    private static final String DEFAULT_MESSAGE = "Can't run Liquibase migrations during tests";

    private static void launchTask(LiquibaseTask task, String... args) {
        String[] fullArguments = new String[]{task.toString()};

        try {
            Main.run(fullArguments);
        } catch (Exception e) {
            throw new LiquibaseMigrationRunException(DEFAULT_MESSAGE, e);
        }
    }

    public static void update(String... args) {
        launchTask(LiquibaseTask.UPDATE, args);
    }

    public static void dropAll(String ...args) {
        launchTask(LiquibaseTask.DROP_ALL, args);
    }
}

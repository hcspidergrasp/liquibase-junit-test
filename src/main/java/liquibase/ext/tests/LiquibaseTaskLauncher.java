package liquibase.ext.tests;

import liquibase.ext.tests.annotations.LiquibaseTest;
import liquibase.ext.tests.exceptions.LiquibaseMigrationRunException;
import liquibase.integration.commandline.Main;

import java.util.ArrayList;
import java.util.Arrays;

public class LiquibaseTaskLauncher {

    private static final String ARG_PREFIX = "--";
    private static final String ARG_EQUAL = "=";
    private static final String ARG_CMD_CHANGE_LOG_FILE = ARG_PREFIX + "changeLogFile";

    private static final String DEFAULT_MESSAGE = "Can't run Liquibase migrations during tests";

    private static void launchTask(LiquibaseTask task, String... args) {
        String[] fullArguments = Arrays.copyOf(args, args.length + 1);
        fullArguments[fullArguments.length - 1] = task.toString();
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

    public static void update(LiquibaseTest annotation) {
        String[] args = parseAnnotationSettings(annotation);
        update(args);
    }

    private static String[] parseAnnotationSettings(LiquibaseTest annotation) {
        ArrayList<String> settings = new ArrayList<>();
        if (annotation.changeLogFile().length() > 0) {
            settings.add(ARG_CMD_CHANGE_LOG_FILE + ARG_EQUAL + annotation.changeLogFile());
        }
        return settings.toArray(new String[settings.size()]);
    }
}

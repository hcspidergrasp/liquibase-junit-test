package liquibase.ext.tests.utils;

import liquibase.ext.tests.annotations.LiquibaseTest;
import liquibase.ext.tests.exceptions.LiquibaseMigrationRunException;
import liquibase.integration.commandline.Main;

import java.util.Arrays;

/**
 * Represents utility class for making command line calls to Liquibase.
 */
public class LiquibaseTaskLauncher {

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

    /**
     * Runs Liquibase Update task.
     * @param args Command line arguments.
     */
    public static void update(String... args) {
        launchTask(LiquibaseTask.UPDATE, args);
    }

    /**
     * Runs Liquibase DropAll task.
     * @param args Command line arguments.
     */
    public static void dropAll(String ...args) {
        launchTask(LiquibaseTask.DROP_ALL, args);
    }

    /**
     * Runs Liquibase Update task.
     * @param annotation {@link LiquibaseTest} annotation with task settings.
     */
    public static void update(LiquibaseTest annotation) {
        String[] args = AnnotationMapper.parseAnnotationSettings(annotation);
        update(args);
    }

}

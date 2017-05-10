package liquibase.ext.tests.utils;

/**
 * Represents Liquibase command line task types.
 * Contains string representation of the command.
 */
public enum LiquibaseTask {

    UPDATE("update"),
    DROP_ALL("dropAll");

    private final String taskCommand;

    LiquibaseTask(String taskCommand) {
        this.taskCommand = taskCommand;
    }

    @Override
    public String toString() {
        return taskCommand;
    }
}

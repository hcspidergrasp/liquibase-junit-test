package liquibase.ext.tests.utils;

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

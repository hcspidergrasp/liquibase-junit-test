package liquibase.ext.tests.exceptions;

public class LiquibaseMigrationRunException extends RuntimeException {

    public LiquibaseMigrationRunException(String message) {
        super(message);
    }

    public LiquibaseMigrationRunException(String message, Throwable cause) {
        super(message, cause);
    }
}

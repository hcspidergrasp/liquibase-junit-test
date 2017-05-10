package liquibase.ext.tests.utils;

import liquibase.ext.tests.annotations.LiquibaseTest;

import java.util.ArrayList;

/**
 * Utils which allows to process Liquibase JUnit Test annotations.
 */
public class AnnotationMapper {

    private static final String ARG_PREFIX = "--";
    private static final String ARG_EQUAL = "=";
    private static final String ARG_CMD_CHANGE_LOG_FILE = ARG_PREFIX + "changeLogFile";

    public static String[] parseAnnotationSettings(LiquibaseTest annotation) {
        ArrayList<String> settings = new ArrayList<>();
        if (annotation.changeLogFile().length() > 0) {
            settings.add(ARG_CMD_CHANGE_LOG_FILE + ARG_EQUAL + annotation.changeLogFile());
        }
        return settings.toArray(new String[settings.size()]);
    }

}

package liquibase.ext.tests.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotation is used to test methods to run Liquibase
 * migrations for this test and drop database when it's finished.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface LiquibaseTest {

}

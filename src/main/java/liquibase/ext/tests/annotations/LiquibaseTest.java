package liquibase.ext.tests.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation is used to test methods to run Liquibase
 * migrations for this test and drop database when it's finished.
 */
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface LiquibaseTest {

    /**
     * Mapped to change log file setting of Liquibase runner.
     */
    String changeLogFile() default "";

    /**
     * Mapped to context setting of Liquibase runner.
     */
    String[] contexts() default {};
}

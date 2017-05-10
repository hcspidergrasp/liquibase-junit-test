package liquibase.ext.tests.utils;

import liquibase.ext.tests.annotations.LiquibaseTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class AnnotationMapperTest {

    @Mock private LiquibaseTest annotation;

    @Test
    public void shouldMapChangeLogToArgument() {
        Mockito.when(annotation.changeLogFile()).thenReturn("test");
        Mockito.when(annotation.contexts()).thenReturn(new String[0]);

        String[] result = AnnotationMapper.parseAnnotationSettings(annotation);

        assertTrue(Arrays.equals(result, new String[] {"--changeLogFile=test"}));
    }

    @Test
    public void shouldMapContextsToArgument() {
        Mockito.when(annotation.changeLogFile()).thenReturn("");
        Mockito.when(annotation.contexts()).thenReturn(new String[] {"test", "foo", "moo"});

        String[] result = AnnotationMapper.parseAnnotationSettings(annotation);

        assertTrue(Arrays.equals(result, new String[] {"--contexts=test,foo,moo"}));
    }
}

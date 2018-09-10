package jar;

import com.google.common.base.CaseFormat;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GuavaTest {

    @Test
    public void caseFormat__Underscore_to_Camel() {
        assertEquals("testData", CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "TEST_DATA"));
        assertEquals("test", CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "TEST"));

        assertEquals("TEST", CaseFormat.UPPER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, "test"));
    }
}
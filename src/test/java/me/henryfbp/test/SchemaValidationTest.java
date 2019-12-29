package me.henryfbp.test;

import junit.framework.TestCase;
import me.henryfbp.Util;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;

import static me.henryfbp.Constants.*;

public class SchemaValidationTest extends TestCase {

    /**
     * Rigourous Test :-)
     */
    public void testSanity() {
        assertTrue(true);
    }

    /***
     * Validate a resume.
     */
    public void testJsonSchemaResume() {
        Util.validateResumeJSON(JSON_RESUME_EXAMPLE);
    }

    /**
     * Test a simple json schema validation.
     */
    public void testJsonSchemaLampshade() {
        Schema schema = SchemaLoader.load(JSON_SCHEMA_LAMPSHADE);
        schema.validate(JSON_LAMPSHADE_EXAMPLE);
    }
}

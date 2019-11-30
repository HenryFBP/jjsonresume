package me.henryfbp.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import me.henryfbp.Util;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;

import static me.henryfbp.Constants.*;

/**
 * Unit test for simple App.
 */
public class ResumeGeneratorTest extends TestCase {


    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ResumeGeneratorTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(ResumeGeneratorTest.class);
    }

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

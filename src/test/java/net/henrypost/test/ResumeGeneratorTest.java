package net.henrypost.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

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
    public void testApp() {
        assertTrue(true);
    }

    /***
     * Validate a resume.
     */
    public void testJsonSchemaResume() {

        JSONObject jsonSubject = new JSONObject(new JSONTokener(
                this.getClass().getClassLoader().getResourceAsStream("schemaTests/resume/example.json")));

        ResumeGenerator.validateResumeJSON(jsonSubject);

    }

    /**
     * Test a simple json schema validation.
     */
    public void testJsonSchemaLampshade() {

        JSONObject jsonSchema = new JSONObject(
                new JSONTokener(this.getClass().getClassLoader().getResourceAsStream("schemaTests/lampshade/schema.json")));

        JSONObject jsonSubject = new JSONObject(
                new JSONTokener(this.getClass().getClassLoader().getResourceAsStream("schemaTests/lampshade/example.json")));

        Schema schema = SchemaLoader.load(jsonSchema);
        schema.validate(jsonSubject);
    }
}

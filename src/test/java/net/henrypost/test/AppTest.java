package net.henrypost.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {


    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        assertTrue(true);
    }

    public void testJsonSchemaResume() {

        JSONObject jsonSchema = new JSONObject(
                new JSONTokener(this.getClass().getClassLoader().getResourceAsStream("schemaTests/resume/schema.json")));

        JSONObject jsonSubject = new JSONObject(
                new JSONTokener(this.getClass().getClassLoader().getResourceAsStream("schemaTests/resume/example.json")));

        // Must validate
        Schema schema = SchemaLoader.load(jsonSchema);
        schema.validate(jsonSubject);
    }

    public void testJsonSchemaLampshade() {

        JSONObject jsonSchema = new JSONObject(
                new JSONTokener(this.getClass().getClassLoader().getResourceAsStream("schemaTests/lampshade/schema.json")));

        JSONObject jsonSubject = new JSONObject(
                new JSONTokener(this.getClass().getClassLoader().getResourceAsStream("schemaTests/lampshade/example.json")));

        Schema schema = SchemaLoader.load(jsonSchema);
        schema.validate(jsonSubject);
    }
}

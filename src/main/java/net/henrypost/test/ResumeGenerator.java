package net.henrypost.test;

/*
 * Hello world!
 */

import j2html.TagCreator;
import j2html.tags.ContainerTag;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

import static j2html.TagCreator.*;
import static j2html.TagCreator.h1;

public class ResumeGenerator {

    /**
     * The JSON resume schema.
     */
    public static JSONObject RESUME_SCHEMA = new JSONObject(
            new JSONTokener(ResumeGenerator.class.getClassLoader().getResourceAsStream("schemaTests/resume/schema.json")));

    public static void validateResumeJSON(JSONObject jsonObject) throws ValidationException {

        Schema schema = SchemaLoader.load(RESUME_SCHEMA);
        schema.validate(jsonObject);

    }

    public static String generateResumeHTML(String jsonPath) {

    }


    public static void main(String[] args) {
        System.out.println("Hello World!");

        System.out.println(
                html(
                        head(
                                title("Title"),
                                link().withRel("stylesheet").withHref("/css/main.css")
                        ),
                        body(
                                TagCreator.main(attrs("#main.content"),
                                        h1("Heading!")
                                )
                        )
                ).renderFormatted()
        );
    }
}


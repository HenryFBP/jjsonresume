package me.henryfbp;

/*
 * Hello world!
 */

import j2html.TagCreator;
import org.json.JSONObject;

import static j2html.TagCreator.*;

public class ResumeGenerator {

    public static String generateResumeHTML(JSONObject jsonObject) {

        return html(
                head(
                        title("resume"),
                        link().withRel("stylesheet").withHref("/css/main.css")
                ),
                body(
                        TagCreator.main(attrs("#main.content"),
                                h1("Heading!")
                        )
                )
        ).renderFormatted();
    }


    public static void main(String[] args) {
        System.out.println("Hello World!");

        System.out.println(
                generateResumeHTML(Constants.EXAMPLE_RESUME)
        );
    }
}


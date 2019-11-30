package me.henryfbp;

/*
 * Hello world!
 */

import j2html.TagCreator;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import static j2html.TagCreator.*;

public class ResumeGenerator {

    public static String generateResumeHTML(JSONObject jsonObject) {
        return generateResumeHTML(jsonObject, Constants.DEFAULT_CSS_FILE);
    }

    public static String generateResumeHTML(JSONObject jsonObject, File cssFile) {

        Resume resume = new Resume(jsonObject);

        return html(
                head(
                        title(resume.getPersonName()),
                        link().withRel("stylesheet").withHref(cssFile.getName())
                ),
                body(
                        TagCreator.main(attrs("#main.content"),
                                h1("Heading!")
                        )
                )
        ).renderFormatted();
    }


    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!");

        // write html to file
        Util.writeStringToFile(generateResumeHTML(Constants.EXAMPLE_RESUME), new File("out/temp.html"), true);

        // copy css file
        Util.copyFileToFolder(Constants.DEFAULT_CSS_FILE, new File("out", Constants.DEFAULT_CSS_FILE.getName()).toPath(), true);

    }
}


package me.henryfbp;

/*
 * Hello world!
 */

import j2html.TagCreator;

import java.io.File;
import java.io.IOException;

import static j2html.TagCreator.*;

public class ResumeGenerator {

    public static String generateResumeLaTeX(Resume resume) {
        return "lol";
    }

    public static String generateResumeHTML(Resume resume) {
        return generateResumeHTML(resume, Constants.DEFAULT_CSS_FILE);
    }

    public static String generateResumeHTML(Resume resume, File cssFile) {

        return html(
                head(
                        title(resume.getPersonName()),
                        link().withRel("stylesheet").withHref(cssFile.getName())
                ),
                body(
                        TagCreator.main(attrs("#main.content"),
                                h1(resume.getPersonName())
                        )
                )
        ).renderFormatted();
    }


    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!");

        // write html to file
        Util.writeStringToFile(generateResumeHTML(new Resume(Constants.JSON_RESUME_EXAMPLE)), new File("out/temp.html"), true);

        // copy css file
        Util.copyFileToFolder(Constants.DEFAULT_CSS_FILE, new File("out"), true);

    }
}


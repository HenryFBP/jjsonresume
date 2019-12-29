package me.henryfbp.generator;

/*
 * Hello world!
 */

import j2html.TagCreator;
import j2html.tags.UnescapedText;
import me.henryfbp.Constants;
import me.henryfbp.Util;
import me.henryfbp.model.Resume;

import java.io.File;
import java.io.IOException;

import static j2html.TagCreator.*;

public class ResumeGeneratorHTML {

    public static String generateResumeHTML(Resume resume) {
        return generateResumeHTML(resume, Constants.DEFAULT_CSS_FILE);
    }

    /**
     * Given a Resume object and a CSS file, generate an HTML version of that resume.
     *
     * @param resume  A Resume object.
     * @param cssFile A CSS file that will style the resulting HTML.
     * @return The HTML file.
     */
    public static String generateResumeHTML(Resume resume, File cssFile) {

        return html(
                new UnescapedText("<!-- generated by jjsonresume -->"),
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

    public static void generate(Resume resume, File cssFile, File outputDirectory) throws IOException {
        generate(resume, cssFile, outputDirectory, false);
    }

    /**
     * Given a Resume object and CSS file, output both an HTML file and a CSS file into the output directory.
     */
    public static void generate(Resume resume, File cssFile, File outputDirectory, boolean overwrite) throws IOException {

        // write html to file
        Util.writeStringToFile(
                generateResumeHTML(resume, cssFile),
                new File(outputDirectory, "resume.html"),
                overwrite
        );

        // copy css file
        Util.copyFileToFolder(cssFile, outputDirectory, overwrite);
    }
}


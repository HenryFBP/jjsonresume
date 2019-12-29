package me.henryfbp.test;

import junit.framework.TestCase;
import me.henryfbp.generator.ResumeGeneratorHTML;
import me.henryfbp.generator.ResumeGeneratorLaTeX;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import static me.henryfbp.Constants.DEFAULT_CSS_FILE;
import static me.henryfbp.Constants.RESUME_EXAMPLE;

public class GenerationTest extends TestCase {

    public void testHTMLGeneration() throws IOException {
        ResumeGeneratorHTML.generate(
                RESUME_EXAMPLE,
                DEFAULT_CSS_FILE,
                new File("out/resume-html/"),
                true
        );
    }

    public void testLaTeXGeneration() throws IOException, ParseException {
        System.out.println("Hello World! I make LaTeX resumes!");

        // make tex file
        ResumeGeneratorLaTeX.generate(RESUME_EXAMPLE,
                new File("out/resume-latex/"),
                new File("resume.tex"),
                true
        );

        // make pdf
        ResumeGeneratorLaTeX.runLaTeXCommand(new File("out/resume-latex/"),
                new File("resume.tex"));

        if (Desktop.isDesktopSupported()) {
            // show pdf
//            Desktop.getDesktop().open(new File("out/resume-latex", "resume.pdf"));
        } else {
            System.out.println("Not showing PDF as desktop display is not supported.");
        }

    }
}

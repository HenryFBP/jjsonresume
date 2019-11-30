package me.henryfbp;

/*
 * Hello world!
 */

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;

import static java.lang.String.format;
import static me.henryfbp.Constants.DEFAULT_LATEX_RESUME_CLASS_FILE;
import static me.henryfbp.Constants.RESUME_EXAMPLE;

public class ResumeGeneratorLaTeX {

    public static String makeSingleArgCommand(String command, String arg) {
        return String.format("\\%s{%s}", command, arg);
    }

    /**
     * Given a LaTeX class file (*.cls), return the LaTeX expression that imports that class file.
     */
    public static String makeDocumentClass(File classFile) {
        String template = "\\documentclass{%s}%n";

        String extension = FilenameUtils.getExtension(classFile.getName());

        if (!extension.equalsIgnoreCase("cls")) {
            throw new IllegalArgumentException("Included file must be a .cls file!");
        }

        return format(template, FilenameUtils.getBaseName(classFile.getName()));
    }

    public static String makeDocumentHeaders() {
        return ("\\usepackage[left=0.75in,top=0.6in,right=0.75in,bottom=0.6in]{geometry} %% Document margins\n" +
                makeSingleArgCommand("usepackage", "hyperref") + "\n" +
                makeSingleArgCommand("usepackage", "xcolor") + "\n" +
                "\\newcommand{\\tab}[1]{\\hspace{.2667\\textwidth}\\rlap{#1}}\n" +
                "\\newcommand{\\itab}[1]{\\hspace{0em}\\rlap{#1}}\n");
    }

    public static String generateResumeLaTeX(Resume resume, File classFile) {
        StringBuilder sb = new StringBuilder();


        sb.append(("%% Generated from jjsonresume.\n"))
                .append(makeDocumentClass(classFile))
                .append(makeDocumentHeaders());

        return sb.toString();
    }


    public static void main(String[] args) throws IOException {
        System.out.println("Hello World! I make LaTeX resumes!");

        System.out.println(generateResumeLaTeX(RESUME_EXAMPLE, DEFAULT_LATEX_RESUME_CLASS_FILE));
    }
}


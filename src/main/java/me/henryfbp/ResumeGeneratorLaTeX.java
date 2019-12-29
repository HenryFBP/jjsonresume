package me.henryfbp;

/*
 * Hello world!
 */

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static java.lang.String.format;
import static me.henryfbp.Constants.DEFAULT_LATEX_RESUME_CLASS_FILE;
import static me.henryfbp.Constants.RESUME_EXAMPLE;

@SuppressWarnings("StringConcatenationInsideStringBufferAppend")
public class ResumeGeneratorLaTeX {

    public static String make1ArgCommand(String command, String arg) {
        return String.format("\\%s{%s}", command, arg);
    }

    public static String makeAddress(String s1) {
        return make1ArgCommand("address", s1);
    }

    public static String makeAddress(String s1, String s2) {
        return make1ArgCommand(
                "address",
                String.format("%s \\\\ %s", s1, s2)
        );
    }

    public static String makePhoneAndEmail(String s1, String s2) {
        // TODO: This currently uses makeAddress because the
        //  address class looks appropriate to use for phone
        //  numbers and emails.
        //  ---
        //  This should be changed in the .cls file for semantic
        //  purposes.
        return makeAddress(s1, s2);
    }

    public static String makeWebsite(URL url, String text) {
        // TODO: This currently uses makeAddress because the
        //  address class looks appropriate to use for phone
        //  numbers and emails.
        //  ---
        //  This should be changed in the .cls file for semantic
        //  purposes.
        return makeAddress(makeHrefBlue(url, text));
    }

    public static String makeHrefBlue(URL url, String text) {
        return String.format("\\href{%s}{\\color{blue}{%s}}", url.toString(), text);
    }

    public static String makeName(String name) {
        return make1ArgCommand("name", name);
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
        return "\\usepackage[left=0.75in,top=0.6in,right=0.75in,bottom=0.6in]{geometry}\n" +
                make1ArgCommand("usepackage", "hyperref") + "\n" +
                make1ArgCommand("usepackage", "xcolor") + "\n" +
                "\\newcommand{\\tab}[1]{\\hspace{.2667\\textwidth}\\rlap{#1}}\n" +
                "\\newcommand{\\itab}[1]{\\hspace{0em}\\rlap{#1}}\n";
    }

    public static String generateResumeLaTeX(Resume resume, File classFile) throws MalformedURLException {
        BetterStringBuilder sb = new BetterStringBuilder(new StringBuilder());

        sb.appendWithNewline("%% Generated from jjsonresume")
                .append(makeDocumentClass(classFile))
                .append(makeDocumentHeaders());

        sb.appendWithNewline(makeName(
                resume.getPersonName()));

        sb.appendWithNewline(makeAddress(
                resume.getAddress(),
                String.format("%s, %s",
                        resume.getCity(), resume.getPostalCode())));

        sb.appendWithNewline(makePhoneAndEmail(
                resume.getPhone(), resume.getEmail()));


        URL websiteURL = new URL(resume.getWebsite());
        sb.appendWithNewline(makeWebsite(websiteURL, websiteURL.getHost()));

        // start of document
        sb.append(make1ArgCommand("begin", "document"));

        // end of document
        sb.append(make1ArgCommand("end", "document"));

        return sb.toString();
    }


    public static void main(String[] args) throws IOException {
        System.out.println("Hello World! I make LaTeX resumes!");

        System.out.println(generateResumeLaTeX(RESUME_EXAMPLE, DEFAULT_LATEX_RESUME_CLASS_FILE));
    }
}


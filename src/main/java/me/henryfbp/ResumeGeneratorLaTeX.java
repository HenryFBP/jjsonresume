package me.henryfbp;

/*
 * Hello world!
 */

import org.apache.commons.io.FilenameUtils;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static java.lang.String.format;
import static me.henryfbp.Constants.DEFAULT_LATEX_RESUME_CLASS_FILE;
import static me.henryfbp.Constants.RESUME_EXAMPLE;

@SuppressWarnings("StringConcatenationInsideStringBufferAppend")
public class ResumeGeneratorLaTeX {

    public static String makeNArgCommand(String command, String... args) {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("\\%s", command));

        for (String arg : args) {
            sb.append(String.format("{%s}", arg));
        }

        return sb.toString();
    }

    public static String makeAddress(String s1) {
        return makeNArgCommand("address", s1);
    }

    public static String makeAddress(String s1, String s2) {
        return makeNArgCommand(
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
        return makeNArgCommand("name", name);
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
                makeNArgCommand("usepackage", "hyperref") + "\n" +
                makeNArgCommand("usepackage", "xcolor") + "\n" +
                "\\newcommand{\\tab}[1]{\\hspace{.2667\\textwidth}\\rlap{#1}}\n" +
                "\\newcommand{\\itab}[1]{\\hspace{0em}\\rlap{#1}}\n";
    }


    /**
     * Given a Resume object, output both a LaTeX file and a default LaTeX style file
     * into the output directory.
     */
    public static void generate(Resume resume,
                                File outputDirectory,
                                File outputFile,
                                boolean overwrite) throws IOException {

        // write latex to file
        Util.writeStringToFile(
                generateResumeLaTeX(resume, DEFAULT_LATEX_RESUME_CLASS_FILE),
                new File(outputDirectory, String.valueOf(outputFile)), overwrite
        );

        // copy latex class file
        Util.copyFileToFolder(DEFAULT_LATEX_RESUME_CLASS_FILE,
                outputDirectory,
                overwrite
        );

    }

    public static String generateResumeLaTeX(Resume resume, File classFile) throws MalformedURLException {
        BetterStringBuilder sb = new BetterStringBuilder(new StringBuilder());

        // add imports and style info
        sb.appendWithNewline("%% Generated from jjsonresume")
                .append(makeDocumentClass(classFile))
                .append(makeDocumentHeaders());

        // add name
        sb.appendWithNewline(makeName(
                resume.getPersonName()));

        // add address
        sb.appendWithNewline(makeAddress(
                resume.getAddress(),
                String.format("%s, %s",
                        resume.getCity(), resume.getPostalCode())));

        // add phone and email
        sb.appendWithNewline(makePhoneAndEmail(
                resume.getPhone(), resume.getEmail()));

        // add website
        URL websiteURL = new URL(resume.getWebsite());
        sb.appendWithNewline(makeWebsite(websiteURL, websiteURL.getHost()));

        // start of document
        sb.appendWithNewline(makeNArgCommand("begin", "document"));

        // education section
        if (resume.getEducation() != null) {
            sb.appendWithNewline(makeNArgCommand("begin", "rSection", "Education"));

            //TODO do it

            sb.appendWithNewline(makeNArgCommand("end", "rSection"));
        }


        // end of document
        sb.appendWithNewline(makeNArgCommand("end", "document"));

        return sb.toString();
    }

    /**
     * Compile a TeX file into a PDF.
     *
     * @param folder  The folder to run the command in.
     * @param texfile The TeX file.
     */
    private static void runLaTeXCommand(File folder, File texfile) {

        ProcessBuilder processBuilder = new ProcessBuilder();

        //chdir to folder
        processBuilder.directory(folder);

        // run pdflatex commands twice because idk
        processBuilder.command("pdflatex", "--shell-escape", texfile.getName());
        processBuilder.command("pdflatex", "--shell-escape", texfile.getName());
    }


    public static void main(String[] args) throws IOException {
        System.out.println("Hello World! I make LaTeX resumes!");

        // make tex file
        generate(RESUME_EXAMPLE,
                new File("out/resume-latex/"),
                new File("resume.tex"),
                true
        );

        // make pdf
        runLaTeXCommand(new File("out/resume-latex/"),
                new File("resume.tex"));

        // show pdf
        Desktop.getDesktop().open(new File("out/resume-latex", "resume.pdf"));

    }


}


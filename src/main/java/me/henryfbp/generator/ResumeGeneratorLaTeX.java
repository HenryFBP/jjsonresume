package me.henryfbp.generator;

/*
 * Hello world!
 */

import me.henryfbp.BetterStringBuilder;
import me.henryfbp.Util;
import me.henryfbp.model.Resume;
import me.henryfbp.model.ResumeEducation;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.Date;

import static java.lang.String.format;
import static me.henryfbp.Constants.DEFAULT_LATEX_RESUME_CLASS_FILE;
import static me.henryfbp.Util.dateToMonthYear;

@SuppressWarnings("StringConcatenationInsideStringBufferAppend")
public class ResumeGeneratorLaTeX {

    /***
     * @param command Base LaTeX command.
     * @param args Other LaTeX commands.
     * @return E.G. "\command{arg1}{arg2}"
     */
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

    /**
     * 123 example way  <~>  chicago il 12345
     */
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

    public static String makeLineBreak() {
        return "\\\\";
    }

    /**
     * IIT     (spaces)       sep 2015 - dec 2019
     */
    public static String makeUniversityNameWithDates(String universityName, Date startDate, Date endDate) {
        return String.format(
                "{\\bf %s} \\hfill {\\em %s - %s}",
                universityName,
                dateToMonthYear(startDate),
                dateToMonthYear(endDate)
        );
    }

    public static String makeGPA(String gpa) {
        return String.format("%s GPA", gpa);
    }

    /***
     * Bachelor's in ITM
     */
    public static String makeUniversityMajorAndSubject(String major, String subject) {
        return String.format("%s's in %s", major, subject);
    }

    /**
     * Bachelor's in ITM, 4.0 GPA
     */
    public static String makeUniversityMajorAndSubjectAndGPA(String major, String subject, String gpa) {
        return makeUniversityMajorAndSubject(major, subject) + ", " + makeGPA(gpa);
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
        return new BetterStringBuilder()
                .appendWithNewline("\\usepackage[left=0.75in,top=0.6in,right=0.75in,bottom=0.6in]{geometry}")
                .appendWithNewline(makeNArgCommand("usepackage", "hyperref"))
                .appendWithNewline(makeNArgCommand("usepackage", "xcolor"))
                .appendWithNewline("\\newcommand{\\tab}[1]{\\hspace{.2667\\textwidth}\\rlap{#1}}")
                .appendWithNewline("\\newcommand{\\itab}[1]{\\hspace{0em}\\rlap{#1}}")
                .toString();
    }

    public static String makeEducationSection(ResumeEducation resumeEducation) {

        BetterStringBuilder sb = new BetterStringBuilder();
        sb.appendWithNewline(makeNArgCommand("begin", "rSection", "Education"));


        try {
            sb.appendWithNewline(
                    makeUniversityNameWithDates(
                            resumeEducation.getInstitution(),
                            resumeEducation.getStartDate(),
                            resumeEducation.getEndDate()
                    ) + makeLineBreak()
            );
        } catch (ParseException e) {
            throw new RuntimeException(String.format("Malformed dates in resume education section for '%s'", resumeEducation.getInstitution()));
        }

        sb.appendWithNewline(
                makeUniversityMajorAndSubjectAndGPA(
                        resumeEducation.getStudyType(),
                        resumeEducation.getArea(),
                        resumeEducation.getGPA()
                ) + makeLineBreak()
        );

        sb.appendWithNewline();

        sb.appendWithNewline(makeNArgCommand("end", "rSection"));

        return sb.toString(); //TODO
    }


    /**
     * Given a Resume object, output both a LaTeX file and a default LaTeX style file
     * into the output directory.
     */
    public static void generate(Resume resume,
                                File outputDirectory,
                                File outputFile,
                                boolean overwrite) throws IOException, ParseException {

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

    public static String generateResumeLaTeX(Resume resume, File classFile) throws MalformedURLException, ParseException {
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
            // for all education sections they've entered,
            for (int i = 0; i < resume.getEducationSectionItems(); i++) {

                ResumeEducation re = resume.getSingleEducation(i);

                // add 'em!
                sb.appendWithNewline(makeEducationSection(re));
            }
        }


        // end of document
        sb.appendWithNewline(makeNArgCommand("end", "document"));

        //print latex file contents
        System.out.println(sb.toString());

        return sb.toString();
    }

    /**
     * Compile a TeX file into a PDF.
     *
     * @param folder  The folder to run the command in.
     * @param texfile The TeX file.
     */
    public static void runLaTeXCommand(File folder, File texfile) throws IOException, InterruptedException {

        // Run pdflatex twice. I don't know why.
        for (int i = 0; i < 2; i++) {
            ProcessBuilder processBuilder = new ProcessBuilder();

            //chdir to folder
            processBuilder.directory(folder);
            processBuilder.command("pdflatex", "--shell-escape", texfile.getName());

            // block on subprocess completion.
            processBuilder.start().waitFor();
        }
    }
}


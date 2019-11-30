package me.henryfbp;

import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import static me.henryfbp.Constants.JSON_SCHEMA_RESUME;

public class Util {

    public static File getResourceFile(String filepath) {

        URL res = Util.class.getClassLoader().getResource(filepath);

        return Paths.get(Objects.requireNonNull(res).getFile()).toFile();
    }

    public static InputStream getResourceFileAsStream(String filepath) {
        return Util.class.getClassLoader().getResourceAsStream(filepath);
    }

    public static JSONObject getResourceFileAsJSONObject(String filepath) {
        return new JSONObject(new JSONTokener(getResourceFileAsStream(filepath)));
    }

    public static void validateResumeJSON(JSONObject jsonObject) throws ValidationException {

        Schema schema = SchemaLoader.load(JSON_SCHEMA_RESUME);
        schema.validate(jsonObject);
    }

    public static void writeStringToFile(String input, File outputFile) throws IOException {
        writeStringToFile(input, outputFile, false);
    }

    public static void copyFileToFolder(File file, Path folder) throws IOException {
        copyFileToFolder(file, folder, false);
    }

    public static void copyFileToFolder(File file, File folder, boolean overwrite) throws IOException {
        copyFileToFolder(file, folder.toPath(), overwrite);
    }


    public static void copyFileToFolder(File file, Path folder, boolean overwrite) throws IOException {
        File destFile = new File(folder.toFile(), file.getName());

        if (overwrite) {
            if (destFile.exists()) {
                destFile.delete();
            }
        }

        Files.copy(file.toPath(), destFile.toPath());
    }

    public static void writeStringToFile(String input, File outputFile, boolean overwrite) throws IOException {

        if (outputFile.exists()) { // if file exists,
            if (overwrite) { //if we should delete it anyways,
                outputFile.delete();
            } else {
                throw new FileAlreadyExistsException(outputFile.getAbsolutePath());
            }
        }

        // Ensure parent dir exists
        new File(outputFile.getParent()).mkdirs();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            bw.write(input);
        }

    }

}

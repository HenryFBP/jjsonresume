package me.henryfbp;

import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;

import static me.henryfbp.Constants.RESUME_SCHEMA;

public class Util {

    public static void validateResumeJSON(JSONObject jsonObject) throws ValidationException {

        Schema schema = SchemaLoader.load(RESUME_SCHEMA);
        schema.validate(jsonObject);

    }

    public static void writeStringToFile(String input, File outputFile) throws IOException {
        writeStringToFile(input, outputFile, false);
    }

    public static void writeStringToFile(String input, File outputFile, boolean overwrite) throws IOException {
        if (outputFile.exists()) {
            throw new FileAlreadyExistsException(outputFile.getAbsolutePath());
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            bw.write(input);
        }

    }

}

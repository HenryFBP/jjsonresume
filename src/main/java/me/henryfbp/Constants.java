package me.henryfbp;

import org.json.JSONObject;

import java.io.File;


public final class Constants {
    /**
     * A JSON resume schema.
     */
    public static JSONObject JSON_SCHEMA_RESUME = Util.getResourceFileAsJSONObject(
            "schemaTests/resume/schema.json");

    /**
     * An example resume object.
     */
    public static JSONObject JSON_RESUME_EXAMPLE = Util.getResourceFileAsJSONObject(
            "schemaTests/resume/example.json");

    /**
     * A JSON 'lampshade' schema.
     */
    public static JSONObject JSON_SCHEMA_LAMPSHADE = Util.getResourceFileAsJSONObject(
            "schemaTests/lampshade/schema.json");

    /**
     * An example 'lampshade' object.
     */
    public static JSONObject JSON_LAMPSHADE_EXAMPLE = Util.getResourceFileAsJSONObject(
            "schemaTests/lampshade/example.json");

    public static File DEFAULT_CSS_FILE = Util.getResourceFile(
            "css/default.css");

    public static File DEFAULT_LATEX_RESUME_STYLE_FILE = Util.getResourceFile(
            "tex/default-resume.tex");
}

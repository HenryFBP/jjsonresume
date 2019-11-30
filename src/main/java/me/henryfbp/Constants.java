package me.henryfbp;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.Objects;

public class Constants {
    /**
     * A JSON resume schema.
     */
    public static JSONObject RESUME_SCHEMA = new JSONObject(new JSONTokener(Objects.requireNonNull(Constants.class.getClassLoader().getResourceAsStream(
            "schemaTests/resume/schema.json"))));

    /**
     * An example resume object.
     */
    public static JSONObject EXAMPLE_RESUME = new JSONObject(new JSONTokener(Objects.requireNonNull(Constants.class.getClassLoader().getResourceAsStream(
            "schemaTests/resume/example.json"))));

    public static JSONObject LAMPSHADE_SCHEMA = new JSONObject(new JSONTokener(Objects.requireNonNull(Constants.class.getClassLoader().getResourceAsStream(
            "schemaTests/lampshade/schema.json"))));

    public static JSONObject EXAMPLE_LAMPSHADE = new JSONObject(new JSONTokener(Objects.requireNonNull(Constants.class.getClassLoader().getResourceAsStream(
            "schemaTests/lampshade/example.json"))));
}

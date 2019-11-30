package me.henryfbp;

import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;

import static me.henryfbp.Constants.RESUME_SCHEMA;

public class Util {

    public static void validateResumeJSON(JSONObject jsonObject) throws ValidationException {

        Schema schema = SchemaLoader.load(RESUME_SCHEMA);
        schema.validate(jsonObject);

    }

}

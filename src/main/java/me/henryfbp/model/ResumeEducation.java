package me.henryfbp.model;

import me.henryfbp.Util;
import org.json.JSONObject;

/**
 * A thin wrapper class for accessing JSON properties from the education section of a resume document.
 */
public class ResumeEducation {
    /*
    The JSON object that contains the data that this Resume object draws from.
     */
    public JSONObject _jsonObject;

    public ResumeEducation(JSONObject jsonObject) {

        Util.validateResumeJSON(jsonObject);

        _jsonObject = jsonObject;
    }


}

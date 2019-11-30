package me.henryfbp;

import org.json.JSONObject;

public class Resume {

    /*
    The JSON object that contains the data that this Resume object draws from.
     */
    public JSONObject _jsonObject;

    public Resume(JSONObject jsonObject) {

        Util.validateResumeJSON(jsonObject);

        this._jsonObject = jsonObject;
    }

    public String getPersonName() {
        return this._jsonObject.getJSONObject("basics").getString("name");
    }
}

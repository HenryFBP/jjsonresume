package me.henryfbp;

import org.json.JSONObject;

import java.net.MalformedURLException;

/**
 * A thin wrapper class for accessing JSON properties from a JSON resume document.
 */
public class Resume {

    /*
    The JSON object that contains the data that this Resume object draws from.
     */
    public JSONObject _jsonObject;

    public Resume(JSONObject jsonObject) {

        Util.validateResumeJSON(jsonObject);

        this._jsonObject = jsonObject;
    }

    public JSONObject getBasics(){
        return this._jsonObject.getJSONObject("basics");
    }

    public String getPersonName() {
        return this.getBasics().getString("name");
    }

    public  String getPersonLabel() {
        return this.getBasics().getString("label");
    }

    public String getPictureURL() throws MalformedURLException {
        return this.getBasics().getString("picture");
    }
}

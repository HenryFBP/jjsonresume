package me.henryfbp;

import org.json.JSONArray;
import org.json.JSONObject;

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

        _jsonObject = jsonObject;
    }

    public JSONObject getBasics() {
        return _jsonObject.getJSONObject("basics");
    }

    public JSONObject getLocation() {
        return getBasics().getJSONObject("location");
    }

    public JSONArray getEducation() {
        return _jsonObject.getJSONArray("education");
    }

    public String getAddress() {
        return getLocation().getString("address");
    }

    public String getPostalCode() {
        return getLocation().getString("postalCode");
    }

    public String getCity() {
        return getLocation().getString("city");
    }

    public String getCountryCode() {
        return getLocation().getString("countryCode");
    }

    public String getRegion() {
        return getLocation().getString("region");
    }

    public String getPersonName() {
        return getBasics().getString("name");
    }

    public String getPhone() {
        return getBasics().getString("phone");
    }

    public String getSummary() {
        return getBasics().getString("summary");
    }

    public String getWebsite() {
        return getBasics().getString("website");
    }

    public String getEmail() {
        return getBasics().getString("email");
    }

    public String getPersonLabel() {
        return getBasics().getString("label");
    }

    public String getPictureURL() {
        return getBasics().getString("picture");
    }


}

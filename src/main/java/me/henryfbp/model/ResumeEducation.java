package me.henryfbp.model;

import org.json.JSONObject;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import static me.henryfbp.Util.jsonArrayToStringArray;
import static me.henryfbp.Util.yyyymmddToDate;

/**
 * A thin wrapper class for accessing JSON properties from the education section of a resume document.
 */
public class ResumeEducation {
    /*
    The JSON object that contains the data that this Resume object draws from.
     */
    public JSONObject _jsonObject;

    public ResumeEducation(JSONObject jsonObject) {

        _jsonObject = jsonObject;
    }

    public String getInstitution() {
        return this._jsonObject.getString("institution");
    }

    public String getArea() {
        return this._jsonObject.getString("area");
    }

    public String getStudyType() {
        return this._jsonObject.getString("studyType");
    }

    public Date getStartDate() throws ParseException {
        return yyyymmddToDate(this._jsonObject.getString("startDate"));
    }

    public Date getEndDate() throws ParseException {
        return yyyymmddToDate(this._jsonObject.getString("endDate"));
    }

    public String getGPA() {
        return this._jsonObject.getString("gpa");
    }

    public List<String> getCourses() {
        return jsonArrayToStringArray(this._jsonObject.getJSONArray("courses"));
    }


}

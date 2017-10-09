
package com.ats.sap_recruitment.bean;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EducationalProfile {

    @SerializedName("per_profile")
    @Expose
    private List<EduPerProfile> perProfile = null;

    public List<EduPerProfile> getPerProfile() {
        return perProfile;
    }

    public void setPerProfile(List<EduPerProfile> perProfile) {
        this.perProfile = perProfile;
    }

    @Override
    public String toString() {
        return "EducationalProfile{" +
                "perProfile=" + perProfile +
                '}';
    }
}

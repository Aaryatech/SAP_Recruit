
package com.ats.sap_recruitment.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EducationalProfile {

    @SerializedName("per_profile")
    @Expose
    private List<EduPerProfile> perProfile;

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

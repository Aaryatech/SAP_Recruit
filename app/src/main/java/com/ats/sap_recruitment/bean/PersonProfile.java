
package com.ats.sap_recruitment.bean;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PersonProfile {

    @SerializedName("per_profile")
    @Expose
    private List<PerProfile> perProfile = null;

    public List<PerProfile> getPerProfile() {

        return perProfile;
    }

    public void setPerProfile(List<PerProfile> perProfile) {
        this.perProfile = perProfile;
    }

    @Override
    public String toString() {
        return "PersonProfile{" +
                "perProfile=" + perProfile +
                '}';
    }
}

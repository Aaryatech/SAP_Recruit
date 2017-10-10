
package com.ats.sap_recruitment.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PersonProfile {

    @SerializedName("per_profile")
    @Expose
    private List<PerProfile> perProfile;

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

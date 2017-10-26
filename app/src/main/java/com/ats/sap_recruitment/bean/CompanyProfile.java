package com.ats.sap_recruitment.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CompanyProfile {

    @SerializedName("per_profile")
    @Expose
    private List<CompanyPerProfile> perProfile;

    public List<CompanyPerProfile> getPerProfile() {
        return perProfile;
    }

    public void setPerProfile(List<CompanyPerProfile> perProfile) {
        this.perProfile = perProfile;
    }

    @Override
    public String toString() {
        return "CompanyProfile{" +
                "perProfile=" + perProfile +
                '}';
    }
}

package com.ats.sap_recruitment.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SapActivity {

    @SerializedName("act_name")
    @Expose
    private String actName;
    @SerializedName("remrk_title")
    @Expose
    private String remrkTitle;

    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }

    public String getRemrkTitle() {
        return remrkTitle;
    }

    public void setRemrkTitle(String remrkTitle) {
        this.remrkTitle = remrkTitle;
    }

    @Override
    public String toString() {
        return "SapActivity{" +
                "actName='" + actName + '\'' +
                ", remrkTitle='" + remrkTitle + '\'' +
                '}';
    }
}

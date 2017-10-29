package com.ats.sap_recruitment.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RekArray {

    @SerializedName("remrk_id")
    @Expose
    private String remrkId;
    @SerializedName("remrk_title")
    @Expose
    private String remrkTitle;

    public String getRemrkId() {
        return remrkId;
    }

    public void setRemrkId(String remrkId) {
        this.remrkId = remrkId;
    }

    public String getRemrkTitle() {
        return remrkTitle;
    }

    public void setRemrkTitle(String remrkTitle) {
        this.remrkTitle = remrkTitle;
    }

    @Override
    public String toString() {
        return "RekArray{" +
                "remrkId='" + remrkId + '\'' +
                ", remrkTitle='" + remrkTitle + '\'' +
                '}';
    }
}

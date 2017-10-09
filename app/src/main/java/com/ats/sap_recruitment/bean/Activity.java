package com.ats.sap_recruitment.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Activity {

    @SerializedName("prof_cat_id")
    @Expose
    private String profCatId;
    @SerializedName("act_name")
    @Expose
    private String actName;

    public String getProfCatId() {
        return profCatId;
    }

    public void setProfCatId(String profCatId) {
        this.profCatId = profCatId;
    }

    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "profCatId='" + profCatId + '\'' +
                ", actName='" + actName + '\'' +
                '}';
    }
}

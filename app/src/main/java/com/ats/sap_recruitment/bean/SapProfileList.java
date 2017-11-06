
package com.ats.sap_recruitment.bean;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SapProfileList {

    @SerializedName("sub_cat")
    @Expose
    private String subCat;
    @SerializedName("sub_sub_cat")
    @Expose
    private String subSubCat;
    @SerializedName("exp_detail")
    @Expose
    private String expDetail;
    @SerializedName("incrested")
    @Expose
    private String incrested;
    @SerializedName("activities")
    @Expose
    private List<SapActivity> activities;

    public String getSubCat() {
        return subCat;
    }

    public void setSubCat(String subCat) {
        this.subCat = subCat;
    }

    public String getSubSubCat() {
        return subSubCat;
    }

    public void setSubSubCat(String subSubCat) {
        this.subSubCat = subSubCat;
    }

    public String getExpDetail() {
        return expDetail;
    }

    public void setExpDetail(String expDetail) {
        this.expDetail = expDetail;
    }

    public String getIncrested() {
        return incrested;
    }

    public void setIncrested(String incrested) {
        this.incrested = incrested;
    }

    public List<SapActivity> getActivities() {
        return activities;
    }

    public void setActivities(List<SapActivity> activities) {
        this.activities = activities;
    }

    @Override
    public String toString() {
        return "SapProfileList{" +
                "subCat='" + subCat + '\'' +
                ", subSubCat='" + subSubCat + '\'' +
                ", expDetail='" + expDetail + '\'' +
                ", incrested='" + incrested + '\'' +
                ", activities=" + activities +
                '}';
    }
}

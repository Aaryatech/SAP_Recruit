
package com.ats.sap_recruitment.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JobProfile {

    @SerializedName("prof_main_cat")
    @Expose
    private String profMainCat;
    @SerializedName("prof_sub_cat")
    @Expose
    private String profSubCat;
    @SerializedName("prof_sub_sub_cat")
    @Expose
    private String profSubSubCat;
    @SerializedName("exp_year")
    @Expose
    private String expYear;
    @SerializedName("exp_month")
    @Expose
    private String expMonth;
    @SerializedName("prof_activities")
    @Expose
    private List<ProfActivity> profActivities;

    public String getProfMainCat() {
        return profMainCat;
    }

    public void setProfMainCat(String profMainCat) {
        this.profMainCat = profMainCat;
    }

    public String getProfSubCat() {
        return profSubCat;
    }

    public void setProfSubCat(String profSubCat) {
        this.profSubCat = profSubCat;
    }

    public String getProfSubSubCat() {
        return profSubSubCat;
    }

    public void setProfSubSubCat(String profSubSubCat) {
        this.profSubSubCat = profSubSubCat;
    }

    public String getExpYear() {
        return expYear;
    }

    public void setExpYear(String expYear) {
        this.expYear = expYear;
    }

    public String getExpMonth() {
        return expMonth;
    }

    public void setExpMonth(String expMonth) {
        this.expMonth = expMonth;
    }

    public List<ProfActivity> getProfActivities() {
        return profActivities;
    }

    public void setProfActivities(List<ProfActivity> profActivities) {
        this.profActivities = profActivities;
    }

    @Override
    public String toString() {
        return "JobProfile{" +
                "profMainCat='" + profMainCat + '\'' +
                ", profSubCat='" + profSubCat + '\'' +
                ", profSubSubCat='" + profSubSubCat + '\'' +
                ", expYear='" + expYear + '\'' +
                ", expMonth='" + expMonth + '\'' +
                ", profActivities=" + profActivities +
                '}';
    }
}


package com.ats.sap_recruitment.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CatId {

    @SerializedName("main_cat_id")
    @Expose
    private String mainCatId;
    @SerializedName("sub_cat_id")
    @Expose
    private String subCatId;
    @SerializedName("sub_sub_cat_id")
    @Expose
    private String subSubCatId;
    @SerializedName("exp_year")
    @Expose
    private String expYear;
    @SerializedName("exp_month")
    @Expose
    private String expMonth;

    public String getMainCatId() {
        return mainCatId;
    }

    public void setMainCatId(String mainCatId) {
        this.mainCatId = mainCatId;
    }

    public String getSubCatId() {
        return subCatId;
    }

    public void setSubCatId(String subCatId) {
        this.subCatId = subCatId;
    }

    public String getSubSubCatId() {
        return subSubCatId;
    }

    public void setSubSubCatId(String subSubCatId) {
        this.subSubCatId = subSubCatId;
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

    @Override
    public String toString() {
        return "CatId{" +
                "mainCatId='" + mainCatId + '\'' +
                ", subCatId='" + subCatId + '\'' +
                ", subSubCatId='" + subSubCatId + '\'' +
                ", expYear='" + expYear + '\'' +
                ", expMonth='" + expMonth + '\'' +
                '}';
    }
}

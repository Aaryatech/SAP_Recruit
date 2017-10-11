
package com.ats.sap_recruitment.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MainCat {

    @SerializedName("prof_cat_id")
    @Expose
    private String profCatId;
    @SerializedName("prof_cat_name")
    @Expose
    private String profCatName;
    @SerializedName("sub_cats")
    @Expose
    private List<SubCat> subCats;

    public String getProfCatId() {
        return profCatId;
    }

    public void setProfCatId(String profCatId) {
        this.profCatId = profCatId;
    }

    public String getProfCatName() {
        return profCatName;
    }

    public void setProfCatName(String profCatName) {
        this.profCatName = profCatName;
    }

    public List<SubCat> getSubCats() {
        return subCats;
    }

    public void setSubCats(List<SubCat> subCats) {
        this.subCats = subCats;
    }

    @Override
    public String toString() {
        return "MainCat{" +
                "profCatId='" + profCatId + '\'' +
                ", profCatName='" + profCatName + '\'' +
                ", subCats=" + subCats +
                '}';
    }
}

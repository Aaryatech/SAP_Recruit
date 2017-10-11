
package com.ats.sap_recruitment.bean;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubCat {

    @SerializedName("prof_cat_id")
    @Expose
    private String profCatId;
    @SerializedName("prof_cat_name")
    @Expose
    private String profCatName;
    @SerializedName("sub_sub_cats")
    @Expose
    private List<SubSubCat> subSubCats;

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

    public List<SubSubCat> getSubSubCats() {
        return subSubCats;
    }

    public void setSubSubCats(List<SubSubCat> subSubCats) {
        this.subSubCats = subSubCats;
    }

    @Override
    public String toString() {
        return "SubCat{" +
                "profCatId='" + profCatId + '\'' +
                ", profCatName='" + profCatName + '\'' +
                ", subSubCats=" + subSubCats +
                '}';
    }
}

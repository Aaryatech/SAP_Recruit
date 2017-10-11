
package com.ats.sap_recruitment.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubSubCat {

    @SerializedName("prof_cat_id")
    @Expose
    private String profCatId;
    @SerializedName("prof_cat_name")
    @Expose
    private String profCatName;

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

    @Override
    public String toString() {
        return "SubSubCat{" +
                "profCatId='" + profCatId + '\'' +
                ", profCatName='" + profCatName + '\'' +
                '}';
    }
}


package com.ats.sap_recruitment.bean;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SapProfile {

    @SerializedName("sap_profile_list")
    @Expose
    private List<SapProfileList> sapProfileList;

    public List<SapProfileList> getSapProfileList() {
        return sapProfileList;
    }

    public void setSapProfileList(List<SapProfileList> sapProfileList) {
        this.sapProfileList = sapProfileList;
    }

    @Override
    public String toString() {
        return "SapProfile{" +
                "sapProfileList=" + sapProfileList +
                '}';
    }
}

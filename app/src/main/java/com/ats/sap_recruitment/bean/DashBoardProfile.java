
package com.ats.sap_recruitment.bean;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DashBoardProfile {

    @SerializedName("per_profile")
    @Expose
    private List<DashBoardPerProfile> dashPerProfile;

    public List<DashBoardPerProfile> getDashPerProfile() {
        return dashPerProfile;
    }

    public void setDashPerProfile(List<DashBoardPerProfile> dashPerProfile) {
        this.dashPerProfile = dashPerProfile;
    }

    @Override
    public String toString() {
        return "DashBoardProfile{" +
                "dashPerProfile=" + dashPerProfile +
                '}';
    }
}

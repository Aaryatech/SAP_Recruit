
package com.ats.sap_recruitment.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DashBoardProfile {

    @SerializedName("per_profile")
    @Expose
    private List<DashBoardPerProfile> dashPerProfile;
    @SerializedName("notice")
    @Expose
    private List<Notice> notice;

    public List<DashBoardPerProfile> getDashPerProfile() {
        return dashPerProfile;
    }

    public void setDashPerProfile(List<DashBoardPerProfile> dashPerProfile) {
        this.dashPerProfile = dashPerProfile;
    }

    public List<Notice> getNotice() {
        return notice;
    }

    public void setNotice(List<Notice> notice) {
        this.notice = notice;
    }

    @Override
    public String toString() {
        return "DashBoardProfile{" +
                "dashPerProfile=" + dashPerProfile +
                ", notice=" + notice +
                '}';
    }
}

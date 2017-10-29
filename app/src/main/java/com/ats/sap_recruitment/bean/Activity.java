
package com.ats.sap_recruitment.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Activity {

    @SerializedName("act_id")
    @Expose
    private String actId;
    @SerializedName("act_name")
    @Expose
    private String actName;
    @SerializedName("saved_flag")
    @Expose
    private Integer savedFlag;
    @SerializedName("remrk_id")
    @Expose
    private Integer remrkId;

    public String getActId() {
        return actId;
    }

    public void setActId(String actId) {
        this.actId = actId;
    }

    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }

    public Integer getSavedFlag() {
        return savedFlag;
    }

    public void setSavedFlag(Integer savedFlag) {
        this.savedFlag = savedFlag;
    }

    public Integer getRemrkId() {
        return remrkId;
    }

    public void setRemrkId(Integer remrkId) {
        this.remrkId = remrkId;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "actId='" + actId + '\'' +
                ", actName='" + actName + '\'' +
                ", savedFlag=" + savedFlag +
                ", remrkId=" + remrkId +
                '}';
    }
}

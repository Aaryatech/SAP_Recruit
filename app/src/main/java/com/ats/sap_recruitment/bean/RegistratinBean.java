
package com.ats.sap_recruitment.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegistratinBean {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("prof_name")
    @Expose
    private String profName;
    @SerializedName("user_type")
    @Expose
    private String userType;
    @SerializedName("TOP")
    @Expose
    private Integer tOP;


    public RegistratinBean() {
    }

    public RegistratinBean(String status, String userId, String profName, String userType, Integer tOP) {
        this.status = status;
        this.userId = userId;
        this.profName = profName;
        this.userType = userType;
        this.tOP = tOP;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProfName() {
        return profName;
    }

    public void setProfName(String profName) {
        this.profName = profName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Integer getTOP() {
        return tOP;
    }

    public void setTOP(Integer tOP) {
        this.tOP = tOP;
    }

    @Override
    public String toString() {
        return "RegistratinBean{" +
                "status='" + status + '\'' +
                ", userId='" + userId + '\'' +
                ", profName='" + profName + '\'' +
                ", userType='" + userType + '\'' +
                ", tOP=" + tOP +
                '}';
    }
}

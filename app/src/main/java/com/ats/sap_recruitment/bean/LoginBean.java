
package com.ats.sap_recruitment.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class LoginBean {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("prof_name")
    @Expose
    private String profName;
    @SerializedName("stud_picture")
    @Expose
    private String studPicture;
    @SerializedName("user_type")
    @Expose
    private String userType;

    public LoginBean() {
    }

    public LoginBean(String status, String userId, String profName, String studPicture, String userType) {
        this.status = status;
        this.userId = userId;
        this.profName = profName;
        this.studPicture = studPicture;
        this.userType = userType;
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

    public String getStudPicture() {
        return studPicture;
    }

    public void setStudPicture(String studPicture) {
        this.studPicture = studPicture;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "LoginBean{" +
                "status='" + status + '\'' +
                ", userId='" + userId + '\'' +
                ", profName='" + profName + '\'' +
                ", studPicture='" + studPicture + '\'' +
                ", userType='" + userType + '\'' +
                '}';
    }
}

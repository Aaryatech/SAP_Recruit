package com.ats.sap_recruitment.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PerProfile {

    @SerializedName("prof_fname")
    @Expose
    private String profFname;
    @SerializedName("prof_mname")
    @Expose
    private String profMname;
    @SerializedName("prof_lname")
    @Expose
    private String profLname;
    @SerializedName("prof_dob")
    @Expose
    private String profDob;
    @SerializedName("prof_curr_location")
    @Expose
    private String profCurrLocation;
    @SerializedName("prof_w_status")
    @Expose
    private String profWStatus;
    @SerializedName("prof_w_exp_year")
    @Expose
    private String profWExpYear;
    @SerializedName("prof_w_exp_month")
    @Expose
    private String profWExpMonth;
    @SerializedName("prof_w_like")
    @Expose
    private String profWLike;
    @SerializedName("prof_full_part_time")
    @Expose
    private String profFullPartTime;
    @SerializedName("prof_curr_salary")
    @Expose
    private String profCurrSalary;
    @SerializedName("prof_company_name")
    @Expose
    private String profCompanyName;
    @SerializedName("prof_company_email")
    @Expose
    private String profCompanyEmail;
    @SerializedName("prof_profile_flag")
    @Expose
    private String profProfileFlag;
    @SerializedName("prof_mobile")
    @Expose
    private String profMobile;
    @SerializedName("prof_alternet_mobile")
    @Expose
    private String profAlternetMobile;
    @SerializedName("prof_email")
    @Expose
    private String profEmail;

    public String getProfFname() {
        return profFname;
    }

    public void setProfFname(String profFname) {
        this.profFname = profFname;
    }

    public String getProfMname() {
        return profMname;
    }

    public void setProfMname(String profMname) {
        this.profMname = profMname;
    }

    public String getProfLname() {
        return profLname;
    }

    public void setProfLname(String profLname) {
        this.profLname = profLname;
    }

    public String getProfDob() {
        return profDob;
    }

    public void setProfDob(String profDob) {
        this.profDob = profDob;
    }

    public String getProfCurrLocation() {
        return profCurrLocation;
    }

    public void setProfCurrLocation(String profCurrLocation) {
        this.profCurrLocation = profCurrLocation;
    }

    public String getProfWStatus() {
        return profWStatus;
    }

    public void setProfWStatus(String profWStatus) {
        this.profWStatus = profWStatus;
    }

    public String getProfWExpYear() {
        return profWExpYear;
    }

    public void setProfWExpYear(String profWExpYear) {
        this.profWExpYear = profWExpYear;
    }

    public String getProfWExpMonth() {
        return profWExpMonth;
    }

    public void setProfWExpMonth(String profWExpMonth) {
        this.profWExpMonth = profWExpMonth;
    }

    public String getProfWLike() {
        return profWLike;
    }

    public void setProfWLike(String profWLike) {
        this.profWLike = profWLike;
    }

    public String getProfFullPartTime() {
        return profFullPartTime;
    }

    public void setProfFullPartTime(String profFullPartTime) {
        this.profFullPartTime = profFullPartTime;
    }

    public String getProfCurrSalary() {
        return profCurrSalary;
    }

    public void setProfCurrSalary(String profCurrSalary) {
        this.profCurrSalary = profCurrSalary;
    }

    public String getProfCompanyName() {
        return profCompanyName;
    }

    public void setProfCompanyName(String profCompanyName) {
        this.profCompanyName = profCompanyName;
    }

    public String getProfCompanyEmail() {
        return profCompanyEmail;
    }

    public void setProfCompanyEmail(String profCompanyEmail) {
        this.profCompanyEmail = profCompanyEmail;
    }

    public String getProfProfileFlag() {

        return profProfileFlag;
    }

    public void setProfProfileFlag(String profProfileFlag) {
        this.profProfileFlag = profProfileFlag;
    }

    public String getProfMobile() {

        return profMobile;
    }

    public void setProfMobile(String profMobile) {

        this.profMobile = profMobile;
    }

    public String getProfAlternetMobile() {
        return profAlternetMobile;
    }

    public void setProfAlternetMobile(String profAlternetMobile) {
        this.profAlternetMobile = profAlternetMobile;
    }

    public String getProfEmail() {
        return profEmail;
    }

    public void setProfEmail(String profEmail) {

        this.profEmail = profEmail;
    }

    @Override
    public String toString() {
        return "PerProfile{" +
                "profFname='" + profFname + '\'' +
                ", profMname='" + profMname + '\'' +
                ", profLname='" + profLname + '\'' +
                ", profDob='" + profDob + '\'' +
                ", profCurrLocation='" + profCurrLocation + '\'' +
                ", profWStatus='" + profWStatus + '\'' +
                ", profWExpYear='" + profWExpYear + '\'' +
                ", profWExpMonth='" + profWExpMonth + '\'' +
                ", profWLike='" + profWLike + '\'' +
                ", profFullPartTime='" + profFullPartTime + '\'' +
                ", profCurrSalary='" + profCurrSalary + '\'' +
                ", profCompanyName='" + profCompanyName + '\'' +
                ", profCompanyEmail='" + profCompanyEmail + '\'' +
                ", profProfileFlag='" + profProfileFlag + '\'' +
                ", profMobile='" + profMobile + '\'' +
                ", profAlternetMobile='" + profAlternetMobile + '\'' +
                ", profEmail='" + profEmail + '\'' +
                '}';
    }
}

package com.ats.sap_recruitment.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompanyPerProfile {

    @SerializedName("prof_fname")
    @Expose
    private String profFname;
    @SerializedName("prof_mname")
    @Expose
    private String profMname;
    @SerializedName("prof_lname")
    @Expose
    private String profLname;
    @SerializedName("prof_mobile")
    @Expose
    private String profMobile;
    @SerializedName("prof_alternet_mobile")
    @Expose
    private String profAlternetMobile;
    @SerializedName("prof_email")
    @Expose
    private String profEmail;
    @SerializedName("rec_comp_name")
    @Expose
    private String recCompName;
    @SerializedName("rec_comp_yar_exp")
    @Expose
    private String recCompYarExp;
    @SerializedName("rec_compamny_desc")
    @Expose
    private String recCompamnyDesc;
    @SerializedName("rec_comp_website")
    @Expose
    private String recCompWebsite;
    @SerializedName("rec_comp_address")
    @Expose
    private String recCompAddress;
    @SerializedName("rec_comp_state")
    @Expose
    private String recCompState;
    @SerializedName("rec_comp_city")
    @Expose
    private String recCompCity;

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

    public String getRecCompName() {
        return recCompName;
    }

    public void setRecCompName(String recCompName) {
        this.recCompName = recCompName;
    }

    public String getRecCompYarExp() {
        return recCompYarExp;
    }

    public void setRecCompYarExp(String recCompYarExp) {
        this.recCompYarExp = recCompYarExp;
    }

    public String getRecCompamnyDesc() {
        return recCompamnyDesc;
    }

    public void setRecCompamnyDesc(String recCompamnyDesc) {
        this.recCompamnyDesc = recCompamnyDesc;
    }

    public String getRecCompWebsite() {
        return recCompWebsite;
    }

    public void setRecCompWebsite(String recCompWebsite) {
        this.recCompWebsite = recCompWebsite;
    }

    public String getRecCompAddress() {
        return recCompAddress;
    }

    public void setRecCompAddress(String recCompAddress) {
        this.recCompAddress = recCompAddress;
    }

    public String getRecCompState() {
        return recCompState;
    }

    public void setRecCompState(String recCompState) {
        this.recCompState = recCompState;
    }

    public String getRecCompCity() {
        return recCompCity;
    }

    public void setRecCompCity(String recCompCity) {
        this.recCompCity = recCompCity;
    }


    @Override
    public String toString() {
        return "CompanyPerProfile{" +
                "profFname='" + profFname + '\'' +
                ", profMname='" + profMname + '\'' +
                ", profLname='" + profLname + '\'' +
                ", profMobile='" + profMobile + '\'' +
                ", profAlternetMobile='" + profAlternetMobile + '\'' +
                ", profEmail='" + profEmail + '\'' +
                ", recCompName='" + recCompName + '\'' +
                ", recCompYarExp='" + recCompYarExp + '\'' +
                ", recCompamnyDesc='" + recCompamnyDesc + '\'' +
                ", recCompWebsite='" + recCompWebsite + '\'' +
                ", recCompAddress='" + recCompAddress + '\'' +
                ", recCompState='" + recCompState + '\'' +
                ", recCompCity='" + recCompCity + '\'' +
                '}';
    }
}

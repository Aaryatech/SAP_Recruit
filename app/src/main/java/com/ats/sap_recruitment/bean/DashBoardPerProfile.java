
package com.ats.sap_recruitment.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DashBoardPerProfile {

    @SerializedName("prof_fname")
    @Expose
    private String profFname;
    @SerializedName("prof_mname")
    @Expose
    private String profMname;
    @SerializedName("prof_lname")
    @Expose
    private String profLname;
    @SerializedName("prof_lst_update")
    @Expose
    private String profLstUpdate;
    @SerializedName("profile_completed")
    @Expose
    private Integer profileCompleted;
    @SerializedName("profile_rating")
    @Expose
    private Double profileRating;
    @SerializedName("profile_score")
    @Expose
    private Integer profileScore;
    @SerializedName("prof_edu_course_detail")
    @Expose
    private String profEduCourseDetail;
    @SerializedName("prof_exp")
    @Expose
    private String profExp;

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

    public String getProfLstUpdate() {
        return profLstUpdate;
    }

    public void setProfLstUpdate(String profLstUpdate) {
        this.profLstUpdate = profLstUpdate;
    }

    public Integer getProfileCompleted() {
        return profileCompleted;
    }

    public void setProfileCompleted(Integer profileCompleted) {
        this.profileCompleted = profileCompleted;
    }

    public Double getProfileRating() {
        return profileRating;
    }

    public void setProfileRating(Double profileRating) {
        this.profileRating = profileRating;
    }

    public Integer getProfileScore() {
        return profileScore;
    }

    public void setProfileScore(Integer profileScore) {
        this.profileScore = profileScore;
    }

    public String getProfEduCourseDetail() {
        return profEduCourseDetail;
    }

    public void setProfEduCourseDetail(String profEduCourseDetail) {
        this.profEduCourseDetail = profEduCourseDetail;
    }

    public String getProfExp() {
        return profExp;
    }

    public void setProfExp(String profExp) {
        this.profExp = profExp;
    }

    @Override
    public String toString() {
        return "DashBoardPerProfile{" +
                "profFname='" + profFname + '\'' +
                ", profMname='" + profMname + '\'' +
                ", profLname='" + profLname + '\'' +
                ", profLstUpdate='" + profLstUpdate + '\'' +
                ", profileCompleted=" + profileCompleted +
                ", profileRating=" + profileRating +
                ", profileScore=" + profileScore +
                ", profEduCourseDetail='" + profEduCourseDetail + '\'' +
                ", profExp='" + profExp + '\'' +
                '}';
    }
}

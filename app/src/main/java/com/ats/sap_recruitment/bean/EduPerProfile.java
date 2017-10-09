package com.ats.sap_recruitment.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EduPerProfile {

    @SerializedName("prof_edu_highest")
    @Expose
    private String profEduHighest;
    @SerializedName("prof_edu_course_detail")
    @Expose
    private String profEduCourseDetail;
    @SerializedName("prof_edu_university")
    @Expose
    private String profEduUniversity;
    @SerializedName("prof_edu_passing_year")
    @Expose
    private String profEduPassingYear;
    @SerializedName("prof_edu_grade_range")
    @Expose
    private String profEduGradeRange;
    @SerializedName("prof_edu_misc_skill_details")
    @Expose
    private String profEduMiscSkillDetails;

    public String getProfEduHighest() {
        return profEduHighest;
    }

    public void setProfEduHighest(String profEduHighest) {
        this.profEduHighest = profEduHighest;
    }

    public String getProfEduCourseDetail() {
        return profEduCourseDetail;
    }

    public void setProfEduCourseDetail(String profEduCourseDetail) {
        this.profEduCourseDetail = profEduCourseDetail;
    }

    public String getProfEduUniversity() {
        return profEduUniversity;
    }

    public void setProfEduUniversity(String profEduUniversity) {
        this.profEduUniversity = profEduUniversity;
    }

    public String getProfEduPassingYear() {
        return profEduPassingYear;
    }

    public void setProfEduPassingYear(String profEduPassingYear) {
        this.profEduPassingYear = profEduPassingYear;
    }

    public String getProfEduGradeRange() {
        return profEduGradeRange;
    }

    public void setProfEduGradeRange(String profEduGradeRange) {
        this.profEduGradeRange = profEduGradeRange;
    }

    public String getProfEduMiscSkillDetails() {
        return profEduMiscSkillDetails;
    }

    public void setProfEduMiscSkillDetails(String profEduMiscSkillDetails) {
        this.profEduMiscSkillDetails = profEduMiscSkillDetails;
    }

    @Override
    public String toString() {
        return "EduPerProfile{" +
                "profEduHighest='" + profEduHighest + '\'' +
                ", profEduCourseDetail='" + profEduCourseDetail + '\'' +
                ", profEduUniversity='" + profEduUniversity + '\'' +
                ", profEduPassingYear='" + profEduPassingYear + '\'' +
                ", profEduGradeRange='" + profEduGradeRange + '\'' +
                ", profEduMiscSkillDetails='" + profEduMiscSkillDetails + '\'' +
                '}';
    }
}


package com.ats.sap_recruitment.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JobsArry {

    @SerializedName("job_id")
    @Expose
    private String jobId;
    @SerializedName("job_title")
    @Expose
    private String jobTitle;
    @SerializedName("job_desc")
    @Expose
    private String jobDesc;
    @SerializedName("job_exp_year")
    @Expose
    private String jobExpYear;
    @SerializedName("job_exp_month")
    @Expose
    private String jobExpMonth;
    @SerializedName("job_date")
    @Expose
    private String jobDate;
    @SerializedName("job_profiles")
    @Expose
    private List<JobProfile> jobProfiles;

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    public String getJobExpYear() {
        return jobExpYear;
    }

    public void setJobExpYear(String jobExpYear) {
        this.jobExpYear = jobExpYear;
    }

    public String getJobExpMonth() {
        return jobExpMonth;
    }

    public void setJobExpMonth(String jobExpMonth) {
        this.jobExpMonth = jobExpMonth;
    }

    public String getJobDate() {
        return jobDate;
    }

    public void setJobDate(String jobDate) {
        this.jobDate = jobDate;
    }

    public List<JobProfile> getJobProfiles() {
        return jobProfiles;
    }

    public void setJobProfiles(List<JobProfile> jobProfiles) {
        this.jobProfiles = jobProfiles;
    }

    @Override
    public String toString() {
        return "JobsArry{" +
                "jobId='" + jobId + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", jobDesc='" + jobDesc + '\'' +
                ", jobExpYear='" + jobExpYear + '\'' +
                ", jobExpMonth='" + jobExpMonth + '\'' +
                ", jobDate='" + jobDate + '\'' +
                ", jobProfiles=" + jobProfiles +
                '}';
    }
}

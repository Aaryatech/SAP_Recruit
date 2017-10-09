package com.ats.sap_recruitment.bean;

public class JobPost {
    int jobId;
    String jobLocation;
    String jobTitle;
    String jobYear;
    String jobType;


    public JobPost(int jobId, String jobLocation, String jobTitle, String jobYear, String jobType) {
        this.jobId = jobId;
        this.jobLocation = jobLocation;
        this.jobTitle = jobTitle;
        this.jobYear = jobYear;
        this.jobType = jobType;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getJobLocation() {
        return jobLocation;
    }

    public void setJobLocation(String jobLocation) {
        this.jobLocation = jobLocation;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobYear() {
        return jobYear;
    }

    public void setJobYear(String jobYear) {
        this.jobYear = jobYear;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    @Override
    public String toString() {
        return "JobPost{" +
                "jobId=" + jobId +
                ", jobLocation='" + jobLocation + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", jobYear='" + jobYear + '\'' +
                ", jobType='" + jobType + '\'' +
                '}';
    }
}

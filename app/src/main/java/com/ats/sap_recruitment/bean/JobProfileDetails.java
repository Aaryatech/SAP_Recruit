
package com.ats.sap_recruitment.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JobProfileDetails {

    @SerializedName("jobs_arry")
    @Expose
    private List<JobsArry> jobsArry;

    public List<JobsArry> getJobsArry() {
        return jobsArry;
    }

    public void setJobsArry(List<JobsArry> jobsArry) {
        this.jobsArry = jobsArry;
    }

    public JobProfileDetails(List<JobsArry> jobsArry) {
        this.jobsArry = jobsArry;
    }

    public JobProfileDetails() {
    }

    @Override
    public String toString() {
        return "JobProfileDetails{" +
                "jobsArry=" + jobsArry +
                '}';
    }
}


package com.ats.sap_recruitment.bean;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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

    @Override
    public String toString() {
        return "JobProfileDetails{" +
                "jobsArry=" + jobsArry +
                '}';
    }
}

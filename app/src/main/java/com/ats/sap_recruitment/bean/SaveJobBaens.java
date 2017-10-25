
package com.ats.sap_recruitment.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SaveJobBaens {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("job_id")
    @Expose
    private Integer jobId;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    @Override
    public String toString() {
        return "SaveJobBaens{" +
                "status='" + status + '\'' +
                ", jobId=" + jobId +
                '}';
    }
}


package com.ats.sap_recruitment.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Notice {

    @SerializedName("not_id")
    @Expose
    private String notId;
    @SerializedName("not_text")
    @Expose
    private String notText;
    @SerializedName("not_date")
    @Expose
    private String notDate;

    public String getNotId() {
        return notId;
    }

    public void setNotId(String notId) {
        this.notId = notId;
    }

    public String getNotText() {
        return notText;
    }

    public void setNotText(String notText) {
        this.notText = notText;
    }

    public String getNotDate() {
        return notDate;
    }

    public void setNotDate(String notDate) {
        this.notDate = notDate;
    }

}

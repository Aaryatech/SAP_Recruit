
package com.ats.sap_recruitment.bean;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ActvityInformation {

    @SerializedName("activities")
    @Expose
    private List<Activity> activities;
    @SerializedName("cat_ids")
    @Expose
    private List<CatId> catIds;
    @SerializedName("rek_array")
    @Expose
    private List<RekArray> rekArray;

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public List<CatId> getCatIds() {
        return catIds;
    }

    public void setCatIds(List<CatId> catIds) {
        this.catIds = catIds;
    }

    public List<RekArray> getRekArray() {
        return rekArray;
    }

    public void setRekArray(List<RekArray> rekArray) {
        this.rekArray = rekArray;
    }

    @Override
    public String toString() {
        return "ActvityInformation{" +
                "activities=" + activities +
                ", catIds=" + catIds +
                ", rekArray=" + rekArray +
                '}';
    }
}

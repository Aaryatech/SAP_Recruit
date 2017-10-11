
package com.ats.sap_recruitment.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Categories {

    @SerializedName("main_cats")
    @Expose
    private List<MainCat> mainCats;

    public List<MainCat> getMainCats() {
        return mainCats;
    }

    public void setMainCats(List<MainCat> mainCats) {
        this.mainCats = mainCats;
    }


    @Override
    public String toString() {
        return "Categories{" +
                "mainCats=" + mainCats +
                '}';
    }
}

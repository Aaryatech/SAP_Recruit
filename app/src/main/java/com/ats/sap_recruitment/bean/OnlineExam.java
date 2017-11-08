
package com.ats.sap_recruitment.bean;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OnlineExam {

    @SerializedName("quest_arry")
    @Expose
    private List<QuestArry> questArry;

    public List<QuestArry> getQuestArry() {
        return questArry;
    }

    public void setQuestArry(List<QuestArry> questArry) {
        this.questArry = questArry;
    }

}

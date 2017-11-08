
package com.ats.sap_recruitment.bean;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuestArry {

    @SerializedName("quest_id")
    @Expose
    private String questId;
    @SerializedName("quest_name")
    @Expose
    private String questName;
    @SerializedName("ans_array")
    @Expose
    private List<AnsArray> ansArray;

    public String getQuestId() {
        return questId;
    }

    public void setQuestId(String questId) {
        this.questId = questId;
    }

    public String getQuestName() {
        return questName;
    }

    public void setQuestName(String questName) {
        this.questName = questName;
    }

    public List<AnsArray> getAnsArray() {
        return ansArray;
    }

    public void setAnsArray(List<AnsArray> ansArray) {
        this.ansArray = ansArray;
    }

}

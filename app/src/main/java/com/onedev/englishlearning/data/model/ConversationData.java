package com.onedev.englishlearning.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ConversationData {

    @SerializedName("entire_sound")
    @Expose
    private String entireSound;

    @SerializedName("conversations")
    @Expose
    private List<Conversation> conversations;

    public String getEntireSound() {
        return entireSound;
    }

    public void setEntireSound(String entireSound) {
        this.entireSound = entireSound;
    }

    public List<Conversation> getConversations() {
        return conversations;
    }

    public void setConversations(List<Conversation> conversations) {
        this.conversations = conversations;
    }
}

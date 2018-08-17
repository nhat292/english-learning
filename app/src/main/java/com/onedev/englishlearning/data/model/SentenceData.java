package com.onedev.englishlearning.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SentenceData {

    @SerializedName("entire_sound")
    @Expose
    private String entireSound;

    @SerializedName("sentences")
    @Expose
    private ArrayList<Sentence> sentences;

    public String getEntireSound() {
        return entireSound;
    }

    public void setEntireSound(String entireSound) {
        this.entireSound = entireSound;
    }

    public ArrayList<Sentence> getSentences() {
        return sentences;
    }

    public void setSentences(ArrayList<Sentence> sentences) {
        this.sentences = sentences;
    }
}

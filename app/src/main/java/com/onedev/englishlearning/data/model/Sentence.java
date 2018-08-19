package com.onedev.englishlearning.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sentence {

    public static int TYPE_NO_SOUND = 0;
    public static int TYPE_SOUND = 1;

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("phrase")
    @Expose
    private String phrase;

    @SerializedName("category_id")
    @Expose
    private int categoryId;

    @SerializedName("sound_url")
    @Expose
    private String soundUrl;

    @SerializedName("type")
    @Expose
    private int type;

    private boolean playing;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getSoundUrl() {
        return soundUrl;
    }

    public void setSoundUrl(String soundUrl) {
        this.soundUrl = soundUrl;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }
}

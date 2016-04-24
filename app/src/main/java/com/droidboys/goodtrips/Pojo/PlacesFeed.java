package com.droidboys.goodtrips.Pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Sandeep on 24-Apr-16.
 */
public class PlacesFeed {
    @SerializedName("given_by__user__first_name")
    String name;
    @SerializedName("description")
    String desc;
    @SerializedName("given_by__profile_pic")
    String prof;
    @SerializedName("is_positive")
    boolean mood;
    long given_by;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getProf() {
        return prof;
    }

    public void setProf(String prof) {
        this.prof = prof;
    }

    public boolean isMood() {
        return mood;
    }

    public void setMood(boolean mood) {
        this.mood = mood;
    }

    public long getGiven_by() {
        return given_by;
    }

    public void setGiven_by(long given_by) {
        this.given_by = given_by;
    }
}

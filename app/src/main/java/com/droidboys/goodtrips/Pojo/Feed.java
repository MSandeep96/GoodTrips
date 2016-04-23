package com.droidboys.goodtrips.Pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Sandeep on 23-Apr-16.
 */
public class Feed {
    long Feedid;
    @SerializedName("place_name")
    String pname;
    @SerializedName("description")
    String desc;
    @SerializedName("user")
    String userName;
    @SerializedName("is_positive")
    boolean mood;
    @SerializedName("thumbnail")
    String img;
    @SerializedName("user_picture")
    String prof_pic;
    @SerializedName("place")
    long place_ID;
    @SerializedName("given_by")
    long user_ID;


    public long getFeedid() {
        return Feedid;
    }

    public void setFeedid(long feedid) {
        Feedid = feedid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isMood() {
        return mood;
    }

    public void setMood(boolean mood) {
        this.mood = mood;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getProf_pic() {
        return prof_pic;
    }

    public void setProf_pic(String prof_pic) {
        this.prof_pic = prof_pic;
    }

    public long getPlace_ID() {
        return place_ID;
    }

    public void setPlace_ID(long place_ID) {
        this.place_ID = place_ID;
    }

    public long getUser_ID() {
        return user_ID;
    }

    public void setUser_ID(long user_ID) {
        this.user_ID = user_ID;
    }
}

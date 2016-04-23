package com.droidboys.goodtrips.Pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Sandeep on 23-Apr-16.
 */
public class Feed implements Parcelable{
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.Feedid);
        dest.writeString(this.pname);
        dest.writeString(this.desc);
        dest.writeString(this.userName);
        dest.writeByte(mood ? (byte) 1 : (byte) 0);
        dest.writeString(this.img);
        dest.writeString(this.prof_pic);
        dest.writeLong(this.place_ID);
        dest.writeLong(this.user_ID);
    }

    public Feed() {
    }

    protected Feed(Parcel in) {
        this.Feedid = in.readLong();
        this.pname = in.readString();
        this.desc = in.readString();
        this.userName = in.readString();
        this.mood = in.readByte() != 0;
        this.img = in.readString();
        this.prof_pic = in.readString();
        this.place_ID = in.readLong();
        this.user_ID = in.readLong();
    }

    public static final Creator<Feed> CREATOR = new Creator<Feed>() {
        @Override
        public Feed createFromParcel(Parcel source) {
            return new Feed(source);
        }

        @Override
        public Feed[] newArray(int size) {
            return new Feed[size];
        }
    };
}

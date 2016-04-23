package com.droidboys.goodtrips.Pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Sandeep on 23-Apr-16.
 */
public class Feed {
    long id;
    @SerializedName("place_name")
    String pname;
    @SerializedName("description")
    String desc;
    @SerializedName("given_by")
    long userId;
    @SerializedName("is_positive")
    boolean mood;
    @SerializedName("thumbnail")
    boolean img;
}

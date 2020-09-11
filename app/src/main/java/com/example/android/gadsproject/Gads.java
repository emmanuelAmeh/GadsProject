package com.example.android.gadsproject;

import com.google.gson.annotations.SerializedName;

public class Gads {
    @SerializedName("name")
    private String mName;

    @SerializedName("hours")
    private int mHours;

    @SerializedName("country")
    private String mCountry;

    @SerializedName("badgeUrl")
    private String mUrl;

    public Gads(String name, int hours, String country, String url) {
        mName = name;
        mHours = hours;
        mCountry = country;
        mUrl = url;
    }

    public String getName() {
        return mName;
    }

    public int getHours() {
        return mHours;
    }

    public String getCountry() {
        return mCountry;
    }

    public String getUrl() {
        return mUrl;
    }
}

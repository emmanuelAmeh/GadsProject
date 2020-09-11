package com.example.android.gadsproject;

import com.google.gson.annotations.SerializedName;

public class GadsIQ {
    @SerializedName("name")
    private String mName;

    @SerializedName("score")
    private int mScore;

    @SerializedName("country")
    private String mCountry;

    @SerializedName("badgeUrl")
    private String mUrl;

    public GadsIQ(String name, int score, String country, String url) {
        mName = name;
        mScore = score;
        mCountry = country;
        mUrl = url;
    }

    public String getName() {
        return mName;
    }

    public int getScore() {
        return mScore;
    }

    public String getCountry() {
        return mCountry;
    }

    public String getUrl() {
        return mUrl;
    }
}

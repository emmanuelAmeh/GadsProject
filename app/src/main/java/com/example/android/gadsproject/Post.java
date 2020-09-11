package com.example.android.gadsproject;

import com.google.gson.annotations.SerializedName;

public class Post {
    @SerializedName("entry.1877115667")
    private String fName;

    @SerializedName("entry.2006916086")
    private String lName;

    @SerializedName("entry.1824927963")
    private String email;

    @SerializedName("entry.284483984")
    private String github;

    public Post(String fName, String lName, String email, String github) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.github = github;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getEmail() {
        return email;
    }

    public String getGithub() {
        return github;
    }
}


package com.company.models;

public class User {

    private final String twitterHandle;
    private final String name;
    private final String profileImageUrl;

    public User(String twitterHandle, String name, String profileImageUrl) {
        this.twitterHandle = twitterHandle;
        this.name = name;
        this.profileImageUrl = profileImageUrl;
    }

    public String getTwitterHandle() {
        return twitterHandle;
    }

    public String getName() {
        return name;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }
}

package com.company.models;

import java.util.Objects;

public class TwitterUser {

    private String twitterHandle;
    private String name;
    private String profileImageUrl;

    public TwitterUser(String twitterHandle, String name, String profileImageUrl) {
        this.twitterHandle = twitterHandle;
        this.name = name;
        this.profileImageUrl = profileImageUrl;
    }

    public TwitterUser() {
    }

    public String getTwitterHandle() {
        return twitterHandle;
    }

    public void setTwitterHandle(String twitterHandle) {
        this.twitterHandle = twitterHandle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    @Override
    public String toString() {
        return "TwitterUser{" +
                "twitterHandle='" + twitterHandle + '\'' +
                ", name='" + name + '\'' +
                ", profileImageUrl='" + profileImageUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TwitterUser that = (TwitterUser) o;
        return Objects.equals(twitterHandle, that.twitterHandle) &&
                Objects.equals(name, that.name) &&
                Objects.equals(profileImageUrl, that.profileImageUrl);
    }

    @Override
    public int hashCode() {

        return Objects.hash(twitterHandle, name, profileImageUrl);
    }
}

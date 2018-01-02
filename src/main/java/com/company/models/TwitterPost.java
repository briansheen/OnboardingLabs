package com.company.models;

import java.util.Date;
import java.util.Objects;

public class TwitterPost {

    private TwitterUser twitterUser;
    private String message;
    private Date createdAt;

    public TwitterPost(TwitterUser twitterUser, String message, Date createdAt) {
        this.twitterUser = twitterUser;
        this.message = message;
        this.createdAt = createdAt;
    }

    public TwitterUser getTwitterUser() {
        return twitterUser;
    }

    public void setTwitterUser(TwitterUser twitterUser) {
        this.twitterUser = twitterUser;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "TwitterPost{" +
                "twitterUser=" + twitterUser +
                ", message='" + message + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TwitterPost that = (TwitterPost) o;
        return Objects.equals(twitterUser, that.twitterUser) &&
                Objects.equals(message, that.message) &&
                Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {

        return Objects.hash(twitterUser, message, createdAt);
    }
}

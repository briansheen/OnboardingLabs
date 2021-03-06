package com.company.models;

import java.util.Date;
import java.util.Objects;

public class TwitterPost {

    private TwitterUser twitterUser;
    private String message;
    private Date createdAt;
    private String id;
    private Long inReplyToStatusId;

    public TwitterPost() {
    }

    public TwitterPost(TwitterUser twitterUser, String message, Date createdAt, String id, Long inReplyToStatusId) {
        this.twitterUser = twitterUser;
        this.message = message;
        this.createdAt = createdAt;
        this.id = id;
        this.inReplyToStatusId = inReplyToStatusId;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getInReplyToStatusId() {
        return inReplyToStatusId;
    }

    public void setInReplyToStatusId(Long inReplyToStatusId) {
        this.inReplyToStatusId = inReplyToStatusId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TwitterPost that = (TwitterPost) o;
        return Objects.equals(twitterUser, that.twitterUser) &&
                Objects.equals(message, that.message) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(id, that.id) &&
                Objects.equals(inReplyToStatusId, that.inReplyToStatusId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(twitterUser, message, createdAt, id, inReplyToStatusId);
    }

    @Override
    public String toString() {
        return "TwitterPost{" +
                "twitterUser=" + twitterUser +
                ", message='" + message + '\'' +
                ", createdAt=" + createdAt +
                ", id='" + id + '\'' +
                ", inReplyToStatusId=" + inReplyToStatusId +
                '}';
    }
}

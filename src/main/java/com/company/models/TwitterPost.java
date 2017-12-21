package com.company.models;

import java.util.Date;

public class TwitterPost {

    private final User user;
    private final String message;
    private final Date createdAt;

    public TwitterPost(User user, String message, Date createdAt) {
        this.user = user;
        this.message = message;
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}

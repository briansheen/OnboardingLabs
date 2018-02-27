package com.company.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PostReplyRequest {

    @JsonProperty("replyMessage")
    String replyMessage;

    @JsonProperty("inReplyToStatusId")
    Long inReplyToStatusId;

    public String getReplyMessage() {
        return replyMessage;
    }

    public void setReplyMessage(String replyMessage) {
        this.replyMessage = replyMessage;
    }

    public Long getInReplyToStatusId() {
        return inReplyToStatusId;
    }

    public void setInReplyToStatusId(Long inReplyToStatusId) {
        this.inReplyToStatusId = inReplyToStatusId;
    }
}

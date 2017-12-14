package com.company.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TwitterErrorResponse {
    private int errorCode;
    private String errorMessage;

    public TwitterErrorResponse() {
    }

    public TwitterErrorResponse(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    @JsonProperty
    public String getErrorMessage() {
        return errorMessage;
    }

    @JsonProperty
    public int getErrorCode() {
        return errorCode;
    }
}

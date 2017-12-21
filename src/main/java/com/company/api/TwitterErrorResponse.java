package com.company.api;

public class TwitterErrorResponse {
    private int errorCode;
    private String errorMessage;

    public TwitterErrorResponse() {
    }

    public TwitterErrorResponse(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }
}

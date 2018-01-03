package com.company.api;

import java.util.Objects;

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

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TwitterErrorResponse that = (TwitterErrorResponse) o;
        return errorCode == that.errorCode &&
                Objects.equals(errorMessage, that.errorMessage);
    }

    @Override
    public int hashCode() {

        return Objects.hash(errorCode, errorMessage);
    }
}

package com.bboroccu.jwtsample.common;

import org.springframework.http.HttpStatus;

import java.util.Date;

public class ResultCode {
    private HttpStatus status;

    // General Error message
    private String message;

    // Error code
    private ErrorCode errorCode;

    private Date timestamp;

    public ResultCode() {
        this.timestamp = new Date();
    }

    public ResultCode(HttpStatus status, String message, ErrorCode errorCode) {
        this.status = status;
        this.message = message;
        this.errorCode = errorCode;
        this.timestamp = new Date();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}

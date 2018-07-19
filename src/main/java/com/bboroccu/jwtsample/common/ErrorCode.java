package com.bboroccu.jwtsample.common;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enumeration of REST Error types.
 * 
 * @author vladimir.stankovic
 *
 *         Aug 3, 2016
 */
public enum ErrorCode {
    SUCCESS(0),
    NOTFOUNDID(1),
    FAILPASSWORD(2),
    NOTFOUNDROLE(3),
    FAIL_REFRESH_PASSWORD(4),
    FAIL(5),
    AUTHENTICATION(10),
    TOKEN_EXPIRED(11),
    NOTFOUND_REFRESH_TOKEN(12),
    UNKNOWN_ERROR(13),
    INVALID_PARAMETER(14);
    
    private int errorCode;

    private ErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @JsonValue
    public int getErrorCode() {
        return errorCode;
    }
}

package com.example.common.exception;

import org.apache.commons.lang3.StringUtils;

@SuppressWarnings("serial")
public class AdtsException extends RuntimeException {

    private final int errorCode;

    private final Object[] args;

    public AdtsException() {
        this(AdtsCode.INTERNAL_SERVER_ERROR);
    }

    public AdtsException(String message, Object... args) {
        super(message);
        this.errorCode = AdtsCode.INTERNAL_SERVER_ERROR.value();
        this.args = args;
    }

    public AdtsException(Throwable cause, Object... args) {
        this(cause, AdtsCode.INTERNAL_SERVER_ERROR, args);
    }

    public AdtsException(Throwable cause, AdtsCode code, Object... args) {
        this(cause, code.value(), args);
    }

    public AdtsException(int errorCode, Object... args) {
        this(AdtsCode.of(errorCode).getReasonPhrase(), errorCode, args);
    }

    public AdtsException(AdtsCode code) {
        this(code, null);
    }

    public AdtsException(AdtsCode code, String message, Object... args) {
        this(StringUtils.isBlank(message) ? code.getReasonPhrase() : message, code.value(), args);
    }

    public AdtsException(String message, int errorCode, Object... args) {
        super(message);
        this.errorCode = errorCode;
        this.args = args;
    }

    public AdtsException(Throwable cause, int errorCode, Object... args) {
        super(cause);
        this.errorCode = errorCode;
        this.args = args;
    }

    public AdtsException(String message, Throwable cause, int errorCode, Object... args) {
        super(message, cause);
        this.errorCode = errorCode;
        this.args = args;
    }

    protected AdtsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace,
            int errorCode, Object... args) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
        this.args = args;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public Object[] getArgs() {
        return args;
    }

    public int getHttpStatusCode() {
        if (errorCode > 0) {
            return errorCode;
        }
        return AdtsCode.INTERNAL_SERVER_ERROR.value();
    }

}

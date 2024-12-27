package com.sijibomiaol.skaetAss.exception;

public class CustomException extends RuntimeException{
    private String message;
    private int code;
    private String details;

    public CustomException(String message, int code, String details) {
        this.message = message;
        this.code = code;
        this.details = details;
    }

    public CustomException(String message) {
        super(message);
        this.message = message;
    }

    public CustomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String message1, int code, String details) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.message = message1;
        this.code = code;
        this.details = details;
    }

    public CustomException(String message, int code) {
        super(message);
        this.code = code;

    }

    public CustomException(String message, String details) {
        super(message);
        this.details = details;
    }
}

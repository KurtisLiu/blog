package com.lk.blog.exception;

public class ParameterException extends RuntimeException {
    public ParameterException(Throwable cause) {
        super(cause);
    }

    public ParameterException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParameterException(String message) {
        super(message);
    }

    public ParameterException() {
        super();
    }
}

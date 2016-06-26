package com.lk.blog.exception;

public class BusinessException extends RuntimeException {
    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }
}

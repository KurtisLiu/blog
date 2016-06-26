package com.lk.blog.exception;

public class DataErrorException extends RuntimeException {
    public DataErrorException() {
        super();
    }

    public DataErrorException(Throwable cause) {
        super(cause);
    }

    public DataErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataErrorException(String message) {
        super(message);
    }
}

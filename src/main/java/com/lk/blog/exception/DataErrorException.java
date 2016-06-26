package com.lk.blog.exception;

/**
 * Created by Administrator on 2016/6/26.
 */
public class DataErrorException extends Exception {
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

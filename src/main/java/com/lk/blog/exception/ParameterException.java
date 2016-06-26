package com.lk.blog.exception;

/**
 * Created by Administrator on 2016/6/26.
 */
public class ParameterException extends Exception {
    public ParameterException(Throwable cause) {
        super(cause);
    }

    public ParameterException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParameterException(String message) {
        super(message);
    }
}

package com.lk.blog.dto;

import java.io.Serializable;

public class AjaxResult implements Serializable {
    private Object data;
    private boolean isSuccess;
    private String message;

    public AjaxResult() {
    }

    public AjaxResult(Object data, boolean isSuccess, String message) {
        this.data = data;
        this.isSuccess = isSuccess;
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
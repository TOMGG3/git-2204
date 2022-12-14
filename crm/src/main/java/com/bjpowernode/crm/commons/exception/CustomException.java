package com.bjpowernode.crm.commons.exception;

public class CustomException extends RuntimeException {

    private Integer code;
    private String message;

    public CustomException() {
    }

    public CustomException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

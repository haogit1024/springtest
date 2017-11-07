package com.czh.exception;

public class LoginException extends RuntimeException {
    private String msg;

    public LoginException(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}

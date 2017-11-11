package com.czh.entity;

public class Token {
    private String token;
    private long time;

    public Token(String token, long time) {
        this.token = token;
        this.time = time;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}

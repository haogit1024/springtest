package com.czh.exception;

public class NotFoundException extends RuntimeException {
    private int id;

    public NotFoundException(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

package com.czh.exception;

public class DatabaseException extends RuntimeException {
    private String table;
    private String operation;
    private String data;

    public DatabaseException(String table, String operation, String data) {
        this.table = table;
        this.operation = operation;
        this.data = data;
    }
}

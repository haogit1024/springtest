package com.czh.response;

/**
 * Created by czh on 17-7-15.
 */
public enum StatusCode {
    OK(200,"OK"),
    NULLREQUESTPARAMETER(101,"请求参数为空"),
    OBJECTNOTEXIST(102,"对象不存在"),
    NOTNUMBERPARAMETER(103,"不是数字参数"),
    FAILOPERATIONDATABASE(104,"数据库操作失败");

    private int code;
    private String message;

    StatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return code + "," + message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

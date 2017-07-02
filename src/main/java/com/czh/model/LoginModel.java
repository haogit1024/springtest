package com.czh.model;

/**
 * Created by czh on 17-7-2.
 */
public class LoginModel {
    private String account;
    private String password;
    private boolean keepLogin;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isKeepLogin() {
        return keepLogin;
    }

    public void setKeepLogin(boolean keepLogin) {
        this.keepLogin = keepLogin;
    }
}

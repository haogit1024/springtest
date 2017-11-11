package com.czh.jwt;

public class Payload {
    private String iss; //发行者
    private String sub; //主题 该JWT所面向的用户
    private String aud; //观众 接收该JWT的一方
    private Long exp; //过期时间
//    private String nbf; //not before
    private Long iat; //发行时间
//    private String jti; //JWT ID

    public String getIss() {
        return iss;
    }

    public void setIss(String iss) {
        this.iss = iss;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getAud() {
        return aud;
    }

    public void setAud(String aud) {
        this.aud = aud;
    }

    public Long getExp() {
        return exp;
    }

    public void setExp(Long exp) {
        this.exp = exp;
    }

//    public String getNbf() {
//        return nbf;
//    }
//
//    public void setNbf(String nbf) {
//        this.nbf = nbf;
//    }

    public Long getIat() {
        return iat;
    }

    public void setIat(Long iat) {
        this.iat = iat;
    }

//    public String getJti() {
//        return jti;
//    }
//
//    public void setJti(String jti) {
//        this.jti = jti;
//    }
}

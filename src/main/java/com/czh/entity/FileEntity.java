package com.czh.entity;

import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;


public class FileEntity implements Serializable {


    private Integer id;
    @NotEmpty
    private Integer uid;
    private String filename;
    private String realname;
    private String url;
    private String md5;
    private int status;
    private String type;
    private long size;
    private String parsonPath;
    private int parsonId;
    private long time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getParsonPath() {
        return parsonPath;
    }

    public void setParsonPath(String parsonPath) {
        this.parsonPath = parsonPath;
    }

    public int getParsonId() {
        return parsonId;
    }

    public void setParsonId(int parsonId) {
        this.parsonId = parsonId;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", uid=" + uid +
                ", filename='" + filename + '\'' +
                ", realname='" + realname + '\'' +
                ", url='" + url + '\'' +
                ", md5='" + md5 + '\'' +
                ", status=" + status +
                ", type='" + type + '\'' +
                ", size=" + size +
                ", parsonPath='" + parsonPath + '\'' +
                ", parsonId=" + parsonId +
                ", time=" + time +
                '}';
    }
}

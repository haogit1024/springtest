package com.czh.entity;

import org.hibernate.validator.constraints.NotEmpty;


public class File {
    private int id;
    @NotEmpty
    private int uid;
    private String filename;
    private String url;
    private String md5;
    private int status;
    private String type;
    private long size;
    private String parsonPath;
    private int parsonId;
    private long time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
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

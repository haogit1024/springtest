package com.czh.dto;


public class FileDTO {
    private int id;
    private String name;
    private String url;
    private String parsonPath;
    private int parsonId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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


    @Override
    public String toString() {
        return "FileDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", parsonPath='" + parsonPath + '\'' +
                ", parsonId=" + parsonId +
                '}';
    }
}

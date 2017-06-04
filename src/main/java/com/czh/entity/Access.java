package com.czh.entity;

import java.sql.Timestamp;

public class Access{
	private Integer id;
	private String token;
	private String jsapi;
	private Timestamp time;

	public Integer getId() { return id; } 

	public void setId(Integer id) { this.id=id; } 

	public String getToken() { return token; } 

	public void setToken(String token) { this.token=token; } 

	public String getJsapi() { return jsapi; } 

	public void setJsapi(String jsapi) { this.jsapi=jsapi; } 

	public Timestamp getTime() { return time; } 

	public void setTime(Timestamp time) { this.time=time; } 

}
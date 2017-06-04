package com.czh.entity;

import java.sql.Timestamp;

public class Msg{
	private Integer id;
	private String access_token;
	private String js_api;
	private Timestamp time;

	public Integer getId() { return id; } 

	public void setId(Integer id) { this.id=id; } 

	public String getAccess_token() { return access_token; } 

	public void setAccess_token(String access_token) { this.access_token=access_token; } 

	public String getJs_api() { return js_api; } 

	public void setJs_api(String js_api) { this.js_api=js_api; } 

	public Timestamp getTime() { return time; } 

	public void setTime(Timestamp time) { this.time=time; } 

}
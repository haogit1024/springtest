package com.czh.entity;


public class Tickler{
	private Integer id;
	private int bid;
	private Integer inputTime;
	private Integer time;
	private String remark;
	private int status;

	public Integer getId() { return id; } 

	public void setId(Integer id) { this.id=id; }

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public Integer getInputTime() { return inputTime; }

	public void setInputTime(Integer inputTime) { this.inputTime=inputTime; } 

	public Integer getTime() { return time; } 

	public void setTime(Integer time) { this.time=time; } 

	public String getRemark() { return remark; } 

	public void setRemark(String remark) { this.remark=remark; }

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
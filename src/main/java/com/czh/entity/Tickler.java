package com.czh.entity;

import java.math.BigDecimal;

public class Tickler{
	private Integer id;
	private String uid;
	private Integer inputTime;
	private Integer time;
	private String remark;
	private float money;
	private int status;

	public Integer getId() { return id; } 

	public void setId(Integer id) { this.id=id; } 

	public String getUid() { return uid; } 

	public void setUid(String uid) { this.uid=uid; } 

	public Integer getInputTime() { return inputTime; } 

	public void setInputTime(Integer inputTime) { this.inputTime=inputTime; } 

	public Integer getTime() { return time; } 

	public void setTime(Integer time) { this.time=time; } 

	public String getRemark() { return remark; } 

	public void setRemark(String remark) { this.remark=remark; } 

	public float getMoney() { return money; }

	public void setMoney(float money) { this.money=money; }

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
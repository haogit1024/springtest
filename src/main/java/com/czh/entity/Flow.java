package com.czh.entity;

import java.sql.Timestamp;

public class Flow{
	private Integer id;
	private Integer subId;
	private String subImg;
	private Timestamp date;
	private Integer empId;
	private String dept;
	private Integer statusId;
	private String opinion;

	public Integer getId() { return id; } 

	public void setId(Integer id) { this.id=id; } 

	public Integer getSubId() { return subId; } 

	public void setSubId(Integer subId) { this.subId=subId; } 

	public String getSubImg() { return subImg; } 

	public void setSubImg(String subImg) { this.subImg=subImg; } 

	public Timestamp getDate() { return date; } 

	public void setDate(Timestamp date) { this.date=date; } 

	public Integer getEmpId() { return empId; } 

	public void setEmpId(Integer empId) { this.empId=empId; } 

	public String getDept() { return dept; } 

	public void setDept(String dept) { this.dept=dept; } 

	public Integer getStatusId() { return statusId; } 

	public void setStatusId(Integer statusId) { this.statusId=statusId; } 

	public String getOpinion() { return opinion; } 

	public void setOpinion(String opinion) { this.opinion=opinion; } 

}
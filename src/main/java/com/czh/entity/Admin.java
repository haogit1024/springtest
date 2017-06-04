package com.czh.entity;

public class Admin{
	private Integer id;
	private String username;
	private String password;
	private String remark;
	private Integer empId;

	public Integer getId() { return id; } 

	public void setId(Integer id) { this.id=id; } 

	public String getUsername() { return username; } 

	public void setUsername(String username) { this.username=username; } 

	public String getPassword() { return password; } 

	public void setPassword(String password) { this.password=password; } 

	public String getRemark() { return remark; } 

	public void setRemark(String remark) { this.remark=remark; } 

	public Integer getEmpId() { return empId; } 

	public void setEmpId(Integer empId) { this.empId=empId; } 

}
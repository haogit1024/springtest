package com.czh.entity;

import java.sql.Timestamp;

public class Submit{
	private Integer subId;
	private String openId;
	private String des;
	private Timestamp subTime;
	private String subImg;
	private Integer statusId;
	private String riskName;
	private String opinion;
	private String build;
	private String part;
	private String solution;
	private Integer budget;
	private String dept;
	private String roleName;
	private Integer empId;
	private Timestamp planTime;
	private Integer fee;
	private String finishImg;
	private Timestamp finishTime;
	private String unfinishReason;
	private String audit;
	private String completion;

	public Integer getSubId() { return subId; } 

	public void setSubId(Integer subId) { this.subId=subId; } 

	public String getOpenId() { return openId; } 

	public void setOpenId(String openId) { this.openId=openId; } 

	public String getDes() { return des; } 

	public void setDes(String des) { this.des=des; } 

	public Timestamp getSubTime() { return subTime; } 

	public void setSubTime(Timestamp subTime) { this.subTime=subTime; } 

	public String getSubImg() { return subImg; } 

	public void setSubImg(String subImg) { this.subImg=subImg; } 

	public Integer getStatusId() { return statusId; } 

	public void setStatusId(Integer statusId) { this.statusId=statusId; } 

	public String getRiskName() { return riskName; } 

	public void setRiskName(String riskName) { this.riskName=riskName; } 

	public String getOpinion() { return opinion; } 

	public void setOpinion(String opinion) { this.opinion=opinion; } 

	public String getBuild() { return build; } 

	public void setBuild(String build) { this.build=build; } 

	public String getPart() { return part; } 

	public void setPart(String part) { this.part=part; } 

	public String getSolution() { return solution; } 

	public void setSolution(String solution) { this.solution=solution; } 

	public Integer getBudget() { return budget; } 

	public void setBudget(Integer budget) { this.budget=budget; } 

	public String getDept() { return dept; } 

	public void setDept(String dept) { this.dept=dept; } 

	public String getRoleName() { return roleName; } 

	public void setRoleName(String roleName) { this.roleName=roleName; } 

	public Integer getEmpId() { return empId; } 

	public void setEmpId(Integer empId) { this.empId=empId; } 

	public Timestamp getPlanTime() { return planTime; } 

	public void setPlanTime(Timestamp planTime) { this.planTime=planTime; } 

	public Integer getFee() { return fee; } 

	public void setFee(Integer fee) { this.fee=fee; } 

	public String getFinishImg() { return finishImg; } 

	public void setFinishImg(String finishImg) { this.finishImg=finishImg; } 

	public Timestamp getFinishTime() { return finishTime; } 

	public void setFinishTime(Timestamp finishTime) { this.finishTime=finishTime; } 

	public String getUnfinishReason() { return unfinishReason; } 

	public void setUnfinishReason(String unfinishReason) { this.unfinishReason=unfinishReason; } 

	public String getAudit() { return audit; } 

	public void setAudit(String audit) { this.audit=audit; } 

	public String getCompletion() { return completion; } 

	public void setCompletion(String completion) { this.completion=completion; } 

}
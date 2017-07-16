package com.czh.entity;

public class Record{
	private Integer id;
	private Integer tid;
	private Integer createTime;
	private Integer time;
	private Integer grade;
	private Float money;

	public Integer getId() { return id; } 

	public void setId(Integer id) { this.id=id; } 

	public Integer getTid() { return tid; } 

	public void setTid(Integer tid) { this.tid=tid; } 

	public Integer getCreateTime() { return createTime; } 

	public void setCreateTime(Integer createTime) { this.createTime=createTime; } 

	public Integer getTime() { return time; } 

	public void setTime(Integer time) { this.time=time; } 

	public Integer getGrade() { return grade; } 

	public void setGrade(Integer grade) { this.grade=grade; } 

	public Float getMoney() { return money; } 

	public void setMoney(Float money) { this.money=money; }

	@Override
	public String toString() {
		return "Record{" +
				"id=" + id +
				", tid=" + tid +
				", createTime=" + createTime +
				", time=" + time +
				", grade=" + grade +
				", money=" + money +
				'}';
	}
}
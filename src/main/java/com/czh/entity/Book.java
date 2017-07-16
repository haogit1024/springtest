package com.czh.entity;

public class Book{
	private Integer id;
	private String name;
	private String uid;
	private Double budget;

	public Integer getId() { return id; } 

	public void setId(Integer id) { this.id=id; } 

	public String getName() { return name; } 

	public void setName(String name) { this.name=name; } 

	public String getUid() { return uid; } 

	public void setUid(String uid) { this.uid=uid; } 

	public Double getBudget() { return budget; } 

	public void setBudget(Double budget) { this.budget=budget; }

	@Override
	public String toString() {
		return "Book{" +
				"id=" + id +
				", name='" + name + '\'' +
				", uid='" + uid + '\'' +
				", budget=" + budget +
				'}';
	}
}
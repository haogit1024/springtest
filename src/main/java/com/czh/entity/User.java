package com.czh.entity;

public class User{
	private String id;
	private String account;
	private String password;
	private String email;
	private String phone;
	private Integer gender;
	private String nickname;
	private String realname;

	public String getId() { return id; } 

	public void setId(String id) { this.id=id; } 

	public String getAccount() { return account; } 

	public void setAccount(String account) { this.account=account; } 

	public String getPassword() { return password; } 

	public void setPassword(String password) { this.password=password; } 

	public String getEmail() { return email; } 

	public void setEmail(String email) { this.email=email; } 

	public String getPhone() { return phone; } 

	public void setPhone(String phone) { this.phone=phone; } 

	public Integer getGender() { return gender; } 

	public void setGender(Integer gender) { this.gender=gender; } 

	public String getNickname() { return nickname; } 

	public void setNickname(String nickname) { this.nickname=nickname; } 

	public String getRealname() { return realname; } 

	public void setRealname(String realname) { this.realname=realname; } 

}
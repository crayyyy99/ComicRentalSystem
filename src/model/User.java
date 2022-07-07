package model;

import java.time.LocalDateTime;

public class User {
	private int id;
	private String username;
	private String password;
	private String name;
	private String icNo;
	private String email;
	private String phoneNo;
	private LocalDateTime regDate;
	private String permission;
	
	public User() {}
		
	public int getId() {
    	return id;
	}	
	public String getUsername() {
    	return username;
	}	
	public String getPassword() {
    	return password;
	}	
	public String getName() {
    	return name;
	}
	public String getIcNo() {
    	return icNo;
	}
	public String getPhoneNo() {
    	return phoneNo;
	}	
	public String getEmail() {
    	return email;
	}
	public LocalDateTime getRegDate() {
    	return regDate;
	}
	public String getPermission() {
    	return permission;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setIcNo(String icNo) {
		this.icNo = icNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}
	
	public void setPermission(String permission) {
    	this.permission = permission;
	}

	
}

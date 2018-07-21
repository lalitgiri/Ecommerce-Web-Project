package com.ecommerce.main.interceptors;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class User implements Serializable{
    private String id;
    private String emailAddress;
    private String  role;
    private String userName;
    private int userId;
    private long phoneNumber;
   
    public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getId () {
        return id;
    }

    public void setId (String id) {
        this.id = id;
    }

  

    public String getEmailAddress () {
        return emailAddress;
    }

    public void setEmailAddress (String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString () {
        return "User{" +
                            "id=" + id +
                            ", emailAddress='" + emailAddress + '\'' +
                            ", userRole='" + role + '\''+
                            '}';
    }
}
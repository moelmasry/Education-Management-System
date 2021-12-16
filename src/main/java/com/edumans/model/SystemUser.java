package com.edumans.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Course Entity which map the system_users table which used for authentication.
 * 
 * @author mohamed.elmasry
 *
 */
@Entity
@Table(name = "SYSTEM_USERS")
public class SystemUser implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "USERNAME")
	private String userName;

	@Column(name = "PASSWORD")
	private String password;

	public SystemUser() {

	}

	public SystemUser(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Course{" + "username='" + userName + "'" + ", password='" + password + "'}";
	}
}

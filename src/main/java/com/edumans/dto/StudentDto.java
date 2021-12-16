package com.edumans.dto;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sun.istack.NotNull;

/**
 * This class work as Data Transfer object for Students Requests and Response
 * 
 * @author mohamed.elmasry
 *
 */

@Component
public class StudentDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	private Long id;
	@NotNull
	private String userName;
	@NotNull
	private String firstName;
	@NotNull
	private String lastName;
	private String address;
	private String phone;
	private List<CourseDto> courses;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<CourseDto> getCourses() {
		return courses;
	}

	public void setCourses(List<CourseDto> courses) {
		this.courses = courses;
	}
}

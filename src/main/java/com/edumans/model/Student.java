package com.edumans.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sun.istack.NotNull;
/**
 * Course Entity which map the Student table.
 * 
 * @author mohamed.elmasry
 *
 */
@Entity
@Table(name = "STUDENT")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Student implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	Long id;

	@NotNull
	@Column(name = "USERNAME")
	String userName;

	@NotNull
	@Column(name = "FIRST_NAME")
	String firstName;

	@NotNull
	@Column(name = "LAST_NAME")
	String lastName;

	@Column(name = "ADDRESS")
	String address;

	@Column(name = "PHONE")
	String phone;

	@JsonManagedReference
	@ManyToMany(targetEntity = Course.class, cascade = CascadeType.ALL)
	List<Course> courses = new ArrayList<>();

	public Student() {
	}

	public Student(String userName, String firstName, String lastName, String address, String phone) {
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phone = phone;
	}

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

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	@Override
	public String toString() {
		return "Student{" + "id=" + id + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\''
				+ ", address='" + address + '\'' + ", phone='" + phone + '\'' + ", courses=" + courses + '}';
	}
}

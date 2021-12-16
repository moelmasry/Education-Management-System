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
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sun.istack.NotNull;

/**
 * Course Entity which map the course table.
 * 
 * @author mohamed.elmasry
 *
 */
@Entity
@Table(name = "COURSE")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Course implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	Long id;

	@NotNull
	@Column(name = "COURSE_NAME")
	String courseName;

	@Column(name = "COURSE_DESCRIPTION")
	String description;

	@ManyToMany(targetEntity = Student.class, mappedBy = "courses", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Student> students = new ArrayList<>();

	public Course() {
	}

	public Course(String courseName, String description) {
		this.courseName = courseName;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "Course{" + "id=" + id + ", courseName='" + courseName + "'" + ", Description='" + description + "'"
				+ '}';
	}

}

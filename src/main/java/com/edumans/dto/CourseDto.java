package com.edumans.dto;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.sun.istack.NotNull;

/**
 * This class work as Data Transfer object for Courses Requests and Response
 * 
 * @author mohamed.elmasry
 *
 */
@Component
public class CourseDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	private Long id;
	private String name;
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

package com.edumans.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.edumans.dto.CourseDto;
import com.edumans.model.Course;

@Component
public interface CourseService {

	
	List<CourseDto> getAllCourses();
	Course getCourseById(Long courseId);
	CourseDto createCourse(CourseDto courseDto);
	CourseDto updateCourse(Long courseId, CourseDto courseDto);
    void deleteCourse(Long courseId);
}
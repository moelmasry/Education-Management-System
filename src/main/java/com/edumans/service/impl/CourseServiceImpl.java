package com.edumans.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.edumans.dto.CourseDto;
import com.edumans.dto.mapper.CourseMapper;
import com.edumans.exception.ResourceNotFoundException;
import com.edumans.model.Course;
import com.edumans.repository.CourseRepository;
import com.edumans.service.CourseService;

/**
 * Courses Service implementation used for hold the course business logic
 */
@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	CourseMapper courseMapper;

	/**
	 * this method get all courses list also it cash it using spring cachable
	 * annotation
	 */
	@Override
	@Cacheable(value = "courses")
	public List<CourseDto> getAllCourses() {
		return courseMapper.toCourseDtoList(courseRepository.findAll());
	}
	
	/**
	 * this method used for get a course by course id
	 * 
	 * @param courseId
	 */
	@Override
	public Course getCourseById(Long courseId) {
		Optional<Course> optionalcourse = courseRepository.findById(courseId);
		if (optionalcourse.isPresent() && optionalcourse.get().getId().equals(courseId)) {
			return optionalcourse.get();
		} else {
			throw new ResourceNotFoundException("course", "id", courseId);
		}

	}

	@Override
	public CourseDto createCourse(CourseDto courseDto) {
		// TODO feature to be added
		return null;
	}

	@Override
	public CourseDto updateCourse(Long courseId, CourseDto courseDto) {
		// TODO feature to be added
		return null;
	}

	@Override
	public void deleteCourse(Long courseId) {
		// TODO feature to be added

	}

}

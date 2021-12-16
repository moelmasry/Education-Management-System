package com.edumans.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.edumans.dto.CourseDto;
import com.edumans.dto.StudentDto;

@Component
public interface StudentService {

	List<StudentDto> getAllStudents();

	StudentDto getStudentById(Long studentId);

	StudentDto createStudent(StudentDto studentDto);

	StudentDto updateStudent(Long studentId, StudentDto studentDto);

	void deleteStudent(Long studentId);

	List<CourseDto> getStudentCourses(Long studentId);

	void registerCourses(Long studentId, List<CourseDto> courseDtoList);

	void cancelCoursesRegistration(Long studentId, List<CourseDto> courseDtoList);

}

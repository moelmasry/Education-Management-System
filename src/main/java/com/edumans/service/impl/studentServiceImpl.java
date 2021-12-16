package com.edumans.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edumans.dto.CourseDto;
import com.edumans.dto.StudentDto;
import com.edumans.dto.mapper.CourseMapper;
import com.edumans.dto.mapper.StudentMapper;
import com.edumans.exception.NoContentException;
import com.edumans.exception.ResourceNotFoundException;
import com.edumans.model.Course;
import com.edumans.model.Student;
import com.edumans.repository.StudentRepository;
import com.edumans.service.CourseService;
import com.edumans.service.StudentService;
/**
 * this service class for handling Student Business logic
 * @author mohamed.elmasry
 *
 */
@Service
public class studentServiceImpl implements StudentService {

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	StudentMapper studentMapper;

	@Autowired
	CourseMapper courseMapper;

	@Autowired
	CourseService courseService;

	/**
	 * get all students 
	 * 
	 */
	@Override
	public List<StudentDto> getAllStudents() {

		List<Student> studentList = studentRepository.findAll();
		if (!studentList.isEmpty()) {
			return studentMapper.toStudentDtoList(studentList);
		} else {
			throw new NoContentException("student");
		}
	}

	/**
	 * get one student by ID
	 * 
	 */
	@Override
	public StudentDto getStudentById(Long studentId) throws ResourceNotFoundException {

		return studentMapper.toStudentDto(fetchStudentById(studentId));
	}

	/**
	 * create student
	 * 
	 */
	@Override
	public StudentDto createStudent(StudentDto studentDto) {
		Student student = studentMapper.toStudenEntity(studentDto);
		studentDto = studentMapper.toStudentDto(studentRepository.save(student));
		return studentDto;
	}

	/**
	 * Update student
	 * 
	 */
	@Override
	public StudentDto updateStudent(Long studentId, StudentDto studentDto) {

		Student originalStudent = fetchStudentById(studentId);
		Student updatedStudent = studentMapper.toStudenEntity(studentDto);

		originalStudent.setFirstName(updatedStudent.getFirstName());
		originalStudent.setLastName(updatedStudent.getLastName());
		originalStudent.setAddress(updatedStudent.getAddress());
		originalStudent.setPhone(updatedStudent.getPhone());
		originalStudent.setUserName(updatedStudent.getUserName());

		originalStudent = studentRepository.save(originalStudent);

		return studentMapper.toStudentDto(originalStudent);
	}

	/**
	 * Delete one student by ID
	 * 
	 */
	@Override
	public void deleteStudent(Long studentId) {
		studentRepository.deleteById(fetchStudentById(studentId).getId());
	}

	/**
	 * get the registered courses for a student by student ID
	 * 
	 */
	@Override
	public List<CourseDto> getStudentCourses(Long studentId) {
		return courseMapper.toCourseDtoList(fetchStudentById(studentId).getCourses());
	}

	/**
	 * Register a student for courses by student ID
	 * 
	 */
	@Override
	public void registerCourses(Long studentId, List<CourseDto> courseDtoList) {
		Student student = fetchStudentById(studentId);

		for (CourseDto courseDto : courseDtoList) {
			Course course = courseService.getCourseById(courseDto.getId());
			if (!student.getCourses().contains(course)) {
				student.getCourses().add(course);
			}
		}
		
		studentRepository.save(student);

	}

	/**
	 * Cancel courses Registration for  a student by student ID
	 * 
	 */
	@Override
	public void cancelCoursesRegistration(Long studentId, List<CourseDto> courseDtoList) {
		Student student = fetchStudentById(studentId);

		for (CourseDto courseDto : courseDtoList) {
			Course course = courseService.getCourseById(courseDto.getId());
			if (student.getCourses().contains(course)) {
				student.getCourses().remove(course);
			}
		}
		
		studentRepository.save(student);
	}

	/**
	 * Helper method to fetch one student by student ID
	 * 
	 */
	private Student fetchStudentById(Long studentId) {
		Optional<Student> studentOptional = studentRepository.findById(studentId);
		if (studentOptional.isPresent() && studentOptional.get() != null) {
			return studentOptional.get();
		} else {
			throw new ResourceNotFoundException("Student", "id", studentId);
		}

	}

}

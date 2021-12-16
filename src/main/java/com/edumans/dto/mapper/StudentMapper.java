package com.edumans.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.edumans.dto.CourseDto;
import com.edumans.dto.StudentDto;
import com.edumans.model.Student;
/**
 * this class used for {@link Student}/{@link StudentDto} mapping
 *
 * @author mohamed.elmasry
 *
 */
@Component
public class StudentMapper {
	@Autowired
	CourseMapper courseMapper;

	/**
	 * Map {@link Student} to {@link StudentDto}
	 * @param student 
	 * @return
	 */
	
	public StudentDto toStudentDto(Student student) {
		List<CourseDto> coursesDtoList = courseMapper.toCourseDtoList(student.getCourses());

		StudentDto studentDto = new StudentDto();
		studentDto.setId(student.getId());
		studentDto.setUserName(student.getUserName());
		studentDto.setFirstName(student.getFirstName());
		studentDto.setLastName(student.getLastName());
		studentDto.setPhone(student.getPhone());
		studentDto.setAddress(student.getAddress());
		studentDto.setCourses(coursesDtoList);

		return studentDto;
	}

	/**
	 * Map list of {@link Student} to list of {@link StudentDto}
	 * @param studentList list of students Entities
	 * @return
	 */
	public List<StudentDto> toStudentDtoList(List<Student> studentList) {
		List<StudentDto> studentDtoList = new ArrayList<>();
		if (studentList != null) {
			for (Student student : studentList) {
				studentDtoList.add(this.toStudentDto(student));
			}
		}
		return studentDtoList;
	}

	/**
	 * map {@link StudentDto} to {@link Student}
	 * @param studentDto
	 * @return
	 */
	public Student toStudenEntity(StudentDto studentDto) {
		Student student = new Student(studentDto.getUserName(), studentDto.getFirstName(), studentDto.getLastName(),
				studentDto.getAddress(), studentDto.getPhone());
		return student;
	}
}

package com.edumans.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edumans.dto.CourseDto;
import com.edumans.dto.DefaultResponesDto;
import com.edumans.dto.StudentDto;
import com.edumans.service.StudentService;
import com.edumans.utils.PDFBuilder;
import com.lowagie.text.DocumentException;
/**
 * this Controller to manage Student API requests
 * 
 * @author mohamed.elmasry
 *
 */
@RestController
@RequestMapping("/students")
public class StudentController {

	@Autowired
	StudentService studentService;

	/*********************************** Task Requested APIs *****************************/

	/**
	 * get all Students
	 * 
	 * @return
	 */
	@GetMapping()
	public ResponseEntity<List<StudentDto>> listStudents() {
		List<StudentDto> studentsDtoList = studentService.getAllStudents();
		return new ResponseEntity<List<StudentDto>>(studentsDtoList, HttpStatus.OK);
	}

	/**
	 *  get student courses based on student ID
	 * @param studentId
	 * @return
	 */
	@GetMapping("/{id}/courses")
	public ResponseEntity<List<CourseDto>> getStudentCourses(@PathVariable("id") Long studentId) {
		List<CourseDto> studentCourses = studentService.getStudentCourses(studentId);
		return new ResponseEntity<>(studentCourses, HttpStatus.OK);
	}

	/**
	 * Using this API consumer can register a student on courses based on student ID
	 * 
	 * @param studentId
	 * @param courseDtoList
	 * @return
	 */
	@PostMapping("/{id}/courses-registration")
	public ResponseEntity<DefaultResponesDto> coursesRegistration(@PathVariable("id") Long studentId,
			@RequestBody List<CourseDto> courseDtoList) {
		studentService.registerCourses(studentId, courseDtoList);
		return new ResponseEntity<DefaultResponesDto>(new DefaultResponesDto(HttpStatus.OK,"Courses Registered successfully"), HttpStatus.CREATED);
	}

	/**
	 * Using this API consumer can cancel student registration based on student ID
	 * @param studentId
	 * @param courseDtoList
	 * @return
	 */
	@PostMapping("/{id}/courses-cancelation")
	public ResponseEntity<DefaultResponesDto> coursesCancelation(@PathVariable("id") Long studentId,
			@RequestBody List<CourseDto> courseDtoList) {
		studentService.cancelCoursesRegistration(studentId, courseDtoList);
		return new ResponseEntity<DefaultResponesDto>(new DefaultResponesDto(HttpStatus.OK,"Courses canceled sucessfully"), HttpStatus.CREATED);
	}

	/**
	 *  Using this API consumer can export student/courses schedule based on student ID
	 * @param studentId
	 * @return
	 * @throws DocumentException
	 * @throws IOException
	 */
	@PostMapping(value = "/{id}/pdfschedule", produces = MediaType.APPLICATION_PDF_VALUE)
	public Object exportStudentCoursesSchedule(@PathVariable("id") Long studentId)
			throws DocumentException, IOException {
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		StudentDto studentDto = studentService.getStudentById(studentId);

		String headerKey = "Content-Disposition";
		String headerValue = "inline; filename=courses_" + studentDto.getFirstName() + "-" + studentDto.getLastName()
				+ "_" + currentDateTime + ".pdf";
		HttpHeaders headers = new HttpHeaders();
		headers.add(headerKey, headerValue);

		List<CourseDto> courseDtoList = studentService.getStudentCourses(studentId);

		PDFBuilder exporter = new PDFBuilder(courseDtoList, studentDto);
		ByteArrayInputStream bis = exporter.build();

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));

	}

	/********************************** Extra CRUD APIs *******************************/

	@GetMapping("/{id}")
	public ResponseEntity<StudentDto> getStudentById(@PathVariable("id") Long studentId) {

		StudentDto studentDto = studentService.getStudentById(studentId);
		return new ResponseEntity<>(studentDto, HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<StudentDto> addStudent(@RequestBody StudentDto studentDto) {
		studentDto = studentService.createStudent(studentDto);
		return new ResponseEntity<>(studentDto, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<StudentDto> updateStudent(@PathVariable Long id, @RequestBody StudentDto studentDto) {
		studentDto = studentService.updateStudent(id, studentDto);
		return new ResponseEntity<>(studentDto, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteStudent(@PathVariable("id") Long studentId) {
		studentService.deleteStudent(studentId);
		return new ResponseEntity<>(new DefaultResponesDto(HttpStatus.OK,"User Deleted") , HttpStatus.OK);
	}

}

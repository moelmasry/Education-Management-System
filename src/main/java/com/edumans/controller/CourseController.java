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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edumans.dto.CourseDto;
import com.edumans.service.CourseService;
import com.edumans.utils.PDFBuilder;
import com.lowagie.text.DocumentException;
/**
 * this controller to manage/route the Courses API requests
 * 
 * @author mohamed.elmasry
 *
 */

@RestController
@RequestMapping("/courses")
public class CourseController {

	@Autowired
	CourseService courseService;

	/**
	 * get All courses from database 
	 * 
	 */
	@GetMapping()
	public Object listCourses() {
		List<CourseDto> coursesDtoList = courseService.getAllCourses();
		return new ResponseEntity<List<CourseDto>>(coursesDtoList, HttpStatus.OK);
	}

	@PostMapping(value = "/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
	public Object exportCoursesToPDF() throws DocumentException, IOException {
		// Format date for file naming
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		// Add File information to the response header.
		String headerKey = "Content-Disposition";
		String headerValue = "inline; filename=courses_" + currentDateTime + ".pdf";
		HttpHeaders headers = new HttpHeaders();
		headers.add(headerKey, headerValue);

		List<CourseDto> courseDtoList = courseService.getAllCourses();

		// build the PDF file
		PDFBuilder builder = new PDFBuilder(courseDtoList);
		ByteArrayInputStream bis = builder.build();

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
	}

}

package com.edumans.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.edumans.model.Course;
import com.edumans.model.Student;
import com.edumans.model.SystemUser;
import com.edumans.repository.StudentRepository;
import com.edumans.repository.SystemUserRepository;

@Component
public class DummyDataGeneration {

	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	SystemUserRepository systemUserRepository;
	
	public void generateStudentCoursesReg() {
		Student student1= new Student("momasry", "Mohamed", "Elmasry","Sharqia", "0123356");
		Student student2= new Student("jhussien", "Jasser", "Hussein","Cairo", "0128365" );
		Student student3= new Student("ofarouq", "Omar", "Farouq", "Giza", "0112569");
		
		
		Course course1 = new Course ("Software Engineering",
				"The course is an introduction to the discipline of Software Engineering");
		Course course2 = new Course ("Java Programming Fundamentals", "Exploring the fundamentals of Java programming");
		Course course3 = new Course ("React Fundamentals", "Learn about JSX and how to build your first component.");
		Course course4 = new Course ("MicroServices", "The course Explain MicroServices Fundamentals");
		Course course5 = new Course ("Spring Boot", "Make your development easier");
		
		
		student1.getCourses().add(course1);
		student1.getCourses().add(course2);
		student1.getCourses().add(course5);
		student2.getCourses().add(course3);
		student2.getCourses().add(course4);
		student3.getCourses().add(course2);
		student3.getCourses().add(course1);
		student3.getCourses().add(course5);
		
		List<Student> studentList = Arrays.asList(student1,student2,student3);
		studentRepository.saveAll(studentList);
	}

	
	public void generateSystemUsers() {
		
		SystemUser sysu1 = new SystemUser("admin","admin");
		SystemUser sysu2 = new SystemUser("momasry","student1");
		SystemUser sysu3 = new SystemUser("jhussien","student2");
		SystemUser sysu4 = new SystemUser("ofarouq","student3");
		
		List<SystemUser> usersList = new ArrayList<SystemUser>();
		usersList.add(sysu1);
		usersList.add(sysu2);
		usersList.add(sysu3);
		usersList.add(sysu4);


		systemUserRepository.saveAllAndFlush(usersList);
	}
	
	
	
}

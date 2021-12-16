package com.edumans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import com.edumans.utils.DummyDataGeneration;

@SpringBootApplication
@EnableCaching
public class EduManSysApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(EduManSysApplication.class, args);
	}
	
	
	/**
	 * this code to automatic add dummy data for test
	 */
    @Autowired
    DummyDataGeneration dummyDataGeneration;
    @Override
    public void run(String... args) throws Exception {

    	dummyDataGeneration.generateSystemUsers();
    	dummyDataGeneration.generateStudentCoursesReg();
    }

}

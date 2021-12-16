package com.edumans.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.edumans.dto.CourseDto;
import com.edumans.model.Course;
/**
 * this class used for {@link Course}/{@link CourseDto} mapping
 *
 * @author mohamed.elmasry
 *
 */
@Component
public class CourseMapper {
	/**
	 * Map {@link Course} to {@link CourseDto}
	 * @param courseEntity
	 * @return
	 */
	public CourseDto toCourseDto(Course courseEntity) {
		CourseDto courseDto = new CourseDto();
        courseDto.setId(courseEntity.getId());
        courseDto.setName(courseEntity.getCourseName());
        courseDto.setDescription(courseEntity.getDescription());
        
        return courseDto;
	}
	
	/**
	 * Map {@link CourseDto} to {@link Course}
	 * @param courseEntity
	 * @return
	 */
	public Course toCourseEntity(CourseDto courseDto) {
		Course courseEntity = new Course(courseDto.getName(),courseDto.getDescription());
       
        return courseEntity;
	}
	
	/**
	 * Map list of {@link Course} to list of {@link CourseDto}
	 * @param courseList list of {@link Course}
	 * @return
	 */
    public List<CourseDto> toCourseDtoList(List<Course> courseList){
        List<CourseDto> courseDtoList = new ArrayList<>();
        if(courseList !=null) {
            for (Course course : courseList) {
                courseDtoList.add(toCourseDto(course));
            }
        }
        return courseDtoList;
    }
    
    /**
	 * Map list of {@link CourseDto} to list of {@link Course}
	 * @param courseDtoList list of {@link CourseDto}
	 * @return
	 */
    
    public List<Course> toCourseEntityList(List<CourseDto> courseDtoList){
        List<Course> courseEntityList = new ArrayList<>();
        if(courseDtoList !=null) {
            for (CourseDto courseDto : courseDtoList) {
            	courseEntityList.add(toCourseEntity(courseDto));
            }
        }
        return courseEntityList;
    }
    
}

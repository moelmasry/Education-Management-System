package com.edumans.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edumans.model.Course;
/**
 * courses Repository
 * @author mohamed.elmasry
 *
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}

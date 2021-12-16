package com.edumans.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edumans.model.Student;
/**
 * Students Repository
 * @author mohamed.elmasry
 *
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}

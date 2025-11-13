package com.trangbn.springboot_application.repository;

import com.trangbn.springboot_application.model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Courses, Integer> {

}

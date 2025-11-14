package com.trangbn.springboot_application.repository;

import com.trangbn.springboot_application.model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Courses, Integer> {

    List<Courses> findByOrderByNameDesc();

    List<Courses> findByOrderByName();
}

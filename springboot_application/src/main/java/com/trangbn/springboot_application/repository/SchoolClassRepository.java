package com.trangbn.springboot_application.repository;

import com.trangbn.springboot_application.model.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolClassRepository extends JpaRepository<SchoolClass,Integer> {


}

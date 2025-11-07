package com.trangbn.springboot_application.repository;

import com.trangbn.springboot_application.model.Holiday;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HolidayRepository extends CrudRepository<Holiday,String> {

}

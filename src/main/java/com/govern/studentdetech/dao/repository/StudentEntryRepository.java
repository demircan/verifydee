package com.govern.studentdetech.dao.repository;

import org.springframework.data.repository.CrudRepository;

import com.govern.studentdetech.dao.entity.StudentEntry;

public  interface StudentEntryRepository extends CrudRepository<StudentEntry, String> {

}

package com.govern.studentdetech;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.govern.studentdetech.dao.entity.StudentEntry;
import com.govern.studentdetech.dao.repository.StudentEntryRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DatabaseTest {

	@Autowired
	private StudentEntryRepository studentEntryRepository;
	
	@Test
	public void databaseTest()  {
		
		List<StudentEntry> studentList =  (List<StudentEntry>) studentEntryRepository.findAll();
		assert(studentList.size() >= 0);
	}
	
	@Test
	public void insertStudentTest() {
		
	
		StudentEntry studentEntry = StudentEntry.builder()
												.name("caglar")
												.lastname("demircan")
												.tckNo("27739642448")
												.verified(true)
												.userId("abcde123")
												.expireDate(LocalDate.now())
												.build();
		
		StudentEntry student = studentEntryRepository.save(studentEntry);
		
		List<StudentEntry> studentList =  (List<StudentEntry>) studentEntryRepository.findAll();
		assert(student.getName().equalsIgnoreCase(studentEntry.getName()));
	}
	
	
}

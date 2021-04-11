package com.govern.studentdetech.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.govern.studentdetech.bean.StudentInfoRequest;
import com.govern.studentdetech.dao.entity.OperationEntry;
import com.govern.studentdetech.dao.repository.OperationEntryRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OperationService {

	@Autowired
	private OperationEntryRepository operationEntryRepository;
	
	public void createOperationRecord(StudentInfoRequest studentInfoRequest, boolean verified) {
		
	OperationEntry operationEntry = OperationEntry.builder()
												  .txnId(UUID.randomUUID().toString())
												  .tckNo(studentInfoRequest.getTckn())
												  .barkodNo(studentInfoRequest.getBarkodNo())
												  .operationDate(LocalDateTime.now())
												  .verified(verified)
												  .build();
												  
	operationEntryRepository.save(operationEntry);	
		
		
	}
	
	
}

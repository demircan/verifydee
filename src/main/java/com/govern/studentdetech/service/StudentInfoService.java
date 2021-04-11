package com.govern.studentdetech.service;

import java.time.LocalDate;
import java.util.Optional;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;

import com.govern.studentdetech.bean.StudentInfoCheckResponse;
import com.govern.studentdetech.bean.StudentInfoRequest;
import com.govern.studentdetech.bean.StudentInfoResponse;
import com.govern.studentdetech.dao.entity.StudentEntry;
import com.govern.studentdetech.dao.repository.StudentEntryRepository;
import com.govern.studentdetech.ws.ResponseBeanWrapped;
import com.govern.studentdetech.ws.rest.IStudentInfoCheckerClient;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class StudentInfoService {

	@Autowired
	private IStudentInfoCheckerClient studentInfoCheckerClient;
	
	@Autowired
	private StudentEntryRepository studentEntryRepository;
	
	@Autowired
	private OperationService operationService;
	
	
	public StudentInfoCheckResponse checkStudentInfo(StudentInfoRequest studentInfoRequest) throws Exception {
		
		log.info("checkStudentInfo method started: {}", studentInfoRequest);
		ResponseBeanWrapped<StudentInfoResponse> response = studentInfoCheckerClient.checkStudentStatus(studentInfoRequest);
		
		if(response.getStatus() == HttpStatus.OK && response.getResponse().isStatus()) {
			
			Optional<StudentEntry> studentEntry = studentEntryRepository.findById(DigestUtils.md5Hex(studentInfoRequest.getTckn()));
			if(studentEntry.isPresent()) {			
				
				studentEntry.get().setVerified(true);
				studentEntry.get().setExpireDate(LocalDate.now().with(lastDayOfYear()));
				studentEntryRepository.save(studentEntry.get());
				
			} else {

				StudentEntry newStudent = StudentEntry.builder()
													  .userId(DigestUtils.md5Hex(studentInfoRequest.getTckn()))
													  .name(studentInfoRequest.getName())
													  .lastname(studentInfoRequest.getSurname())
													  .tckNo(studentInfoRequest.getTckn())
													  .verified(true)
													  .expireDate(LocalDate.now().with(lastDayOfYear()))
													  .build();	
				studentEntryRepository.save(newStudent);
			}
			
			
		} 
		
		return new StudentInfoCheckResponse(response.getResponse());
	}
	
	
	public StudentInfoCheckResponse getStudentVerificationInfo(StudentInfoRequest studentInfoRequest) throws Exception {
		
		log.info("getStudentVerificationInfo method started: {}", studentInfoRequest);
		Optional<StudentEntry> studentEntry = studentEntryRepository.findById(DigestUtils.md5Hex(studentInfoRequest.getTckn()));
		
		if(studentEntry.isPresent() && studentEntry.get().getVerified() && studentEntry.get().getExpireDate().isAfter(LocalDate.now())) {
			log.info("StudentEntry record found: {}", studentInfoRequest.getTckn());
			operationService.createOperationRecord(studentInfoRequest, true);
			return new StudentInfoCheckResponse(true);
		} else {
			log.info("StudentEntry record not found call verify ws: {}", studentInfoRequest.getTckn());
			StudentInfoCheckResponse studentInfoCheckResponse = checkStudentInfo(studentInfoRequest);
			operationService.createOperationRecord(studentInfoRequest, studentInfoCheckResponse.isActiveStudent());
			return studentInfoCheckResponse;
		}
		
		
		
	}
	
}

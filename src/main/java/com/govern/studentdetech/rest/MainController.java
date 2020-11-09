package com.govern.studentdetech.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.govern.studentdetech.bean.StudentInfoCheckResponse;
import com.govern.studentdetech.bean.StudentInfoRequest;
import com.govern.studentdetech.bean.StudentInfoResponse;
import com.govern.studentdetech.service.StudentInfoService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/state/rest/")
@Slf4j
public class MainController {
	
	@Autowired
	private StudentInfoService studentInfoService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/check-student", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public StudentInfoCheckResponse notifySubscriberChange(@RequestBody StudentInfoRequest studentInfoRequest) throws Exception {
		
		log.info("check-student request received: tckn: {} barkodNo: {}", studentInfoRequest.getTckn(), studentInfoRequest.getBarkodNo());

		StudentInfoCheckResponse studentInfoCheckResponse = studentInfoService.checkStudentInfo(studentInfoRequest);
		
		return studentInfoCheckResponse;
	}
	
	
}

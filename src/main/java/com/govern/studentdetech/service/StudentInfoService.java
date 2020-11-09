package com.govern.studentdetech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.govern.studentdetech.bean.StudentInfoCheckResponse;
import com.govern.studentdetech.bean.StudentInfoRequest;
import com.govern.studentdetech.bean.StudentInfoResponse;
import com.govern.studentdetech.ws.ResponseBeanWrapped;
import com.govern.studentdetech.ws.rest.IStudentInfoCheckerClient;


@Service
public class StudentInfoService {

	@Autowired
	private IStudentInfoCheckerClient studentInfoCheckerClient;
	
	public StudentInfoCheckResponse checkStudentInfo(StudentInfoRequest studentInfoRequest) throws Exception {
		
		ResponseBeanWrapped<StudentInfoResponse> response = studentInfoCheckerClient.checkStudentStatus(studentInfoRequest);
		return new StudentInfoCheckResponse(response.getResponse());
	}
	
}

package com.govern.studentdetech.bean;

import lombok.Data;

@Data
public class StudentInfoCheckResponse {

	
	private boolean activeStudent;	
	private int login;
	private ResponseData data;
	
	public StudentInfoCheckResponse(StudentInfoResponse studentInfoResponse) {
		
		this.activeStudent = studentInfoResponse.isStatus();
		this.data = studentInfoResponse.getData();
		this.login = studentInfoResponse.getLogin();
		
	}
	
	
}

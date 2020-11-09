package com.govern.studentdetech.ws.rest;

import com.govern.studentdetech.bean.StudentInfoRequest;
import com.govern.studentdetech.ws.ResponseBeanWrapped;

public interface IStudentInfoCheckerClient<T> {

	public ResponseBeanWrapped<T> checkStudentStatus(StudentInfoRequest studentInfoRequest) throws Exception;
	
}

package com.govern.studentdetech.ws.rest;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import com.govern.studentdetech.bean.StudentInfoRequest;
import com.govern.studentdetech.bean.StudentInfoResponse;
import com.govern.studentdetech.ws.ResponseBeanWrapped;
import com.govern.studentdetech.ws.TemplateRestSender;

@Service
public class StudentInfoCheckerClient extends TemplateRestSender<String, StudentInfoResponse> implements IStudentInfoCheckerClient<StudentInfoResponse> {

	@PostConstruct
	void initial(){
		init();
	}
	
	@Override
	public ResponseBeanWrapped<StudentInfoResponse> checkStudentStatus(StudentInfoRequest studentInfoRequest) throws Exception {
		
		ResponseBeanWrapped<StudentInfoResponse> response = sendRest(null, "https://m.turkiye.gov.tr/api.php?p=belge-dogrulama&qr=barkodlubelgedogrulama://barkod:" + studentInfoRequest.getBarkodNo() + ";tckn:" + studentInfoRequest.getTckn(), new HttpHeaders());
		logger.info("Student info check result -> tckn: {} HttpResultCode: {} ", studentInfoRequest, response.getStatus().value());
		return response;
	}
	
	@Override
	public HttpMethod getHttpMethod() {
		return HttpMethod.POST;
	}

	@Override
	public Class<StudentInfoResponse> getResponseClass() {
		return StudentInfoResponse.class;
	}

	
}

package com.govern.studentdetech.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.govern.studentdetech.bean.StudentInfoCheckResponse;
import com.govern.studentdetech.bean.StudentInfoRequest;
import com.govern.studentdetech.service.StudentInfoService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UserController {
	
	@Autowired
	private StudentInfoService studentInfoService;

	@GetMapping(value="new-query")
	public String addNewProject(){
		return "student-check";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/check-student")
	public String notifySubscriberChange(@RequestParam String tckn,
														   @RequestParam String barkodNo) throws Exception {
		
		log.info("check-student request received: tckn: {} barkodNo: {}", tckn, barkodNo);
		StudentInfoCheckResponse studentInfoCheckResponse = studentInfoService.checkStudentInfo(new StudentInfoRequest(tckn, barkodNo));
		
		if(studentInfoCheckResponse.isActiveStudent()) {
			return "verify-success";
		} 
		
		return "verify-error";
	}
	
	
	@GetMapping(value="student-check")
	public String checkbootstrap(){
		return "index";
	}
	
	@GetMapping(value="index")
	public String getIndex(){
		return "index";
	}
	
}
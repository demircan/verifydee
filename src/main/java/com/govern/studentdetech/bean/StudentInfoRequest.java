package com.govern.studentdetech.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentInfoRequest {

	private String name;
	private String surname;
	private String tckn;
	private String barkodNo;
	
}

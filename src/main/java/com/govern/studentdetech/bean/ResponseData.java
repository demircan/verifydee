package com.govern.studentdetech.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Data;

//@JsonRootName(value = "Data")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ResponseData {

	private String kimlikNo;
	
	private String barkodluBelge;
	

}

package com.govern.studentdetech.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentInfoResponse {

	@JsonProperty("kuyrukMu")
	private Integer kuyruk;
	
	@JsonProperty("return")
	private boolean status;
	
	private int login;
	private ResponseData data;
	
}

package com.him.schoolmanagementsystem.config;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MainError implements Serializable {

	
	private static final long serialVersionUID = 1L;
	public static final Integer ERROR_BAD_REQUEST=400;
	
	@JsonProperty("Code")
	private Integer code;
	
	@JsonProperty("Message")
	private String message;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MainError(Integer code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	
	

}

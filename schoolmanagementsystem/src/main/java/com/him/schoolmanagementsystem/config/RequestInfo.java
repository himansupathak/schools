package com.him.schoolmanagementsystem.config;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestInfo<T> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@JsonProperty("RequestInfo")
	private T requestInfo;
	public T getRequestInfo() {
		return requestInfo;
	}
	public void setRequestInfo(T requestInfo) {
		this.requestInfo = requestInfo;
	}
	
	
	
	

}

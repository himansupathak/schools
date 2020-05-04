package com.him.schoolmanagementsystem.config;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseInfo<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	@JsonProperty("ResponseInfo")
	private T responseInfo;

	public ResponseInfo(T responseInfo) {
		this.responseInfo = responseInfo;
	}

	public T getResponseInfo() {
		return responseInfo;
	}

	public void setResponseInfo(T responseInfo) {
		this.responseInfo = responseInfo;
	}

}

package com.him.schoolmanagementsystem.config;

import java.util.ArrayList;
import java.util.List;

public class ValidationException extends MainException {

	
	private static final long serialVersionUID = 1L;
	
	private List<MainError> mainError=new ArrayList<>();

	public ValidationException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public ValidationException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ValidationException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ValidationException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public List<MainError> getMainError() {
		return mainError;
	}

	public void setMainError(List<MainError> mainError) {
		this.mainError = mainError;
	}
	
	
	

}

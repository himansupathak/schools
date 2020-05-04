package com.him.schoolmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.him.schoolmanagementsystem.config.DateTimeUtil;
import com.him.schoolmanagementsystem.config.MainException;
import com.him.schoolmanagementsystem.config.RequestInfo;
import com.him.schoolmanagementsystem.config.ResponseInfo;
import com.him.schoolmanagementsystem.model.Employee;
import com.him.schoolmanagementsystem.model.Role;
import com.him.schoolmanagementsystem.service.EmployeeService;

@RestController
@RequestMapping(value = "/api/employee")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {
	
	@Autowired EmployeeService employeeService;
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseInfo<String>> addEmployee(@RequestBody RequestInfo<Employee> requestInfo)
			throws MainException {

		String result = null;
		try {
			Employee roleEntity = requestInfo.getRequestInfo();
			/*if (roleEntity != null) {
				roleEntity.setCreatedBy("User");
				roleEntity.setCreatedDate(DateTimeUtil.currentTimeStamp());
			}*/
			employeeService.addEmployee(roleEntity);
			result = "Employee is added";
		} catch (MainException e) {
			// TODO: handle exception
			throw e;
		} catch (Exception e) {
			throw new MainException(e);
		}
		return new ResponseEntity<ResponseInfo<String>>(new ResponseInfo<String>(result), HttpStatus.CREATED);

	}
	//getEmployee for login

		@RequestMapping(value = "/{emailid}/{password}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<ResponseInfo<Employee>> getEmployeeForLogin(@PathVariable("emailid") String emailid,
				@PathVariable("password") String password)
				throws MainException {

			Employee employee = null;
			try {
				
				employee=employeeService.getEmployeeForLogin(emailid,password);
				
			} catch (MainException e) {
				throw e;
			} catch (Exception e) {
				throw new MainException(e);
			}
			return new ResponseEntity<ResponseInfo<Employee>>(new ResponseInfo<Employee>(employee), HttpStatus.CREATED);

		} 

}

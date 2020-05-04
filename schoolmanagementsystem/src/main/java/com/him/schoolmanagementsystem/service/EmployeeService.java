package com.him.schoolmanagementsystem.service;

import com.him.schoolmanagementsystem.config.NotFoundException;
import com.him.schoolmanagementsystem.config.ServiceException;
import com.him.schoolmanagementsystem.config.ValidationException;
import com.him.schoolmanagementsystem.model.Employee;
import com.him.schoolmanagementsystem.model.Role;

public interface EmployeeService {
	public Employee addEmployee(Employee employeeToAdd) throws NotFoundException, ValidationException, ServiceException;

	public Employee getEmployeeForLogin(String emailid, String password) throws NotFoundException, ValidationException, ServiceException;
}

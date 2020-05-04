package com.him.schoolmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.him.schoolmanagementsystem.config.MainException;
import com.him.schoolmanagementsystem.config.NotFoundException;
import com.him.schoolmanagementsystem.config.ServiceException;
import com.him.schoolmanagementsystem.config.ValidationException;
import com.him.schoolmanagementsystem.model.Employee;
import com.him.schoolmanagementsystem.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired EmployeeRepository employeeRepository; 
	@Override
	public Employee addEmployee(Employee employeeToAdd)
			throws NotFoundException, ValidationException, ServiceException {
		Employee employee=null;
		try {
			/*if(roleToAdd.getRoleName() ==null)
			{
				MainError error=new MainError(MainError.ERROR_BAD_REQUEST,"Role is Required.");
				ValidationException c=new ValidationException(error.getMessage());
				c.getMainError().add(error);
				throw c;
			}
			Role exitRole= roleRepository.findRoleByRoleName(roleToAdd.getRoleName());
			if(exitRole!=null)
			{
		
				MainError error=new MainError(MainError.ERROR_BAD_REQUEST,"Role [" + roleToAdd.getRoleName() +"] already exits.");
				ValidationException c=new ValidationException(error.getMessage());
				c.getMainError().add(error);
				throw c;
			}*/
			//BaseEntity baseEntity=new BaseEntity(employeeToAdd.getCreatedBy(),employeeToAdd.getCreatedDate(),null,null,roleToAdd.getStatus());
			//roleToAdd.setBaseEntity(baseEntity);
			employee=employeeRepository.addEmployee(employeeToAdd);
		}
	   /* catch(ValidationException noexp)
		{
			throw noexp;
		}
		*/
		catch (MainException mexp) {
			throw new ServiceException(mexp);
		}
		catch (Exception e) {
			throw new ServiceException(new MainException(e));
		}
		return employee;
	}
	@Override
	public Employee getEmployeeForLogin(String emailid, String password)
			throws NotFoundException, ValidationException, ServiceException {
		Employee result=null;
		try {
			
			result=employeeRepository.getEmployeeForLogin(emailid,password);
			if(result ==null)
			{
				throw new NotFoundException("Bad Credential!!");
			}
			
		}
		 catch(NotFoundException noexp)
		{
			throw noexp;
		}
		
		catch (MainException mexp) {
			throw new ServiceException(mexp);
		}
		catch (Exception e) {
			throw new ServiceException(new MainException(e));
		}
		return result;	
	}
	
}

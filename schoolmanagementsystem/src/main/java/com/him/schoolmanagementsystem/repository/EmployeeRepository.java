package com.him.schoolmanagementsystem.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.him.schoolmanagementsystem.config.PersistenceException;
import com.him.schoolmanagementsystem.model.Employee;
import com.him.schoolmanagementsystem.model.Role;


@Component
public class EmployeeRepository {

	@Autowired private EmployeeJpaRepo employeeRepo;
	@PersistenceContext private EntityManager em;
	
	@Transactional(rollbackFor= PersistenceException.class)
	public Employee addEmployee(Employee employeeToAdd) throws PersistenceException {
		Employee result=null;
		try {
			result=employeeRepo.save(employeeToAdd);
			//em.clear();
		}
		catch (Throwable e) {
			// TODO: handle exception
			throw new PersistenceException(e.getMessage());
		}
		
		return result;
		
		
	}
	@Transactional(readOnly=true,rollbackFor=PersistenceException.class
			)
	public Employee getEmployeeForLogin(String emailid, String password) throws PersistenceException  {
		Employee role=null;
		try {
			
			String sql = "SELECT * FROM `employeemaster` WHERE `emailid`=?1 and `password`=?2";
			Query q = em.createNativeQuery(sql, Employee.class);
			q.setParameter(1, emailid);
			q.setParameter(2, password);
			
			role = (Employee) q.getSingleResult();
		
		}
		catch (Throwable e) {
			role=null;
		}
		return role;
	}
}


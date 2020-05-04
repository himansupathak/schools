package com.him.schoolmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.him.schoolmanagementsystem.model.Employee;
import com.him.schoolmanagementsystem.model.Role;



@Repository
public interface EmployeeJpaRepo extends JpaRepository<Employee, Integer> {
	

}

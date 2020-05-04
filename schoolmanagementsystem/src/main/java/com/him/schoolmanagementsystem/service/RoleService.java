package com.him.schoolmanagementsystem.service;

import java.util.List;

import com.him.schoolmanagementsystem.config.NotFoundException;
import com.him.schoolmanagementsystem.config.ServiceException;
import com.him.schoolmanagementsystem.config.ValidationException;
import com.him.schoolmanagementsystem.model.Role;

public interface RoleService{
	
	public Role addRole(Role roleToAdd) throws NotFoundException, ValidationException, ServiceException;

	public Role getRole(int id) throws NotFoundException, ValidationException, ServiceException;

	public List<Role> getAllRole() throws NotFoundException, ValidationException, ServiceException;

	public Role updateRole(int id, Role roleEntity) throws NotFoundException, ValidationException, ServiceException;

	public void deleteRole(int id) throws NotFoundException, ValidationException, ServiceException;


}

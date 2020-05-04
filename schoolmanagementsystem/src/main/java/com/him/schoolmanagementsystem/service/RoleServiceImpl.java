package com.him.schoolmanagementsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.him.schoolmanagementsystem.config.MainError;
import com.him.schoolmanagementsystem.config.MainException;
import com.him.schoolmanagementsystem.config.NotFoundException;
import com.him.schoolmanagementsystem.config.ServiceException;
import com.him.schoolmanagementsystem.config.ValidationException;
import com.him.schoolmanagementsystem.model.BaseEntity;
import com.him.schoolmanagementsystem.model.Role;
import com.him.schoolmanagementsystem.repository.RoleRepository;
@Service

public class RoleServiceImpl implements RoleService{
	
	@Autowired RoleRepository roleRepository; 

	@Override
	public Role addRole(Role roleToAdd) throws NotFoundException,ValidationException,ServiceException {
		
		Role role=null;
		try {
			if(roleToAdd.getRoleName() ==null)
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
			}
			BaseEntity baseEntity=new BaseEntity(roleToAdd.getCreatedBy(),roleToAdd.getCreatedDate(),null,null,roleToAdd.getStatus());
			roleToAdd.setBaseEntity(baseEntity);
			role=roleRepository.addRole(roleToAdd);
		}
	    catch(ValidationException noexp)
		{
			throw noexp;
		}
		
		catch (MainException mexp) {
			throw new ServiceException(mexp);
		}
		catch (Exception e) {
			throw new ServiceException(new MainException(e));
		}
		return role;
	}

	@Override
	public Role getRole(int roleId) throws NotFoundException, ValidationException, ServiceException {
		
		Role result=null;
		try {
			if(roleId<=0)
			{
				MainError error=new MainError(MainError.ERROR_BAD_REQUEST,"Role is Required.");
				ValidationException c=new ValidationException(error.getMessage());
				c.getMainError().add(error);
				throw c;
			}
			result=roleRepository.findRoleById(roleId);
			if(result ==null)
			{
				throw new NotFoundException(new MainException("Role is not found."));
			}
			
		}
		 catch(ValidationException |NotFoundException noexp)
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

	@Override
	public List<Role> getAllRole() throws NotFoundException, ValidationException, ServiceException {
		List<Role> result=null;
		try {
			
			result=roleRepository.getAllRole();
			
		}
		
		catch (MainException mexp) {
			throw new ServiceException(mexp);
		}
		catch (Exception e) {
			throw new ServiceException(new MainException(e));
		}
		return result;	
	}

	@Override
	public Role updateRole(int id, Role roleToUpdated) throws NotFoundException, ValidationException, ServiceException {
		Role role=null;
		try {
			if(roleToUpdated.getRoleName() ==null)
			{
				MainError error=new MainError(MainError.ERROR_BAD_REQUEST,"Role is Required.");
				ValidationException c=new ValidationException(error.getMessage());
				c.getMainError().add(error);
				throw c;
			}
			Role exitRole= roleRepository.findRoleById(id);
			
			if(exitRole!=null)
			{
				Role exitRoleByName= roleRepository.findRoleByRoleName(roleToUpdated.getRoleName());
				if(exitRoleByName !=null && (exitRoleByName.getRoleName().equalsIgnoreCase(roleToUpdated.getRoleName()))){
				MainError error=new MainError(MainError.ERROR_BAD_REQUEST,"Role [" + roleToUpdated.getRoleName() +"] already exits.");
				ValidationException c=new ValidationException(error.getMessage());
				c.getMainError().add(error);
				throw c;
				}
			}
			updateRoleEntity(exitRole,roleToUpdated);
	
			role=roleRepository.addRole(exitRole);
		}
	    catch(ValidationException noexp)
		{
			throw noexp;
		}
		
		catch (MainException mexp) {
			throw new ServiceException(mexp);
		}
		catch (Exception e) {
			throw new ServiceException(new MainException(e));
		}
		return role;

	}
 private static void updateRoleEntity(Role exitRole,Role roleToUpdated)
 {
	 exitRole.setRoleName(roleToUpdated.getRoleName());
	exitRole.setStatus(roleToUpdated.getStatus());
		BaseEntity baseEntity=new BaseEntity(exitRole.getCreatedBy(),exitRole.getCreatedDate(),roleToUpdated.getLastUpdatedBy(),roleToUpdated.getLastUpdatedDate(),exitRole.getStatus());
		exitRole.setBaseEntity(baseEntity);
 }

@Override
public void deleteRole(int id) throws NotFoundException, ValidationException, ServiceException {
	Role exitRole=null;
	try {
	 exitRole=getRole(id);
	roleRepository.deleteRole(exitRole);
	}
	 catch(ValidationException |NotFoundException noexp)
	{
		throw noexp;
	}
	
	catch (MainException mexp) {
		throw new ServiceException(mexp);
	}
	catch (Exception e) {
		throw new ServiceException(new MainException(e));
	}
	
}
}

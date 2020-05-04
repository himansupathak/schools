package com.him.schoolmanagementsystem.repository;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Optional;
import com.him.schoolmanagementsystem.config.PersistenceException;
import com.him.schoolmanagementsystem.model.Role;

@Component
public class RoleRepository {
	
	@Autowired private RoleJpaRepo roleRepo;
	@PersistenceContext private EntityManager em;
	
	@Transactional(rollbackFor= PersistenceException.class)
	public Role addRole(Role roleToAdd) throws PersistenceException {
		Role result=null;
		try {
			result=roleRepo.save(roleToAdd);
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
	public Role findRoleByRoleName(String roleName) throws PersistenceException{
		Role role=null;
		try {
			role=roleRepo.findRoleByRoleName(roleName);
		}
		catch (Throwable e) {
			// TODO: handle exception
			throw new PersistenceException(e.getMessage());
		}
		return role;
	}
@Transactional(readOnly=true,rollbackFor=PersistenceException.class
)
public Role findRoleById(int roleId) throws PersistenceException{
	
	Role role=null;
	try {
		role=roleRepo.findRoleById(roleId);
		//Optional<Role> opt=roleRepo.findOne()
	}
	catch (Throwable e) {
		// TODO: handle exception
		throw new PersistenceException(e.getMessage());
	}
	return role;
}
@Transactional(readOnly=true,rollbackFor=PersistenceException.class
)
public List<Role> getAllRole() throws PersistenceException {
	List<Role> role=null;
	try {
		role=roleRepo.findAll();
		//Optional<Role> opt=roleRepo.findOne()
	}
	catch (Throwable e) {
		// TODO: handle exception
		throw new PersistenceException(e.getMessage());
	}
	return role;
}
@Transactional(rollbackFor= PersistenceException.class)
public void deleteRole(Role exitRole) throws PersistenceException{
	try {
		roleRepo.delete(exitRole);
		//Optional<Role> opt=roleRepo.findOne()
	}
	catch (Throwable e) {
		// TODO: handle exception
		throw new PersistenceException(e.getMessage());
	}
	
	
}

}

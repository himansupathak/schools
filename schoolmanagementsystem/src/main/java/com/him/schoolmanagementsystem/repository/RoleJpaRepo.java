package com.him.schoolmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.google.common.base.Optional;
import com.him.schoolmanagementsystem.model.Role;

@Repository
public interface RoleJpaRepo extends JpaRepository<Role, Integer> {

	Role findRoleByRoleName(String roleName);
	
	Role findRoleById(int roleId);

}

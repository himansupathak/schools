package com.him.schoolmanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.him.schoolmanagementsystem.config.DateTimeUtil;
import com.him.schoolmanagementsystem.config.MainException;
import com.him.schoolmanagementsystem.config.RequestInfo;
import com.him.schoolmanagementsystem.config.ResponseInfo;
import com.him.schoolmanagementsystem.model.Role;
import com.him.schoolmanagementsystem.service.RoleService;

@RestController
@RequestMapping(value = "/api/role")

public class RoleController {

	@Autowired
	private RoleService roleService;

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseInfo<String>> addRole(@RequestBody RequestInfo<Role> requestInfo)
			throws MainException {

		String result = null;
		try {
			Role roleEntity = requestInfo.getRequestInfo();
			if (roleEntity != null) {
				roleEntity.setCreatedBy("User");
				roleEntity.setCreatedDate(DateTimeUtil.currentTimeStamp());
			}
			roleService.addRole(roleEntity);
			result = "Role is added";
		} catch (MainException e) {
			// TODO: handle exception
			throw e;
		} catch (Exception e) {
			throw new MainException(e);
		}
		return new ResponseEntity<ResponseInfo<String>>(new ResponseInfo<String>(result), HttpStatus.CREATED);

	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseInfo<Role>> getRole(@PathVariable("id") int id)
			throws MainException {

		Role role = null;
		try {
			
			role=roleService.getRole(id);
			
		} catch (MainException e) {
			throw e;
		} catch (Exception e) {
			throw new MainException(e);
		}
		return new ResponseEntity<ResponseInfo<Role>>(new ResponseInfo<Role>(role), HttpStatus.CREATED);

	}
	
	@RequestMapping(value = "/search",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseInfo<List<Role>>> getAllRole()
			throws MainException {

		List<Role> role = null;
		try {
			
			role=roleService.getAllRole();
			
		} catch (MainException e) {
			throw e;
		} catch (Exception e) {
			throw new MainException(e);
		}
		return new ResponseEntity<ResponseInfo<List<Role>>>(new ResponseInfo<List<Role>>(role), HttpStatus.CREATED);

	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseInfo<String>> updateRole(@PathVariable("id") int id,@RequestBody RequestInfo<Role> requestInfo)
			throws MainException {

		String result = null;
		try {
			Role roleEntity = requestInfo.getRequestInfo();
			if (roleEntity != null) {
				roleEntity.setLastUpdatedBy("User");
				roleEntity.setLastUpdatedDate(DateTimeUtil.currentTimeStamp());
			}
			roleService.updateRole(id,roleEntity);
			result = "Role is updated";
		} catch (MainException e) {
			// TODO: handle exception
			throw e;
		} catch (Exception e) {
			throw new MainException(e);
		}
		return new ResponseEntity<ResponseInfo<String>>(new ResponseInfo<String>(result), HttpStatus.CREATED);

	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseInfo<String>> deleteRole(@PathVariable("id") int id)
			throws MainException {

		String result = null;
		try {
			
			roleService.deleteRole(id);
			result = "Role is deleted";
		} catch (MainException e) {
			// TODO: handle exception
			throw e;
		} catch (Exception e) {
			throw new MainException(e);
		}
		return new ResponseEntity<ResponseInfo<String>>(new ResponseInfo<String>(result), HttpStatus.CREATED);

	}
	

}

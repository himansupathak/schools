package com.him.schoolmanagementsystem.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.him.schoolmanagementsystem.config.PersistenceException;
import com.him.schoolmanagementsystem.model.CoursePost;
import com.him.schoolmanagementsystem.model.Role;


@Component
public class PostRepository {

	@Autowired private PostRepo postRepo;
	@PersistenceContext private EntityManager em;
	
	@Transactional(rollbackFor= PersistenceException.class)
	public CoursePost addPost(CoursePost employeeToAdd) throws PersistenceException {
		CoursePost result=null;
		try {
			result=postRepo.save(employeeToAdd);
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
	public List<CoursePost> getAllPost() throws PersistenceException{
		List<CoursePost> role=null;
		try {
			role=postRepo.findAll();
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
	public CoursePost findRoleById(int postid) throws PersistenceException {
		CoursePost role=null;
		try {
			role=postRepo.findRoleById(postid);
			//Optional<Role> opt=roleRepo.findOne()
		}
		catch (Throwable e) {
			// TODO: handle exception
			throw new PersistenceException(e.getMessage());
		}
		return role;
	}
	@Transactional(rollbackFor= PersistenceException.class)
	public void deletePost(CoursePost exitRole) throws PersistenceException {
		try {
			postRepo.delete(exitRole);
			//Optional<Role> opt=roleRepo.findOne()
		}
		catch (Throwable e) {
			// TODO: handle exception
			throw new PersistenceException(e.getMessage());
		}
		
		
	}
	
}


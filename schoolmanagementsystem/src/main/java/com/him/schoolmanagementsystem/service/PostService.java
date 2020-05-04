package com.him.schoolmanagementsystem.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.him.schoolmanagementsystem.config.NotFoundException;
import com.him.schoolmanagementsystem.config.ServiceException;
import com.him.schoolmanagementsystem.config.ValidationException;
import com.him.schoolmanagementsystem.model.CoursePost;
import com.him.schoolmanagementsystem.model.Employee;

public interface PostService {
	public CoursePost addPost(MultipartFile file, CoursePost employeeToAdd) throws NotFoundException, ValidationException, ServiceException;

	public List<CoursePost> getAllPost() throws NotFoundException, ValidationException, ServiceException;

	public CoursePost getPost(int postid) throws NotFoundException, ValidationException, ServiceException;

	public CoursePost updatePost(int id, CoursePost roleEntity, MultipartFile file) throws NotFoundException, ValidationException, ServiceException;

	public void deletePost(int id) throws NotFoundException, ValidationException, ServiceException;

	

}

package com.him.schoolmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.him.schoolmanagementsystem.model.CoursePost;

public interface PostRepo extends JpaRepository<CoursePost, Integer>{

	CoursePost findRoleById(int postid);

}

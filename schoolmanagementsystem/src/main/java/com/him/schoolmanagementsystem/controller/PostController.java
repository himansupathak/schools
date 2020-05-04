package com.him.schoolmanagementsystem.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Optional;
import com.him.schoolmanagementsystem.config.MainException;
import com.him.schoolmanagementsystem.config.RequestInfo;
import com.him.schoolmanagementsystem.config.ResponseInfo;
import com.him.schoolmanagementsystem.model.CoursePost;
import com.him.schoolmanagementsystem.service.PostService;

@RestController
@RequestMapping(value = "/api/post")
@CrossOrigin(origins = "http://localhost:4200")
public class PostController {

	@Autowired
	PostService postService;

	@RequestMapping(method = RequestMethod.POST, consumes = { "multipart/form-data" })
	public ResponseEntity<ResponseInfo<String>> addPost(@RequestPart("requestInfo") String requestInfo,
			@RequestPart("file") MultipartFile file) throws MainException, IOException {

		ObjectMapper mapper = new ObjectMapper();
		RequestInfo<CoursePost> coursePost = null;
		coursePost = mapper.readValue(requestInfo, new TypeReference<RequestInfo<CoursePost>>() {
		});
		CoursePost roleEntity = coursePost.getRequestInfo();
		String result = null;
		try {
			postService.addPost(file, roleEntity);
			result = "Post is added";
		} catch (MainException e) {
			throw e;
		} catch (Exception e) {
			throw new MainException(e);
		}
		return new ResponseEntity<ResponseInfo<String>>(new ResponseInfo<String>(result), HttpStatus.CREATED);

	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseInfo<List<CoursePost>>> getAllPost() throws MainException {

		List<CoursePost> post = null;
		try {

			post = postService.getAllPost();

		} catch (MainException e) {
			throw e;
		} catch (Exception e) {
			throw new MainException(e);
		}
		return new ResponseEntity<ResponseInfo<List<CoursePost>>>(new ResponseInfo<List<CoursePost>>(post),
				HttpStatus.CREATED);

	}

	@RequestMapping(value = "/{postid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseInfo<CoursePost>> getPost(@PathVariable("postid") int postid) throws MainException {

	CoursePost role = null;
	  byte[] imageBytes = null;
		try {
			role = postService.getPost(postid);
		  //decompressBytes(role.getImage());
			if (role!=null) {
				imageBytes = role.getImage();
			}
		} catch (MainException e) {
			throw e;
		} catch (Exception e) {
			throw new MainException(e);
		}
		return new ResponseEntity<ResponseInfo<CoursePost>>(new ResponseInfo<CoursePost>(role), HttpStatus.CREATED);
		//return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);

	}
	// uncompress the image bytes before returning it to the angular application

	public static byte[] decompressBytes(byte[] data) {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		try {
			while (!inflater.finished()) {
				int count = inflater.inflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			outputStream.close();
		} catch (IOException ioe) {
		} catch (DataFormatException e) {
		}
		return outputStream.toByteArray();
	}

	@RequestMapping(value = "/{postid}", method = RequestMethod.PUT,consumes = { "multipart/form-data" })
	public ResponseEntity<ResponseInfo<String>> updatePost(@PathVariable("postid") int postid,
			@RequestPart("requestInfo") String requestInfo,
			@RequestPart("file") MultipartFile file) throws MainException {

		String result = null;
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			RequestInfo<CoursePost> coursePost = null;
			coursePost = mapper.readValue(requestInfo, new TypeReference<RequestInfo<CoursePost>>() {
			});
			CoursePost roleEntity = coursePost.getRequestInfo();
			postService.updatePost(postid, roleEntity,file);
			result = "Post is updated";
		} catch (MainException e) {
			// TODO: handle exception
			throw e;
		} catch (Exception e) {
			throw new MainException(e);
		}
		return new ResponseEntity<ResponseInfo<String>>(new ResponseInfo<String>(result), HttpStatus.CREATED);

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseInfo<String>> deletePost(@PathVariable("id") int id) throws MainException {

		String result = null;
		try {

			postService.deletePost(id);
			result = "Post is deleted";
		} catch (MainException e) {
			// TODO: handle exception
			throw e;
		} catch (Exception e) {
			throw new MainException(e);
		}
		return new ResponseEntity<ResponseInfo<String>>(new ResponseInfo<String>(result), HttpStatus.CREATED);

	}

}

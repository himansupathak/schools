package com.him.schoolmanagementsystem.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.io.Files;
import com.him.schoolmanagementsystem.config.MainError;
import com.him.schoolmanagementsystem.config.MainException;
import com.him.schoolmanagementsystem.config.NotFoundException;
import com.him.schoolmanagementsystem.config.ServiceException;
import com.him.schoolmanagementsystem.config.ValidationException;
import com.him.schoolmanagementsystem.model.BaseEntity;
import com.him.schoolmanagementsystem.model.CoursePost;
import com.him.schoolmanagementsystem.model.Role;
import com.him.schoolmanagementsystem.repository.PostRepository;
import com.querydsl.core.types.Path;
import com.sun.xml.internal.ws.message.stream.OutboundStreamHeader;

import springfox.documentation.spring.web.paths.Paths;

@Service
public class PostServiceImpl implements PostService {
	@Autowired
	PostRepository postRepository;
	@Value("${relativeWebPath}")
	private String relativePath;

	@Override
	public CoursePost addPost(MultipartFile file, CoursePost employeeToAdd)
			throws NotFoundException, ValidationException, ServiceException {
		CoursePost employee = null;
		try (InputStream in = file.getInputStream();
				OutputStream out = new FileOutputStream(relativePath + file.getOriginalFilename())) {
			employeeToAdd.setImage(file.getBytes());
			employeeToAdd.setImageName(file.getOriginalFilename());
			employee = postRepository.addPost(employeeToAdd);
			FileCopyUtils.copy(in, out);

		} 
		catch (MainException mexp) {
			throw new ServiceException(mexp);
		}catch (IOException e) {
			// TODO: handle exception
		}catch (Exception e) {
			throw new ServiceException(new MainException(e));
		}
		return employee;
	}

	private static String writeContent(String data, String fileName) {
		String path = "C:/Users/Him/upload";
		File file = null;
		OutputStream os = null;
		try {
			File uploadDir = new File(path);
			if (!uploadDir.exists()) {
				uploadDir.mkdirs();
			}
			file = new File(uploadDir + File.separator + fileName);
			os = new FileOutputStream(file);
			os.write(data.getBytes(), 0, data.length());
			if (file.exists()) {
				path = file.getAbsolutePath();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return path;
	}

	@Override
	public List<CoursePost> getAllPost() throws NotFoundException, ValidationException, ServiceException {
		List<CoursePost> result = null;
		try {

			result = postRepository.getAllPost();

		}

		catch (MainException mexp) {
			throw new ServiceException(mexp);
		} catch (Exception e) {
			throw new ServiceException(new MainException(e));
		}
		return result;
	}

	@Override
	public CoursePost getPost(int postid) throws NotFoundException, ValidationException, ServiceException {
		CoursePost result = null;
		try {
			if (postid <= 0) {
				MainError error = new MainError(MainError.ERROR_BAD_REQUEST, "PostId is Required.");
				ValidationException c = new ValidationException(error.getMessage());
				c.getMainError().add(error);
				throw c;
			}
			result = postRepository.findRoleById(postid);
			if (result == null) {
				throw new NotFoundException(new MainException("Post is not found."));
			}

		} catch (ValidationException | NotFoundException noexp) {
			throw noexp;
		}

		catch (MainException mexp) {
			throw new ServiceException(mexp);
		} catch (Exception e) {
			throw new ServiceException(new MainException(e));
		}
		return result;
	}

	@Override
	public CoursePost updatePost(int id, CoursePost roleEntity, MultipartFile file)
			throws NotFoundException, ValidationException, ServiceException {
		CoursePost role = null;
			try (InputStream in = file.getInputStream();
					OutputStream out = new FileOutputStream(relativePath + file.getOriginalFilename())) {
				
				if (roleEntity.getTitle() == null) {
					MainError error = new MainError(MainError.ERROR_BAD_REQUEST, "Title is Required.");
					ValidationException c = new ValidationException(error.getMessage());
					c.getMainError().add(error);
					throw c;
				}
					CoursePost exitRole = this.getPost(id);
					
					roleEntity.setImage(file.getBytes());
					roleEntity.setImageName(file.getOriginalFilename());
					updateRoleEntity(exitRole, roleEntity);	
					role = postRepository.addPost(exitRole);
				FileCopyUtils.copy(in, out);	
			} 
		catch (ValidationException noexp) {
			throw noexp;
		}

		catch (MainException mexp) {
			throw new ServiceException(mexp);
		} catch (Exception e) {
			throw new ServiceException(new MainException(e));
		}
		return role;

	}

	private static void updateRoleEntity(CoursePost exitRole, CoursePost roleToUpdated) {
		exitRole.setTitle(roleToUpdated.getTitle());
		exitRole.setShortDesc(roleToUpdated.getShortDesc());
		exitRole.setFullDesc(roleToUpdated.getFullDesc());
		exitRole.setImage(roleToUpdated.getImage());
		exitRole.setImageName(roleToUpdated.getImageName());
	}

	@Override
	public void deletePost(int id) throws NotFoundException, ValidationException, ServiceException {
		CoursePost exitRole = null;
		try {
			exitRole = getPost(id);
			postRepository.deletePost(exitRole);
		} catch (ValidationException | NotFoundException noexp) {
			throw noexp;
		}

		catch (MainException mexp) {
			throw new ServiceException(mexp);
		} catch (Exception e) {
			throw new ServiceException(new MainException(e));
		}

	}

}

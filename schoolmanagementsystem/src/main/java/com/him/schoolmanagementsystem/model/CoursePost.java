package com.him.schoolmanagementsystem.model;

import java.io.File;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "coursepost")
public class CoursePost {
	private static final long serialVersionUID = 1L;
	@Id
	@JsonProperty("Id")
	@SequenceGenerator(name = "coursepost_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Integer id;

	@JsonProperty("title")
	@Column(name = "title")
	private String title;

	@JsonProperty("shortDesc")
	@Column(name = "short_desc")
	private String shortDesc;

	@JsonProperty("fullDesc")
	@Column(name = "full_desc")
	private String fullDesc;

	@JsonProperty("author")
	@Column(name = "author")
	private String author;

	@Lob
	@JsonProperty("image")
	@Column(name = "image", length = 1000)
	private byte[] image;
	
	@JsonIgnore
	@Column(name = "imagename")
	private String imageName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getShortDesc() {
		return shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	public String getFullDesc() {
		return fullDesc;
	}

	public void setFullDesc(String fullDesc) {
		this.fullDesc = fullDesc;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	
}

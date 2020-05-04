package com.him.schoolmanagementsystem.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.him.schoolmanagementsystem.config.DateTimeUtil;

@MappedSuperclass
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@JsonProperty("CreatedBy")
	@Column(name = "created_by")
	private String createdBy;

	@JsonProperty("CreatedDate")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeUtil.DATE_TIME_DEFAULT, timezone = DateTimeUtil.TIME_ZONE_CENTRAL)
	@Column(name = "created_date")
	private Timestamp createdDate;

	@JsonProperty("LastUpdatedBy")
	@Column(name = "updated_by")
	private String lastUpdatedBy;

	@JsonProperty("LastUpdatedDate")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeUtil.DATE_TIME_DEFAULT, timezone = DateTimeUtil.TIME_ZONE_CENTRAL)
	@Column(name = "updated_date")
	private Timestamp lastUpdatedDate;

	@JsonProperty("Status")
	@Column(name = "status")
	private Integer status;

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Timestamp getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Timestamp lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@JsonIgnore
	@Override
	public String toString() {
		return "BaseEntity [createdBy=" + createdBy + ", createdDate=" + createdDate + ", lastUpdatedBy="
				+ lastUpdatedBy + ", lastUpdatedDate=" + lastUpdatedDate + ", status=" + status + "]";
	}

	public BaseEntity(String createdBy, Timestamp createdDate, String lastUpdatedBy, Timestamp lastUpdatedDate,
			Integer status) {
		super();
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.lastUpdatedBy = lastUpdatedBy;
		this.lastUpdatedDate = lastUpdatedDate;
		this.status = status;
	}

	public BaseEntity() {
		super();
	}

	@JsonIgnore
	@Transient
	public void setBaseEntity(BaseEntity baseEntity) {
		this.createdBy = baseEntity.getCreatedBy();
		this.createdDate = baseEntity.getCreatedDate();
		this.lastUpdatedBy = baseEntity.getLastUpdatedBy();
		this.lastUpdatedDate = baseEntity.getLastUpdatedDate();
	}

}

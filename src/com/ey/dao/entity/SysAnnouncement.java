package com.ey.dao.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * SysAnnouncement entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_announcement")
public class SysAnnouncement implements java.io.Serializable {

	// Fields

	private Long id;
	private Date createTime;
	private Date retentionTime;
	private String title;
	private String content;
	private Integer announcementScope;
	private Integer announcementGroup;
	private String areaId;
	private Integer status;
	private String retentionFlag;

	// Constructors

	/** default constructor */
	public SysAnnouncement() {
	}

	/** minimal constructor */
	public SysAnnouncement(Long id, Date createTime, Integer announcementScope,
			Integer announcementGroup, String areaId, Integer status) {
		this.id = id;
		this.createTime = createTime;
		this.announcementScope = announcementScope;
		this.announcementGroup = announcementGroup;
		this.areaId = areaId;
		this.status = status;
	}

	/** full constructor */
	public SysAnnouncement(Long id, Date createTime, String title,
			String content, Integer announcementScope,
			Integer announcementGroup, String areaId, Integer status) {
		this.id = id;
		this.createTime = createTime;
		this.title = title;
		this.content = content;
		this.announcementScope = announcementScope;
		this.announcementGroup = announcementGroup;
		this.areaId = areaId;
		this.status = status;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", nullable = false, length = 10)
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "title")
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "content")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "announcement_scope", nullable = false)
	public Integer getAnnouncementScope() {
		return this.announcementScope;
	}

	public void setAnnouncementScope(Integer announcementScope) {
		this.announcementScope = announcementScope;
	}

	@Column(name = "announcement_group", nullable = false)
	public Integer getAnnouncementGroup() {
		return this.announcementGroup;
	}

	public void setAnnouncementGroup(Integer announcementGroup) {
		this.announcementGroup = announcementGroup;
	}

	@Column(name = "area_id", nullable = false, length = 20)
	public String getAreaId() {
		return this.areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	@Column(name = "status", nullable = false)
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
    
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "retention_time", nullable = false, length = 10)
	public Date getRetentionTime() {
		return retentionTime;
	}

	public void setRetentionTime(Date retentionTime) {
		this.retentionTime = retentionTime;
	}
    
	@Column(name = "retention_flag")
	public String getRetentionFlag() {
		return retentionFlag;
	}

	public void setRetentionFlag(String retentionFlag) {
		this.retentionFlag = retentionFlag;
	}
	
	
	

}
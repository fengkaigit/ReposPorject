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
 * NoticeInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "notice_info")
public class NoticeInfo implements java.io.Serializable {

	// Fields

	private Long id;
	private Long userId;
	private String serverContent;
	private Date createTime;
	private Integer sendStatus;
	private Integer noticeType;
	private Integer noticeMode;
	private Boolean massFlag;
	private String  parentAreaId;
	private String areaId;
	private String areaName;

	// Constructors

	/** default constructor */
	public NoticeInfo() {
	}

	/** full constructor */
	public NoticeInfo(Long id, Long userId, String serverContent,
			Date createTime, Integer sendStatus, Integer noticeType,
			Integer noticeMode) {
		this.id = id;
		this.userId = userId;
		this.serverContent = serverContent;
		this.createTime = createTime;
		this.sendStatus = sendStatus;
		this.noticeType = noticeType;
		this.noticeMode = noticeMode;
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

	@Column(name = "user_id")
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "server_content", nullable = false)
	public String getServerContent() {
		return this.serverContent;
	}

	public void setServerContent(String serverContent) {
		this.serverContent = serverContent;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", nullable = false, length = 10)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "send_status", nullable = false)
	public Integer getSendStatus() {
		return this.sendStatus;
	}

	public void setSendStatus(Integer sendStatus) {
		this.sendStatus = sendStatus;
	}

	@Column(name = "notice_type", nullable = false)
	public Integer getNoticeType() {
		return this.noticeType;
	}

	public void setNoticeType(Integer noticeType) {
		this.noticeType = noticeType;
	}

	@Column(name = "notice_mode", nullable = false)
	public Integer getNoticeMode() {
		return this.noticeMode;
	}

	public void setNoticeMode(Integer noticeMode) {
		this.noticeMode = noticeMode;
	}
	@Column(name = "mass_flag")
	public Boolean getMassFlag() {
		return massFlag;
	}

	public void setMassFlag(Boolean massFlag) {
		this.massFlag = massFlag;
	}
	@Column(name = "area_pid",length=50)
	public String getParentAreaId() {
		return parentAreaId;
	}

	public void setParentAreaId(String parentAreaId) {
		this.parentAreaId = parentAreaId;
	}
	@Column(name = "area_id",length=50)
	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	@Column(name = "area_name",length=300)
	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	

}
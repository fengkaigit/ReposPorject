package com.ey.dao.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * NoticeInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "notice_info")
public class NoticeInfo implements java.io.Serializable {

	// Fields

	private long id;
	private long userId;
	private byte[] serverContent;
	private Date createTime;
	private Integer sendStatus;
	private Integer noticeType;
	private Integer noticeMode;

	// Constructors

	/** default constructor */
	public NoticeInfo() {
	}

	/** full constructor */
	public NoticeInfo(long id, long userId, byte[] serverContent,
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
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "user_id", nullable = false)
	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Column(name = "server_content", nullable = false)
	public byte[] getServerContent() {
		return this.serverContent;
	}

	public void setServerContent(byte[] serverContent) {
		this.serverContent = serverContent;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "create_time", nullable = false, length = 10)
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

}
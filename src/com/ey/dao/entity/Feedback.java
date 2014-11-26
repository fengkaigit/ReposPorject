package com.ey.dao.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Feedback entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "feedback")
public class Feedback implements java.io.Serializable {

	// Fields

	private Long id;
	private Long userId;
	private byte[] userIdea;
	private Date viewTime;
	private Date systemTime;
	private String systemFeedback;
	private Integer backFlag;

	// Constructors

	/** default constructor */
	public Feedback() {
	}

	/** full constructor */
	public Feedback(Long id, Long userId, byte[] userIdea,
			Date viewTime, String systemFeedback, Date systemTime, Integer backFlag) {
		this.id = id;
		this.userId = userId;
		this.userIdea = userIdea;
		this.viewTime = viewTime;
		this.systemFeedback = systemFeedback;
		this.systemTime = systemTime;
		this.backFlag = backFlag;
	}
	
	/** minimal constructor */
	public Feedback(Long id, Long userId, byte[] userIdea,
			Date viewTime, Integer backFlag) {
		this.id = id;
		this.userId = userId;
		this.userIdea = userIdea;
		this.viewTime = viewTime;
		this.backFlag = backFlag;
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

	@Column(name = "user_id", nullable = false)
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "user_idea", nullable = false)
	public byte[] getUserIdea() {
		return this.userIdea;
	}

	public void setUserIdea(byte[] userIdea) {
		this.userIdea = userIdea;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "view_time", nullable = false, length = 10)
	public Date getViewTime() {
		return this.viewTime;
	}

	public void setViewTime(Date viewTime) {
		this.viewTime = viewTime;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "system_time", nullable = false, length = 10)
	public Date getSystemTime() {
		return this.systemTime;
	}

	public void setSystemTime(Date systemTime) {
		this.systemTime = systemTime;
	}

	@Column(name = "system_feedback", nullable = false, length=250)
	public String getSystemFeedback() {
		return this.systemFeedback;
	}

	public void setSystemFeedback(String systemFeedback) {
		this.systemFeedback = systemFeedback;
	}

	@Column(name = "back_flag", nullable = false)
	public Integer getBackFlag() {
		return this.backFlag;
	}

	public void setBackFlag(Integer backFlag) {
		this.backFlag = backFlag;
	}

}
package com.ey.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

/**
 * UserOrgan entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user_organ")
public class UserOrgan implements java.io.Serializable {

	// Fields

	private long organId;
	private long userId;

	// Constructors

	// Property accessors
    @Id
	@Column(name = "organ_id", nullable = false)
	public long getOrganId() {
		return this.organId;
	}
    
    public UserOrgan() {
		
	}
	public UserOrgan(long organId, long userId) {
		super();
		this.organId = organId;
		this.userId = userId;
	}

	public void setOrganId(long organId) {
		this.organId = organId;
	}
	@Id
	@Column(name = "user_id", nullable = false)
	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

}
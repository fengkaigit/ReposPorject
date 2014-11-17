package com.ey.dao.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * DivideLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "divide_log")
public class DivideLog implements java.io.Serializable {

	// Fields

	private DivideLogId id;

	// Constructors

	/** default constructor */
	public DivideLog() {
	}

	/** full constructor */
	public DivideLog(DivideLogId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "uuid", column = @Column(name = "uuid", length = 50)),
			@AttributeOverride(name = "stageStatus", column = @Column(name = "stage_status")),
			@AttributeOverride(name = "finishedState", column = @Column(name = "finished_state")) })
	public DivideLogId getId() {
		return this.id;
	}

	public void setId(DivideLogId id) {
		this.id = id;
	}

}
package com.ey.dao.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * DivideLogId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class DivideLogId implements java.io.Serializable {

	// Fields

	private String uuid;
	private Integer stageStatus;
	private String finishedState;

	// Constructors

	/** default constructor */
	public DivideLogId() {
	}

	/** full constructor */
	public DivideLogId(String uuid, Integer stageStatus, String finishedState) {
		this.uuid = uuid;
		this.stageStatus = stageStatus;
		this.finishedState = finishedState;
	}

	// Property accessors

	@Column(name = "uuid", length = 50)
	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Column(name = "stage_status")
	public Integer getStageStatus() {
		return this.stageStatus;
	}

	public void setStageStatus(Integer stageStatus) {
		this.stageStatus = stageStatus;
	}

	@Column(name = "finished_state")
	public String getFinishedState() {
		return this.finishedState;
	}

	public void setFinishedState(String finishedState) {
		this.finishedState = finishedState;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof DivideLogId))
			return false;
		DivideLogId castOther = (DivideLogId) other;

		return ((this.getUuid() == castOther.getUuid()) || (this.getUuid() != null
				&& castOther.getUuid() != null && this.getUuid().equals(
				castOther.getUuid())))
				&& ((this.getStageStatus() == castOther.getStageStatus()) || (this
						.getStageStatus() != null
						&& castOther.getStageStatus() != null && this
						.getStageStatus().equals(castOther.getStageStatus())))
				&& ((this.getFinishedState() == castOther.getFinishedState()) || (this
						.getFinishedState() != null
						&& castOther.getFinishedState() != null && this
						.getFinishedState()
						.equals(castOther.getFinishedState())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUuid() == null ? 0 : this.getUuid().hashCode());
		result = 37
				* result
				+ (getStageStatus() == null ? 0 : this.getStageStatus()
						.hashCode());
		result = 37
				* result
				+ (getFinishedState() == null ? 0 : this.getFinishedState()
						.hashCode());
		return result;
	}

}
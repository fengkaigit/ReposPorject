package com.ey.dao.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * BillSubjectDataId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class BillSubjectDataId implements java.io.Serializable {

	// Fields

	private long modelId;
	private long id;

	// Constructors

	/** default constructor */
	public BillSubjectDataId() {
	}

	/** full constructor */
	public BillSubjectDataId(long modelId, long id) {
		this.modelId = modelId;
		this.id = id;
	}

	// Property accessors

	@Column(name = "model_id", nullable = false)
	public long getModelId() {
		return this.modelId;
	}

	public void setModelId(long modelId) {
		this.modelId = modelId;
	}

	@Column(name = "ID", nullable = false)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof BillSubjectDataId))
			return false;
		BillSubjectDataId castOther = (BillSubjectDataId) other;

		return (this.getModelId() == castOther.getModelId())
				&& (this.getId() == castOther.getId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getModelId();
		result = 37 * result + (int) this.getId();
		return result;
	}

}
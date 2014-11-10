package com.ey.dao.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * BillSubjectDataId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class BillSubjectDataId implements java.io.Serializable {

	// Fields

	private Long modelId;
	private Long id;

	// Constructors

	/** default constructor */
	public BillSubjectDataId() {
	}

	/** full constructor */
	public BillSubjectDataId(Long modelId, Long id) {
		this.modelId = modelId;
		this.id = id;
	}

	// Property accessors

	@Column(name = "model_id", nullable = false)
	public Long getModelId() {
		return this.modelId;
	}

	public void setModelId(Long modelId) {
		this.modelId = modelId;
	}

	@Column(name = "ID", nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
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
}
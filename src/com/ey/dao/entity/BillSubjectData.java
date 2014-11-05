package com.ey.dao.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * BillSubjectData entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "bill_subject_data")
public class BillSubjectData implements java.io.Serializable {

	// Fields

	private BillSubjectDataId id;
	private String fieldName;
	private String fieldData;

	// Constructors

	/** default constructor */
	public BillSubjectData() {
	}

	/** minimal constructor */
	public BillSubjectData(BillSubjectDataId id, String fieldName) {
		this.id = id;
		this.fieldName = fieldName;
	}

	/** full constructor */
	public BillSubjectData(BillSubjectDataId id, String fieldName,
			String fieldData) {
		this.id = id;
		this.fieldName = fieldName;
		this.fieldData = fieldData;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "modelId", column = @Column(name = "model_id", nullable = false)),
			@AttributeOverride(name = "id", column = @Column(name = "ID", nullable = false)) })
	public BillSubjectDataId getId() {
		return this.id;
	}

	public void setId(BillSubjectDataId id) {
		this.id = id;
	}

	@Column(name = "field_name", nullable = false)
	public String getFieldName() {
		return this.fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	@Column(name = "field_data")
	public String getFieldData() {
		return this.fieldData;
	}

	public void setFieldData(String fieldData) {
		this.fieldData = fieldData;
	}

}
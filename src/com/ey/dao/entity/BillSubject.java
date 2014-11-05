package com.ey.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BillSubject entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "bill_subject")
public class BillSubject implements java.io.Serializable {

	// Fields

	private long id;
	private String sourceId;
	private String fieldType;
	private String fieldName;
	private Integer fieldSize;
	private Integer fieldPrecision;
	private String fieldExplain;
	private String fieldConstraint;
	private String defaultValue;

	// Constructors

	/** default constructor */
	public BillSubject() {
	}

	/** minimal constructor */
	public BillSubject(long id, String sourceId, String fieldType,
			String fieldName) {
		this.id = id;
		this.sourceId = sourceId;
		this.fieldType = fieldType;
		this.fieldName = fieldName;
	}

	/** full constructor */
	public BillSubject(long id, String sourceId, String fieldType,
			String fieldName, Integer fieldSize, Integer fieldPrecision,
			String fieldExplain, String fieldConstraint, String defaultValue) {
		this.id = id;
		this.sourceId = sourceId;
		this.fieldType = fieldType;
		this.fieldName = fieldName;
		this.fieldSize = fieldSize;
		this.fieldPrecision = fieldPrecision;
		this.fieldExplain = fieldExplain;
		this.fieldConstraint = fieldConstraint;
		this.defaultValue = defaultValue;
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

	@Column(name = "source_Id", nullable = false, length = 50)
	public String getSourceId() {
		return this.sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	@Column(name = "field_type", nullable = false, length = 10)
	public String getFieldType() {
		return this.fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	@Column(name = "field_name", nullable = false)
	public String getFieldName() {
		return this.fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	@Column(name = "field_size")
	public Integer getFieldSize() {
		return this.fieldSize;
	}

	public void setFieldSize(Integer fieldSize) {
		this.fieldSize = fieldSize;
	}

	@Column(name = "field_precision")
	public Integer getFieldPrecision() {
		return this.fieldPrecision;
	}

	public void setFieldPrecision(Integer fieldPrecision) {
		this.fieldPrecision = fieldPrecision;
	}

	@Column(name = "field_explain", length = 180)
	public String getFieldExplain() {
		return this.fieldExplain;
	}

	public void setFieldExplain(String fieldExplain) {
		this.fieldExplain = fieldExplain;
	}

	@Column(name = "field_constraint", length = 180)
	public String getFieldConstraint() {
		return this.fieldConstraint;
	}

	public void setFieldConstraint(String fieldConstraint) {
		this.fieldConstraint = fieldConstraint;
	}

	@Column(name = "default_value", length = 50)
	public String getDefaultValue() {
		return this.defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

}
package com.ey.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BillSource entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "bill_source")
public class BillSource implements java.io.Serializable {

	// Fields

	private String id;
	private String IExplain;
	private String physicalTable;
	private String billAddress;
	private String modelAddress;

	// Constructors

	/** default constructor */
	public BillSource() {
	}

	/** minimal constructor */
	public BillSource(String id, String physicalTable, String billAddress) {
		this.id = id;
		this.physicalTable = physicalTable;
		this.billAddress = billAddress;
	}

	/** full constructor */
	public BillSource(String id, String IExplain, String physicalTable,
			String billAddress, String modelAddress) {
		this.id = id;
		this.IExplain = IExplain;
		this.physicalTable = physicalTable;
		this.billAddress = billAddress;
		this.modelAddress = modelAddress;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 50)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "i_explain", length = 240)
	public String getIExplain() {
		return this.IExplain;
	}

	public void setIExplain(String IExplain) {
		this.IExplain = IExplain;
	}

	@Column(name = "physical_table", nullable = false, length = 60)
	public String getPhysicalTable() {
		return this.physicalTable;
	}

	public void setPhysicalTable(String physicalTable) {
		this.physicalTable = physicalTable;
	}

	@Column(name = "bill_address", nullable = false, length = 120)
	public String getBillAddress() {
		return this.billAddress;
	}

	public void setBillAddress(String billAddress) {
		this.billAddress = billAddress;
	}

	@Column(name = "model_address", length = 120)
	public String getModelAddress() {
		return this.modelAddress;
	}

	public void setModelAddress(String modelAddress) {
		this.modelAddress = modelAddress;
	}

}
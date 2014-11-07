package com.ey.dao.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * ServiceProfitRelation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "service_profit_relation")
public class ServiceProfitRelation implements java.io.Serializable {

	// Fields

	private ServiceProfitRelationId id;

	// Constructors

	/** default constructor */
	public ServiceProfitRelation() {
	}

	/** full constructor */
	public ServiceProfitRelation(ServiceProfitRelationId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "profitId", column = @Column(name = "profit_id", nullable = false)),
			@AttributeOverride(name = "incomeId", column = @Column(name = "income_id", nullable = false)) })
	public ServiceProfitRelationId getId() {
		return this.id;
	}

	public void setId(ServiceProfitRelationId id) {
		this.id = id;
	}

}
package com.ey.dao.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * AgentTransferRelation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "agent_transfer_relation")
public class HosterSetting implements java.io.Serializable {

	// Fields

	private HosterSettingId id;

	// Constructors

	/** default constructor */
	public HosterSetting() {
	}

	/** full constructor */
	public HosterSetting(HosterSettingId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "hosterId", column = @Column(name = "hoster_id", nullable = false)),
			@AttributeOverride(name = "detailId", column = @Column(name = "detail_id", nullable = false)) })
	public HosterSettingId getId() {
		return this.id;
	}

	public void setId(HosterSettingId id) {
		this.id = id;
	}

}
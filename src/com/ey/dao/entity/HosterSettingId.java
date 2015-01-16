package com.ey.dao.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AgentTransferRelationId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class HosterSettingId implements java.io.Serializable {

	// Fields

	private Long hosterId;
	private Long detailId;

	// Constructors

	/** default constructor */
	public HosterSettingId() {
	}

	/** full constructor */
	public HosterSettingId(Long hosterId, Long detailId) {
		this.hosterId = hosterId;
		this.detailId = detailId;
	}

	// Property accessors

	@Column(name = "hoster_id", nullable = false)
	public Long getHosterId() {
		return this.hosterId;
	}

	public void setHosterId(Long hosterId) {
		this.hosterId = hosterId;
	}

	@Column(name = "detail_id", nullable = false)
	public Long getDetailId() {
		return this.detailId;
	}

	public void setDetailId(Long detailId) {
		this.detailId = detailId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof HosterSettingId))
			return false;
		HosterSettingId castOther = (HosterSettingId) other;

		return (this.getHosterId() == castOther.getHosterId())
				&& (this.getDetailId() == castOther.getDetailId());
	}

}
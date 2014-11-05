package com.ey.dao.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * NoticeSettingId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class NoticeSettingId implements java.io.Serializable {

	// Fields

	private long userId;
	private Integer noticeType;

	// Constructors

	/** default constructor */
	public NoticeSettingId() {
	}

	/** full constructor */
	public NoticeSettingId(long userId, Integer noticeType) {
		this.userId = userId;
		this.noticeType = noticeType;
	}

	// Property accessors

	@Column(name = "user_id", nullable = false)
	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Column(name = "notice_type", nullable = false)
	public Integer getNoticeType() {
		return this.noticeType;
	}

	public void setNoticeType(Integer noticeType) {
		this.noticeType = noticeType;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof NoticeSettingId))
			return false;
		NoticeSettingId castOther = (NoticeSettingId) other;

		return (this.getUserId() == castOther.getUserId())
				&& ((this.getNoticeType() == castOther.getNoticeType()) || (this
						.getNoticeType() != null
						&& castOther.getNoticeType() != null && this
						.getNoticeType().equals(castOther.getNoticeType())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getUserId();
		result = 37
				* result
				+ (getNoticeType() == null ? 0 : this.getNoticeType()
						.hashCode());
		return result;
	}

}
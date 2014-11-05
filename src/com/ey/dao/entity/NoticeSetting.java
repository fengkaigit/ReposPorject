package com.ey.dao.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * NoticeSetting entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "notice_setting")
public class NoticeSetting implements java.io.Serializable {

	// Fields

	private NoticeSettingId id;
	private boolean EMailSet;
	private boolean messageSet;
	private boolean systemNotice;

	// Constructors

	/** default constructor */
	public NoticeSetting() {
	}

	/** full constructor */
	public NoticeSetting(NoticeSettingId id, boolean EMailSet,
			boolean messageSet, boolean systemNotice) {
		this.id = id;
		this.EMailSet = EMailSet;
		this.messageSet = messageSet;
		this.systemNotice = systemNotice;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "userId", column = @Column(name = "user_id", nullable = false)),
			@AttributeOverride(name = "noticeType", column = @Column(name = "notice_type", nullable = false)) })
	public NoticeSettingId getId() {
		return this.id;
	}

	public void setId(NoticeSettingId id) {
		this.id = id;
	}

	@Column(name = "e_mail_set", nullable = false)
	public boolean getEMailSet() {
		return this.EMailSet;
	}

	public void setEMailSet(boolean EMailSet) {
		this.EMailSet = EMailSet;
	}

	@Column(name = "message_set", nullable = false)
	public boolean getMessageSet() {
		return this.messageSet;
	}

	public void setMessageSet(boolean messageSet) {
		this.messageSet = messageSet;
	}

	@Column(name = "system_notice", nullable = false)
	public boolean getSystemNotice() {
		return this.systemNotice;
	}

	public void setSystemNotice(boolean systemNotice) {
		this.systemNotice = systemNotice;
	}

}
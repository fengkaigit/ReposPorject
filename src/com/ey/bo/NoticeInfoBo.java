package com.ey.bo;

import java.util.Date;

public class NoticeInfoBo {

	private Long id;
	private String serverContent;
	private Date createTime;
	private Integer noticeType;
	
	public NoticeInfoBo(){
		
	}
	public NoticeInfoBo(Long id, String serverContent, Date createTime, Integer noticeType){
		this.id = id;
		this.serverContent = serverContent;
		this.createTime = createTime;
		this.noticeType = noticeType;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getServerContent() {
		return serverContent;
	}
	public void setServerContent(String serverContent) {
		this.serverContent = serverContent;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getNoticeType() {
		return noticeType;
	}
	public void setNoticeType(Integer noticeType) {
		this.noticeType = noticeType;
	}
	
	
}

package com.ey.bo;

import java.util.Date;

public class SysAnnounceBo {

	private Long id;
	
	private Date createTime;
	
	private String title;
	
	private String content;

	public SysAnnounceBo(){
		
	}
	
	public SysAnnounceBo(Long id, Date createTime, String title, String content ){
		this.id=id;
		this.content=content;
		this.createTime = createTime;
		this.title = title;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
}

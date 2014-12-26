package com.ey.bo;

import java.util.Date;

public class SysAnnounceBo {

	private Long id;
	
	private String createTime;
	
	private String title;
	
	private String content;

	public SysAnnounceBo(){
		
	}
	
	public SysAnnounceBo(Long id, String createTime, String title, String content ){
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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
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

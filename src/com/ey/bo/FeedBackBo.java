package com.ey.bo;

public class FeedBackBo {

	private Long id;
	private Long userId;
	private String userIdea;
	private String viewTime;
	private String systemTime;
	private String systemFeedback;
	private Integer backFlag;
	private Integer backType;
	private String eMail;
	private String areaId;
	private String userName;
	private String areaName;
	
	public FeedBackBo(Long id, Long userId, String userIdea, String systemFeedback,
			Integer backFlag, Integer backType, String email, String areaId,
			String areaName, String userName){
		this.id = id;
		this.userId = userId;
		this.userIdea = userIdea;
		this.systemFeedback = systemFeedback;
		this.backFlag = backFlag;
		this.backType = backType;
		this.eMail = email;
		this.areaId = areaId;
		this.areaName = areaName;
		this.userName = userName;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserIdea() {
		return userIdea;
	}
	public void setUserIdea(String userIdea) {
		this.userIdea = userIdea;
	}
	public String getViewTime() {
		return viewTime;
	}
	public void setViewTime(String viewTime) {
		this.viewTime = viewTime;
	}
	public String getSystemTime() {
		return systemTime;
	}
	public void setSystemTime(String systemTime) {
		this.systemTime = systemTime;
	}
	public String getSystemFeedback() {
		return systemFeedback;
	}
	public void setSystemFeedback(String systemFeedback) {
		this.systemFeedback = systemFeedback;
	}
	public Integer getBackFlag() {
		return backFlag;
	}
	public void setBackFlag(Integer backFlag) {
		this.backFlag = backFlag;
	}
	public Integer getBackType() {
		return backType;
	}
	public void setBackType(Integer backType) {
		this.backType = backType;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	
}

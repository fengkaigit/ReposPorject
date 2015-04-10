package com.ey.bo;

public class HosterBo {

	public String hoster;
	
	public String payAddress;
	
	public Integer groupId;
	
	public String groupName;
	
	public String areaId;
	
	public String areaName;
	
	public Long id;
	
	public HosterBo(){
		
	}
	
	public HosterBo(String areaId, String areaName, String hoster, String payAddress, Integer groupId, String groupName, Long id){
		this.areaId = areaId;
		this.areaName = areaName;
		this.hoster = hoster;
		this.payAddress = payAddress;
		this.groupId = groupId;
		this.groupName = groupName;
		this.id = id;
	}

	
	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getHoster() {
		return hoster;
	}

	public void setHoster(String hoster) {
		this.hoster = hoster;
	}

	public String getPayAddress() {
		return payAddress;
	}

	public void setPayAddress(String payAddress) {
		this.payAddress = payAddress;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}

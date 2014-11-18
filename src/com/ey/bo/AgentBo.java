package com.ey.bo;

import com.ey.dao.entity.AgentInfo;

public class AgentBo extends AgentInfo {
   private String areaName;
   
   public AgentBo(){
	   
   }
    
   public AgentBo(Long id, String registAccount, String passwd, String EMail,
		Integer mobile, String areaId, String areaName) {
	super(id, registAccount, passwd, EMail, mobile, areaId);
	this.areaName = areaName;
}

   public String getAreaName() {
	return areaName;
   }

   public void setAreaName(String areaName) {
	this.areaName = areaName;
    }
   
}

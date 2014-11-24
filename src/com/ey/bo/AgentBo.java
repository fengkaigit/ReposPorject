package com.ey.bo;

import com.ey.dao.entity.AgentInfo;

public class AgentBo extends AgentInfo {
	private String areaName;
	private String areaPathName;
    private String areaPath;
    
    private String rule;//返点规则
    private Long agentAccount;//银行账户

	public AgentBo() {

	}

	public AgentBo(Long id, String registAccount, String passwd, String EMail,
			String mobile, double rebackDot, String registRealName,
			String areaId, String areaName,String areaPathName,
			String areaPath) {
		super(id, registAccount, passwd, EMail, mobile, areaId, rebackDot,
				registRealName);
		this.areaName = areaName;
		this.areaPathName = areaPathName.substring(9);
		this.areaPath = areaPath.substring(8);
	}
	
	public AgentBo(Long id, String registAccount, String passwd, String EMail,
			String mobile, double rebackDot, String registRealName,
			String areaId, String areaName,String areaPathName,
			String areaPath, String rule, Long agentAccount) {
		super(id, registAccount, passwd, EMail, mobile, areaId, rebackDot,
				registRealName);
		this.areaName = areaName;
		this.areaPathName = areaPathName.substring(9);
		this.areaPath = areaPath.substring(8);
		this.rule = rule;
		this.agentAccount = agentAccount;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAreaPathName() {
		return areaPathName;
	}

	public void setAreaPathName(String areaPathName) {
		this.areaPathName = areaPathName;
	}

	public String getAreaPath() {
		return areaPath;
	}

	public void setAreaPath(String areaPath) {
		this.areaPath = areaPath;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public Long getAgentAccount() {
		return agentAccount;
	}

	public void setAgentAccount(Long agentAccount) {
		this.agentAccount = agentAccount;
	}

	
    
}

package com.ey.quartz.job;

public interface AgentRebackRule {

	public Double calculateAgentRebackDot(Double serviceMoeny) throws Exception;
	
	public Double calculateAgentRebackDot() throws Exception;
	
}
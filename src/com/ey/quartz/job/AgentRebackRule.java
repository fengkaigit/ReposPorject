package com.ey.quartz.job;

public interface AgentRebackRule {

	public Double calculateAgentRebackDot(Double serviceMoney, Double dot) throws Exception;
	
	public Double calculateAgentRebackDot() throws Exception;
	
}
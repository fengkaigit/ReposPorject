package com.ey.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AgentClearStatis entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "agent_clear_statis")
public class AgentClearStatis {

	// Fields

		private Long id;
		private Long agentId;
		private String agentName;
		private Integer year;
		private Integer month;
		private Double settleMoney;
		private Double replaceMoney;
		
		public AgentClearStatis(){
			
		}
		
		public AgentClearStatis(Long agentId, String agentName, Double settleMoney, Double replaceMoney){
			this.agentId = agentId;
			this.agentName = agentName;
			this.settleMoney = settleMoney;
			this.replaceMoney = replaceMoney;
		}
		
		/** full constructor */
		public AgentClearStatis(Long id, Long agentId, String agentName, Integer year, 
				Integer month, Double settleMoney, Double replaceMoney){
			this.id = id;
			this.agentId = agentId;
			this.agentName = agentName;
			this.year = year;
			this.month = month;
			this.settleMoney = settleMoney;
			this.replaceMoney = replaceMoney;
		}

		// Property accessors
		@Id
		@Column(name = "ID", unique = true, nullable = false)
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		@Column(name = "agent_id", nullable = false)
		public Long getAgentId() {
			return agentId;
		}

		public void setAgentId(Long agentId) {
			this.agentId = agentId;
		}

		@Column(name = "agent_name", nullable = false, length = 250)
		public String getAgentName() {
			return agentName;
		}

		public void setAgentName(String agentName) {
			this.agentName = agentName;
		}

		@Column(name = "year", nullable = false)
		public Integer getYear() {
			return year;
		}

		public void setYear(Integer year) {
			this.year = year;
		}

		@Column(name = "month", nullable = false)
		public Integer getMonth() {
			return month;
		}

		public void setMonth(Integer month) {
			this.month = month;
		}

		@Column(name = "settle_money", nullable = false)
		public Double getSettleMoney() {
			return settleMoney;
		}

		public void setSettleMoney(Double settleMoney) {
			this.settleMoney = settleMoney;
		}

		@Column(name = "replace_money", nullable = false)
		public Double getReplaceMoney() {
			return replaceMoney;
		}

		public void setReplaceMoney(Double replaceMoney) {
			this.replaceMoney = replaceMoney;
		}
	
	
}

package com.ey.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AgentClearStatis entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "system_clear_statis")
public class SystemClearStatis {

	// Fields

		private Long id;
		private Integer year;
		private Integer month;
		private Double serviceMoney;
		private Double replaceMoney;
		private Double agentMoney;
		private Double profitMoney;
		
		public SystemClearStatis(){
			
		}

		public SystemClearStatis(Double serviceMoney, Double replaceMoney,
				Double agentMoney, Double profitMoney){
			this.year = year;
			this.month = month;
			this.serviceMoney = serviceMoney;
			this.replaceMoney = replaceMoney;
			this.agentMoney = agentMoney;
			this.profitMoney = profitMoney;
		}
		
		/** full constructor */
		public SystemClearStatis(Long id, Integer year, Integer month, Double serviceMoney, Double replaceMoney,
				Double agentMoney, Double profitMoney){
			this.id = id;
			this.year = year;
			this.month = month;
			this.serviceMoney = serviceMoney;
			this.replaceMoney = replaceMoney;
			this.agentMoney = agentMoney;
			this.profitMoney = profitMoney;
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

		@Column(name = "service_money", nullable = false)
		public Double getServiceMoney() {
			return serviceMoney;
		}

		public void setServiceMoney(Double serviceMoney) {
			this.serviceMoney = serviceMoney;
		}

		@Column(name = "agent_money", nullable = false)
		public Double getAgentMoney() {
			return agentMoney;
		}

		public void setAgentMoney(Double agentMoney) {
			this.agentMoney = agentMoney;
		}

		public Double getProfitMoney() {
			return profitMoney;
		}

		@Column(name = "profit_money", nullable = false)
		public void setProfitMoney(Double profitMoney) {
			this.profitMoney = profitMoney;
		}

		@Column(name = "replace_money", nullable = false)
		public Double getReplaceMoney() {
			return replaceMoney;
		}

		public void setReplaceMoney(Double replaceMoney) {
			this.replaceMoney = replaceMoney;
		}	
}

package com.ey.bo;

public class CatvInfoBo {
	
	private Long id;
	
	private String televisionName;
	
	private double televisionMoney;

	public CatvInfoBo(){
		
	}
	
	public CatvInfoBo(Long id,String televisionName, double televisionMoney){
		this.id = id;
		this.televisionName = televisionName;
		this.televisionMoney = televisionMoney;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTelevisionName() {
		return televisionName;
	}

	public void setTelevisionName(String televisionName) {
		this.televisionName = televisionName;
	}

	public double getTelevisionMoney() {
		return televisionMoney;
	}

	public void setTelevisionMoney(double televisionMoney) {
		this.televisionMoney = televisionMoney;
	}
	
}

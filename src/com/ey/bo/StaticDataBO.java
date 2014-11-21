package com.ey.bo;

import java.io.Serializable;
import java.util.List;

import com.ey.dao.entity.BaseCustomValue;

public class StaticDataBO implements Serializable {
	private String propEngName;
	private String propChName;
	private List<BaseCustomValue> values;
	public StaticDataBO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StaticDataBO(String propEngName, String propChName,
			List<BaseCustomValue> values) {
		super();
		this.propEngName = propEngName;
		this.propChName = propChName;
		this.values = values;
	}
	public String getPropEngName() {
		return propEngName;
	}
	public void setPropEngName(String propEngName) {
		this.propEngName = propEngName;
	}
	public String getPropChName() {
		return propChName;
	}
	public void setPropChName(String propChName) {
		this.propChName = propChName;
	}
	public List<BaseCustomValue> getValues() {
		return values;
	}
	public void setValues(List<BaseCustomValue> values) {
		this.values = values;
	}
	
}

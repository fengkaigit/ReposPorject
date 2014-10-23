package com.ey.util;

public class Field {
	private String name;
	private String mapping;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMapping() {
		return mapping;
	}
	public void setMapping(String mapping) {
		this.mapping = mapping;
	}
	public Field(String name, String mapping) {
		super();
		this.name = name;
		this.mapping = mapping;
	}
	
}

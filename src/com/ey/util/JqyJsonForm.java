package com.ey.util;

public class JqyJsonForm {
	private int total;
	private Object rows;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public Object getRows() {
		return rows;
	}
	public void setRows(Object rows) {
		this.rows = rows;
	}
	public JqyJsonForm(){
		
	}
public JqyJsonForm(int total,Object rows){
		this.total=total;
		this.rows=rows;
	}

}

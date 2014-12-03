package com.ey.bo;

public class ResultBo {
	
	private boolean success;
	private Object data;
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public ResultBo(boolean success, Object data) {
		super();
		this.success = success;
		this.data = data;
	}
	public ResultBo(boolean success) {
		super();
		this.success = success;
	}
	public ResultBo() {
		super();
	}
	
}

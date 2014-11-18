package com.ey.forms;

import java.io.Serializable;

public class UserForm implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1558962507592728294L;
	private String verify;
	private String loginCode;
	private String password;
	private String forwardUrl;

	public String getVerify() {
		return verify;
	}

	public void setVerify(String verify) {
		this.verify = verify;
	}

	public String getLoginCode() {
		return loginCode;
	}

	public void setLoginCode(String loginCode) {
		this.loginCode = loginCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getForwardUrl() {
		return forwardUrl;
	}

	public void setForwardUrl(String forwardUrl) {
		this.forwardUrl = forwardUrl;
	}

}

package com.ey.entity;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import com.ey.entity.base.AbstractEntityBean;



@Entity
@Table(name = "SYS_USER")
public class User extends AbstractEntityBean implements java.io.Serializable{
	/**
	 * 用户编号(长度为50，唯一，非空)
	 */
	@NotNull(message = "用户编号不能为空!")
	@Column(name = "LOGINCODE", length = 50, nullable = false)
	private String loginCode;// 用户名

	/**
	 * 用户名(长度为50，唯一，非空)
	 */
	@NotNull(message = "用户名不能为空!")
	@Column(name = "USERNAME", length = 100, nullable = false)
	private String userName;// 用户名

	/**
	 * 用户真实姓名
	 */
	@NotNull(message = "真实姓名不能为空!")
	@Column(name = "REALNAME", length = 100, nullable = false)
	private String realName;// 用户真实姓名

	/**
	 * 密码(长度为100，非空)
	 */
	// springmvc生成json不包含此字段
	//@JsonIgnore
	@NotNull(message = "密码不能为空!")
	@Size(min = 3, max = 100, message = "密码长度为3-100位")
	@Column(name = "PASSWORD", length = 100, nullable = false)
	private String password;// 密码

	public String getLoginCode() {
		return loginCode;
	}

	public void setLoginCode(String loginCode) {
		this.loginCode = loginCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}

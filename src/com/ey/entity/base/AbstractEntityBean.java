package com.ey.entity.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.TableGenerator;


import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.GenericGenerator;

/**
 * 实体类的基类
 * 
 * @author 
 * @version v1.1
 * 
 */
@DynamicInsert
@MappedSuperclass
public class AbstractEntityBean {

	/**
	 * 主键ID，32位唯一UUID,非空
	 */
	//@GenericGenerator(name = "ud", strategy = "identity")
	//@TableGenerator(name = "commonGenerator", table = "base_generator", pkColumnName = "keyName", valueColumnName = "keyValue", pkColumnValue = "pk", allocationSize = 1)
	//@GeneratedValue(strategy = GenerationType.TABLE,generator ="commonGenerator")
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;// 主键ID

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}

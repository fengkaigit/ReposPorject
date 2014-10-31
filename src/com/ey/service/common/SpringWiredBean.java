package com.ey.service.common;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class SpringWiredBean extends SpringBeanAutowiringSupport {

	@Autowired
	private BeanFactory beanFactory;

	private SpringWiredBean() {
	}

	private static SpringWiredBean instance;

	static {
		// 静态块，初始化实例
		instance = new SpringWiredBean();
	}

	/**
	 * 实例方法 使用的时候先通过getInstance方法获取实例
	 * 
	 * @param beanId
	 * @return
	 */
	public Object getBean(String beanId) {
		return beanFactory.getBean(beanId);
	}

	public static SpringWiredBean getInstance() {
		return instance;
	}
}

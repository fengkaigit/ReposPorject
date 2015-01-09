package com.ey.util;
import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ey.dao.entity.UserBase;
import com.ey.service.PoundageService;

public class FeeUtil {
	public static Double getPoundage(String serviceName,
			ServletContext servletContext, UserBase user, Integer paymentType,
			String areaId) {
		PoundageService ps = getPoundageService(serviceName, servletContext);
		return ps.getPoundage(user, paymentType, areaId);
	}
	public static Double getPoundageSelf(String serviceName,
			ServletContext servletContext, UserBase user, Integer paymentType,
			String areaId) {
		PoundageService ps = getPoundageService(serviceName, servletContext);
		return ps.getPoundageSelf(user, paymentType, areaId);
	}
	public static Double getPoundageOther(String serviceName,
			ServletContext servletContext, UserBase user, Integer paymentType,
			String areaId) {
		PoundageService ps = getPoundageService(serviceName, servletContext);
		return ps.getPoundageOther(user, paymentType, areaId);
	}
	public static PoundageService getPoundageService(String serviceName,
			ServletContext servletContext) {
		WebApplicationContext applicationContext = null;
		applicationContext = WebApplicationContextUtils
				.getWebApplicationContext(servletContext);
		return (PoundageService) applicationContext.getBean(serviceName);
	}
}

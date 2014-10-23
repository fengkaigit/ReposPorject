package com.ey.controller.base;

import java.util.Locale;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 * 所有 Controller的父类
 * 
 * 
 * @version 1.0
 * 
 */
@Controller
public class BaseController {
	
	public String getText(String key,HttpServletRequest request){
		 Locale locale = RequestContextUtils.getLocale(request);
		 ApplicationContext ctx = RequestContextUtils.getWebApplicationContext(request);
		 return ctx.getMessage(key, null,locale);
	}
	
}

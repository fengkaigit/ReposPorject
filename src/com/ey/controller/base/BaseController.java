package com.ey.controller.base;

import java.util.Locale;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 * 所有 Controller的父类
 * 
 * 
 * @version 1.0
 * 
 */
public class BaseController {
	
	@RequestMapping(value="/main")
    public ModelAndView main(HttpServletRequest request,HttpServletResponse response){
  	 // ModelAndView mav = new ModelAndView("test/main");
  	  return null;
    }
}

package com.ey.controller.base;

import java.util.Locale;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@ModelAttribute("page")
	public Integer initPage(HttpServletRequest request){
		String p = request.getParameter("page");
		Integer page=1;
		if(p!=null){
		     page = Integer.valueOf(p);
		     return page;
		}
		return page;
	}
	
	@ModelAttribute("rows")
	public Integer initRows(HttpServletRequest request){
		String r = request.getParameter("rows");
		Integer rows = 10;
		if(r!=null){
		   rows = Integer.valueOf(r);
		   return rows;
	    }
		return rows;
	}
	@RequestMapping(value="/main")
    public ModelAndView main(HttpServletRequest request,HttpServletResponse response){
  	  ModelAndView mav = new ModelAndView("home/index");
  	  return mav;
    }
}

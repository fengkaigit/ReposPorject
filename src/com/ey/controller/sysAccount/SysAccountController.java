package com.ey.controller.sysAccount;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ey.bo.SystemAccountBo;
import com.ey.dao.entity.SystemAccount;
import com.ey.service.SystemAccountService;

@Controller
@RequestMapping(value="/sysaccount")
public class SysAccountController {

	@Autowired
    private SystemAccountService sysService;
	
	@RequestMapping(value="/list")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		List<SystemAccountBo> accountList = sysService.findSystemAccountList();
		mav.addObject("syslist", accountList);
		mav.setViewName("sysaccount/index");
		return mav;		
	}
	
	@RequestMapping(value="/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id,HttpServletRequest request,HttpServletResponse response){
	  SystemAccountBo sysAccount = sysService.getSysAccount(id);
	  ModelAndView mav = new ModelAndView("sysaccount/modifyaccount");
	  mav.addObject("sysAccount", sysAccount);
	  return mav;
	}
}

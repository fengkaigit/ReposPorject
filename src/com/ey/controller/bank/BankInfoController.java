package com.ey.controller.bank;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ey.dao.entity.BankInfo;
import com.ey.service.BankAccountService;

@Controller
@RequestMapping(value="/bank")
public class BankInfoController {

	@Autowired
    private BankAccountService bankAccountService;
	
	@RequestMapping(value="/list")
	public ModelAndView list(Integer page,Integer rows,HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		Map<String,Object> params = new HashMap();
		List<BankInfo> accountList = bankAccountService.getBankInfoList(params, page, rows);
		mav.addObject("banklist", accountList);
		mav.setViewName("bank/list");
		return mav;		
	}
}

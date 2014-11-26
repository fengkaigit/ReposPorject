package com.ey.controller.bill;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ey.dao.entity.ServiceChargeBill;
import com.ey.service.BillService;

@Controller
@RequestMapping(value="/bill")
public class BillController {
	
	@Autowired
    private BillService billService;

	@RequestMapping(value="/servicelist")
	public ModelAndView servicelist(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		List<ServiceChargeBill> sysManList = billService.getServiceBillList();
		mav.addObject("servicelist", sysManList);
		mav.setViewName("bill/service");
		return mav;
	}

	@RequestMapping(value="/poundagelist")
	public ModelAndView poundagelist(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		List<ServiceChargeBill> sysManList = null;
		mav.addObject("poundagelist", sysManList);
		mav.setViewName("bill/poundage");
		return mav;
	}
	
	@RequestMapping(value="/profitlist")
	public ModelAndView profitlist(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		List<ServiceChargeBill> sysManList = null;
		mav.addObject("profitlist", sysManList);
		mav.setViewName("bill/profit");
		return mav;
	}
	
	@RequestMapping(value="/settlelist")
	public ModelAndView settlelist(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		List<ServiceChargeBill> sysManList = null;
		mav.addObject("settlelist", sysManList);
		mav.setViewName("bill/settle");
		return mav;
	}
	
	@RequestMapping(value="/incomelist")
	public ModelAndView incomelist(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		List<ServiceChargeBill> sysManList = null;
		mav.addObject("incomelist", sysManList);
		mav.setViewName("bill/income");
		return mav;
	}
}

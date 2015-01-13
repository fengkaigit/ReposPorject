package com.ey.controller.bill;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ey.bo.PaymentBillBo;
import com.ey.bo.ServiceChargeBillBo;
import com.ey.bo.SettleBillBo;
import com.ey.dao.entity.PaymentBill;
import com.ey.service.BillService;

@Controller
@RequestMapping(value="/bill")
public class BillController {
	
	@Autowired
    private BillService billService;

	@RequestMapping(value="/servicelist")
	public ModelAndView servicelist(Date startDate, Date endDate, HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		List<ServiceChargeBillBo> billList = billService.getServiceBillList(startDate, endDate);
		mav.addObject("servicelist", billList);
		mav.setViewName("bill/service");
		return mav;
	}
	
	@RequestMapping(value="/showPayment/{id}")
	public ModelAndView showPayment(@PathVariable("id") Long id,HttpServletRequest request,HttpServletResponse response){
	  List<PaymentBillBo> billList = billService.getPaymentBillList(id);
	  ModelAndView mav = new ModelAndView("bill/paymentbill");
	  mav.addObject("paymentlist", billList);
	  return mav;
	}

	@RequestMapping(value="/poundagelist")
	public ModelAndView poundagelist(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		List<ServiceChargeBillBo> sysManList = billService.getPoundageBillList();
		mav.addObject("poundagelist", sysManList);
		mav.setViewName("bill/poundage");
		return mav;
	}
	
	@RequestMapping(value="/showServicePoundage/{id}")
	public ModelAndView showServicePoundage(@PathVariable("id") Long id,HttpServletRequest request,HttpServletResponse response){
	  List<ServiceChargeBillBo> billList = billService.getServiceBillList(id,"ServiceChargeBill","PoundageServiceRelation","poundageId");
	  ModelAndView mav = new ModelAndView("bill/service");
	  mav.addObject("servicelist", billList);
	  return mav;
	}
	
	@RequestMapping(value="/showServiceProfit/{id}")
	public ModelAndView showServiceProfit(@PathVariable("id") Long id,HttpServletRequest request,HttpServletResponse response){
	  List<ServiceChargeBillBo> billList = billService.getServiceBillList(id, "ServiceChargeBill", "ProfitServiceRelation", "profitBillId");
	  ModelAndView mav = new ModelAndView("bill/service");
	  mav.addObject("servicelist", billList);
	  return mav;
	}
	
	@RequestMapping(value="/profitlist")
	public ModelAndView profitlist(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		List<ServiceChargeBillBo> sysManList = billService.getProfitBillList();
		mav.addObject("profitlist", sysManList);
		mav.setViewName("bill/profit");
		return mav;
	}
	
	@RequestMapping(value="/settlelist")
	public ModelAndView settlelist(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		List<SettleBillBo> sysManList = billService.getSettleBillList();
		mav.addObject("settlelist", sysManList);
		mav.setViewName("bill/settle");
		return mav;
	}
	
	@RequestMapping(value="/steriliselist")
	public ModelAndView steriliselist(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		List<SettleBillBo> sysManList = billService.getSettleBillList();
		mav.addObject("settlelist", sysManList);
		mav.setViewName("bill/sterilise");
		return mav;
	}
	
	@RequestMapping(value="/transferlist")
	public ModelAndView transferlist(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		List<SettleBillBo> sysManList = billService.getSettleBillList();
		mav.addObject("settlelist", sysManList);
		mav.setViewName("bill/transfer");
		return mav;
	}
	
	@RequestMapping(value="/incomelist")
	public ModelAndView incomelist(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		List<ServiceChargeBillBo> sysManList = billService.getIncomeBillList();
		mav.addObject("incomelist", sysManList);
		mav.setViewName("bill/income");
		return mav;
	}
	
	@RequestMapping(value="/showServiceIncome/{id}")
	public ModelAndView showServiceIncome(@PathVariable("id") Long id,HttpServletRequest request,HttpServletResponse response){
	  List<ServiceChargeBillBo> billList = billService.getServiceBillList(id, "ServiceChargeBill", "IncomeServiceRelation", "incomeBillId");
	  ModelAndView mav = new ModelAndView("bill/service");
	  mav.addObject("servicelist", billList);
	  return mav;
	}
	
	@RequestMapping(value="/showServiceSettle/{id}")
	public ModelAndView showServiceSettle(@PathVariable("id") Long id,HttpServletRequest request,HttpServletResponse response){
	  List<ServiceChargeBillBo> billList = billService.getServiceBillList(id, "ServiceChargeBill", "SettleServiceRelation", "settleBillId");
	  ModelAndView mav = new ModelAndView("bill/service");
	  mav.addObject("servicelist", billList);
	  return mav;
	}
}

package com.ey.controller.jf;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ey.bo.ChargeEntBo;
import com.ey.consts.SystemConst;
import com.ey.controller.base.BaseController;
import com.ey.dao.entity.Area;
import com.ey.dao.entity.BankInfo;
import com.ey.dao.entity.UserBase;
import com.ey.forms.JfForm;
import com.ey.service.AreaService;
import com.ey.service.ChargeEntService;
import com.ey.service.FeeService;
import com.ey.service.GHfService;
import com.ey.service.JfService;
import com.ey.util.DateUtil;
import com.ey.util.MoneyUtil;
import com.ey.util.StringUtil;

@Controller
@RequestMapping(value="/ghf")
public class GHfController extends BaseController {
	@Autowired
	private AreaService areaService;
	@Autowired
	private ChargeEntService chargeEntService;
	@Autowired
	private GHfService ghfService;
	@Autowired
	private FeeService feeService;
	@Autowired
	private JfService jfService;
	public final static Integer TYPE = 3;
	@RequestMapping(value = "/first")
	public ModelAndView first(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		UserBase currentUser = (UserBase)request.getSession().getAttribute(SystemConst.USER);
		String settingId = request.getParameter("settingId");
		jfService.prePareParams(mav, currentUser, settingId, TYPE, true,request.getSession().getServletContext());
		List<String> monthes = new ArrayList();
		for(int i=1;i<=12;i++){
			if(i<10){
				monthes.add("0"+i);
			}else{
				monthes.add(""+i);
			}
		}
		Date date = new Date();
		int curYear = DateUtil.getYear(date);
		int curMonth = DateUtil.getMonth(date);
		int preYear = curYear-1;
		List<Integer> years = new ArrayList();
		years.add(preYear);
		years.add(curYear);
		/*FeeRule feeRule = feeService.getFeeRule(TYPE, date);
		if(feeRule!=null){
			try{
				Object obj = ClassUtil.loadClass(feeRule.getRule());
				Double poundage = (Double)ClassUtil.invokeMothod(obj, "getPoundage");
				mav.addObject("poundage", poundage);
			}catch(Exception ex){
				ex.printStackTrace();
			}
			
		}*/
		mav.addObject("years",years );
		mav.addObject("year",curYear );
		mav.addObject("month",curMonth );
		mav.addObject("monthes", monthes);
		mav.addObject("paymentType", TYPE);
		mav.setViewName("jf/ghf/first");
		return mav;
	}
	@RequestMapping(value = "/second")
	public ModelAndView second(HttpServletRequest request,
			HttpServletResponse response,JfForm form) {
		UserBase currentUser = (UserBase)request.getSession().getAttribute(SystemConst.USER);
		form.setPayType(TYPE);
		form.setPaymentStatus(0);//0：创建；
		form.setBusinessType(0);//0：代缴；
		form.setBillType(1);//1：虚拟账单；
		form.setUserId(currentUser.getId());//登录用户
		form.setUserName(currentUser.getRealName());
		form.setPaymentMode(0);//0：银行缴费
		form.setDivideStatus(0);//0：未生成劳务费划款单据
		form.setPeriodFrequency("month");//month;
		if(form.getEntId()!=null){
			ChargeEntBo ceb = chargeEntService.getChargeEnt(form.getEntId());
			if(ceb!=null){
				form.setEndName(ceb.getEnterpriseName());
			}
			
		}
		if(form.getAreaId()!=null){
			Area area = this.areaService.getArea(form.getAreaId());
			if(area!=null){
				form.setAreaName(area.getProvince());
			}
		}
		form.setMoneycn(MoneyUtil.toUpperCase(form.getBillMoney()+form.getPoundage()));
		ghfService.saveBill(form,currentUser,request.getSession().getServletContext());
		request.getSession().setAttribute(SystemConst.GHF_BILL, form);
		ModelAndView mav = new ModelAndView();
		mav.addObject(SystemConst.GHF_BILL,form);
		mav.setViewName("jf/ghf/second");
		return mav;
	}
	@RequestMapping(value = "/third")
	public ModelAndView third(HttpServletRequest request,
			HttpServletResponse response) {
		JfForm form = (JfForm)request.getSession().getAttribute(SystemConst.GHF_BILL);
		String bankCode = request.getParameter("bankCode");
		if(!StringUtil.isEmptyString(bankCode)){
			BankInfo info = (BankInfo)ghfService.getObjectById(BankInfo.class,bankCode);
			if(info!=null){
				form.setBankName(info.getBankName());
			}
			form.setBankCode(bankCode);
		}
		form.setPaymentStatus(0);//3：核实确认；
		UserBase currentUser = (UserBase) request.getSession().getAttribute(SystemConst.USER);
		ghfService.saveBill(form,currentUser,request.getSession().getServletContext());
		ModelAndView mav = new ModelAndView();
		mav.addObject(SystemConst.GHF_BILL,form);
		mav.setViewName("jf/ghf/third");
		return mav;
	}
	@RequestMapping(value = "/fourth")
	public ModelAndView fourth(HttpServletRequest request,
			HttpServletResponse response,JfForm jform) {
		JfForm form = (JfForm)request.getSession().getAttribute(SystemConst.GHF_BILL);
		form.setBankAccount(jform.getBankAccount());
		form.setMobile(jform.getMobile());
		form.setMobileVerify(jform.getMobileVerify());
		form.setRemark(jform.getRemark());
		
		form.setPaymentStatus(1);//1：系统缴费成功；
		UserBase currentUser = (UserBase) request.getSession().getAttribute(SystemConst.USER);
		ghfService.saveBill(form,currentUser,request.getSession().getServletContext());
		//request.getSession().removeAttribute(SystemConst.GHF_BILL);
		ModelAndView mav = new ModelAndView();
		mav.addObject(SystemConst.GHF_BILL,form);
		mav.setViewName("jf/ghf/fourth");
		return mav;
	}
}

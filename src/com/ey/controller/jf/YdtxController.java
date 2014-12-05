package com.ey.controller.jf;

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
import com.ey.dao.entity.ChargeEnterprise;
import com.ey.dao.entity.FeeRule;
import com.ey.dao.entity.UserBase;
import com.ey.forms.JfForm;
import com.ey.service.AreaService;
import com.ey.service.ChargeEntService;
import com.ey.service.FeeService;
import com.ey.service.JfService;
import com.ey.service.YdtxfService;
import com.ey.util.ClassUtil;
import com.ey.util.DateUtil;
import com.ey.util.MoneyUtil;
import com.ey.util.StringUtil;

@Controller
@RequestMapping(value="/ydtx")
public class YdtxController extends BaseController {
	@Autowired
	private AreaService areaService;
	@Autowired
	private ChargeEntService chargeEntService;
	@Autowired
	private YdtxfService ydtxfService;
	@Autowired
	private FeeService feeService;
	@Autowired
	private JfService jfService;
	public final static Integer TYPE = 4;
	@RequestMapping(value = "/first")
	public ModelAndView first(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		String settingId = request.getParameter("settingId");
		UserBase currentUser = (UserBase)request.getSession().getAttribute(SystemConst.USER);
		jfService.prePareParams(mav, currentUser, settingId, TYPE, false);
		Date date = new Date();
		FeeRule feeRule = feeService.getFeeRule(TYPE, date);
		if(feeRule!=null){
			try{
				Object obj = ClassUtil.loadClass(feeRule.getRule());
				Double poundage = (Double)ClassUtil.invokeMothod(obj, "getPoundage");
				mav.addObject("poundage", poundage);
			}catch(Exception ex){
				ex.printStackTrace();
			}
			
		}
		mav.addObject("paymentType", TYPE);
		mav.setViewName("jf/ydtx/first");
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
		String areaId = form.getAreaId();
		if(StringUtil.isEmptyString(areaId)){
			areaId = currentUser.getAreaId();
			form.setAreaId(areaId);
		}
		List<ChargeEnterprise> charges = chargeEntService.getChargesByArea(areaId, TYPE);
		if(charges!=null&&charges.size()>0){
			form.setEntId(charges.get(0).getId());
		}
		if(form.getYear()==null){
			Date date = new Date();
			int curYear = DateUtil.getYear(date);
			int curMonth = DateUtil.getMonth(date);
			form.setYear(curYear);
			form.setMonth(curMonth+"");
		}
		if(form.getMobiles()!=null){
			StringBuffer b =  new StringBuffer("");
			for(String m:form.getMobiles()){
				if(b.length()>0){
					b.append(",");
				}
				b.append(m);
			}
			form.setBillNumber(b.toString());
		}else{
			form.setBillNumber("无");
		}
		if(form.getBillMoneys()!=null&&form.getBillMoneys().length>0){
			double billMoney = 0;
			for(double bm:form.getBillMoneys()){
				billMoney = billMoney +bm;
			}
			form.setBillMoney(billMoney);
		}
		
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
		ydtxfService.saveBill(form);
		request.getSession().setAttribute(SystemConst.YDTXF_BILL, form);
		ModelAndView mav = new ModelAndView();
		mav.addObject(SystemConst.YDTXF_BILL,form);
		mav.setViewName("jf/ydtx/second");
		return mav;
	}
	@RequestMapping(value = "/third")
	public ModelAndView third(HttpServletRequest request,
			HttpServletResponse response) {
		JfForm form = (JfForm)request.getSession().getAttribute(SystemConst.YDTXF_BILL);
		String bankCode = request.getParameter("bankCode");
		if(!StringUtil.isEmptyString(bankCode)){
			BankInfo info = (BankInfo)ydtxfService.getObjectById(BankInfo.class,bankCode);
			if(info!=null){
				form.setBankName(info.getBankName());
			}
			form.setBankCode(bankCode);
		}
		form.setPaymentStatus(3);//3：核实确认；
		ydtxfService.saveBill(form);
		ModelAndView mav = new ModelAndView();
		mav.addObject(SystemConst.YDTXF_BILL,form);
		mav.setViewName("jf/ydtx/third");
		return mav;
	}
	@RequestMapping(value = "/fourth")
	public ModelAndView fourth(HttpServletRequest request,
			HttpServletResponse response,JfForm jform) {
		JfForm form = (JfForm)request.getSession().getAttribute(SystemConst.YDTXF_BILL);
		form.setBankAccount(jform.getBankAccount());
		form.setMobile(jform.getMobile());
		form.setMobileVerify(jform.getMobileVerify());
		form.setRemark(jform.getRemark());
		
		form.setPaymentStatus(1);//1：系统缴费成功；
		ydtxfService.saveBill(form);
		//request.getSession().removeAttribute(SystemConst.YDTXF_BILL);
		ModelAndView mav = new ModelAndView();
		mav.addObject(SystemConst.YDTXF_BILL,form);
		mav.setViewName("jf/ydtx/fourth");
		return mav;
	}
}

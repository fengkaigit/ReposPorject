package com.ey.controller.phoneController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ey.bo.AreaBo;
import com.ey.bo.CatvInfoBo;
import com.ey.bo.ChargeEntBo;
import com.ey.bo.ConfirmPaymentBo;
import com.ey.bo.NoticeInfoBo;
import com.ey.bo.PaymentSettingPhoneBo;
import com.ey.bo.PaymentSettingPropertyBo;
import com.ey.bo.PaymentSettingStandardBo;
import com.ey.bo.PaymentSettingTrafficBo;
import com.ey.bo.ResultBo;
import com.ey.bo.StandardBo;
import com.ey.consts.SystemConst;
import com.ey.dao.common.dbid.DbidGenerator;
import com.ey.dao.entity.AgentInfo;
import com.ey.dao.entity.Area;
import com.ey.dao.entity.CatvInfo;
import com.ey.dao.entity.ChargeEnterprise;
import com.ey.dao.entity.FeeRule;
import com.ey.dao.entity.NoticeInfo;
import com.ey.dao.entity.PaymentSetting;
import com.ey.dao.entity.UserBase;
import com.ey.forms.JfForm;
import com.ey.service.AgentService;
import com.ey.service.AreaService;
import com.ey.service.CatvService;
import com.ey.service.ChargeEntService;
import com.ey.service.CnfService;
import com.ey.service.DfService;
import com.ey.service.FeeService;
import com.ey.service.GHfService;
import com.ey.service.JfService;
import com.ey.service.JtfkfService;
import com.ey.service.LoginService;
import com.ey.service.RQfService;
import com.ey.service.SettingService;
import com.ey.service.SfService;
import com.ey.service.UserService;
import com.ey.service.WYfService;
import com.ey.service.YXfService;
import com.ey.service.YdtxfService;
import com.ey.util.ClassUtil;
import com.ey.util.MD5;
import com.ey.util.MoneyUtil;
import com.ey.util.RequestUtils;
import com.ey.util.StringUtil;

@Controller
@RequestMapping(value="/phone")
public class PhoneController {

	@Autowired
    private AreaService areaService;//行政区域
	
	@Autowired
    private ChargeEntService chargeEntService;//收款单位
	
	@Autowired
    private SettingService settingService;//缴费信息设置
	
	@Autowired
    private CatvService catvService;//有线电视台信息
	
	@Autowired
	private LoginService loginService;//登录
	
	@Autowired
	private SfService sfService;//水费
	
	@Autowired
	private CnfService cnfService;//采暖费
	
	@Autowired
	private DfService dfService;//电费
	
	@Autowired
	private GHfService ghfService;//固话宽带
	
	@Autowired
	private JtfkfService jtfkfService;//交通罚款
	
	@Autowired
	private RQfService rqfService;//燃气费
	
	@Autowired
	private WYfService wyfService;//物业
	
	@Autowired
	private YdtxfService ydtxfService;//移动
	
	@Autowired
	private YXfService yxfService;//有线电视
	
	@Autowired
	private AgentService agentService;
	
	@Autowired
	private FeeService feeService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/areaJson")
	public ModelAndView getAreaInJSON(String areaType,HttpServletRequest request,
			HttpServletResponse response) throws IOException, JSONException{
		List<AreaBo> areaLst = new ArrayList();
		JSONObject obj = new JSONObject();
		try{
			
			List<Area> dataList = areaService.getAreaList(areaType);
			// 节点列表（散列表，用于临时存储节点对象）  
			HashMap nodeList = new HashMap();  
			// 根据结果集构造节点列表（存入散列表）
			for (Iterator it = dataList.iterator(); it.hasNext();) {
				Area dataRecord = (Area) it.next();
				AreaBo node = new AreaBo();  
	
				node.setId( (String)dataRecord.getId());
				node.setName( (String) dataRecord.getProvince());
				node.setParentId( (String)dataRecord.getCity());
				nodeList.put(node.getId(), node);
			}
			
			// 构造无序的多叉树
			Set entrySet = nodeList.entrySet();  
			for (Iterator it = entrySet.iterator(); it.hasNext();) {
				AreaBo node = (AreaBo) ((Map.Entry) it.next()).getValue();
				if (node.findParentId().equals("0")) {  
					((AreaBo)nodeList.get(node.getId())).setId(node.getId());
					((AreaBo)nodeList.get(node.getId())).setName(node.getName());
					((AreaBo)nodeList.get(node.getId())).setParentId(node.findParentId());
				}else{
				   ((AreaBo) nodeList.get(node.findParentId())).addChild(node);
				}
			}
			for (Iterator it = entrySet.iterator(); it.hasNext();) {
				AreaBo node = (AreaBo) ((Map.Entry) it.next()).getValue();
				if (node.findParentId().equals("0")){
					((AreaBo)nodeList.get(node.getId())).sortChildren();
					areaLst.add((AreaBo)nodeList.get(node.getId()));
				}
			}
			// 输出有序的树形菜单的JSON字符串
			Collections.sort(areaLst,new NodeIDComparator());
			JSONArray jsonArr=JSONArray.fromObject(areaLst);
			obj.put("success", true);
			obj.put("data", jsonArr);
		}catch (Exception e) {
			obj.put("success", false);
			obj.put("data", "获取缴费区域信息失败！");
		}
		
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		String retnStr = "";
		if (request.getParameter("callback")!=null)
			retnStr = request.getParameter("callback");
		else
			retnStr = "callback";
		retnStr = retnStr + "(" + obj.toString() + ")";
		out.println(retnStr);
		out.flush();
		out.close();
		return null;
	}
	
	@RequestMapping(value="/entJson")
	public ModelAndView getEntInJSON(String areaId,int payType,HttpServletRequest request,
			HttpServletResponse response) throws JSONException, IOException{
		JSONObject obj = new JSONObject();
		try{
			List<ChargeEnterprise> lst = chargeEntService.getChargesByArea(areaId, payType);
			List<StandardBo> boLst = new ArrayList();
			for (ChargeEnterprise ent:lst){
				StandardBo bo = new StandardBo(ent.getId(),ent.getEnterpriseName());
				boLst.add(bo);
			}
			JSONArray jsonArr=JSONArray.fromObject(boLst);
			obj.put("success", true);
			obj.put("data", jsonArr);
		}catch (Exception e) {
			obj.put("success", false);
			obj.put("data", "获取缴费单位信息失败！");
		}
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		String retnStr = "";
		if (request.getParameter("callback")!=null)
			retnStr = request.getParameter("callback");
		else
			retnStr = "callback";
		retnStr = retnStr + "(" + obj.toString() + ")";
		out.println(retnStr);
		out.flush();
		out.close();
		return null;
	}
	
	@RequestMapping(value="/paymentSettingJson")
	public ModelAndView getPaymentSettingInJSON(int payType,HttpServletRequest request,
			HttpServletResponse response) throws JSONException, IOException{
		JSONObject obj = new JSONObject();
		try{
			UserBase currentUser = (UserBase) request.getSession().getAttribute(
					SystemConst.USER);
			List<PaymentSetting> lst = settingService.list(currentUser.getId(),payType);
			List standardLst = new ArrayList();
			for (PaymentSetting setting:lst){
				if (payType==4){
					PaymentSettingPhoneBo bo = new PaymentSettingPhoneBo(setting.getBillNumber(),setting.getHoster());
					standardLst.add(bo);
				}else if (payType==5){
					PaymentSettingTrafficBo bo = new PaymentSettingTrafficBo(setting.getBillNumber(),setting.getHoster(),setting.getVehicleNumber(),setting.getCarframeNumber(),setting.getEngineNumber());
					standardLst.add(bo);
				}else if (payType==6){
					PaymentSettingPropertyBo bo = new PaymentSettingPropertyBo(setting.getHoster(),setting.getPayAddress());
					standardLst.add(bo);
				}else{
					PaymentSettingStandardBo bo = new PaymentSettingStandardBo(setting.getBillNumber(),setting.getHoster(),setting.getPayAddress());
					standardLst.add(bo);
				}
			}
			JSONArray jsonArr=JSONArray.fromObject(standardLst);
			obj.put("success", true);
			obj.put("data", jsonArr);
		}catch (Exception e) {
			obj.put("success", false);
			obj.put("data", "获取缴费账户设置信息失败！");
		}

		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		String retnStr = "";
		if (request.getParameter("callback")!=null)
			retnStr = request.getParameter("callback");
		else
			retnStr = "callback";
		retnStr = retnStr + "(" + obj.toString() + ")";
		out.println(retnStr);
		out.flush();
		out.close();
		return null;
	}
	
	@RequestMapping(value="/catvInfoJson")
	public ModelAndView getCatvInJSON(String areaId,int televisionType,HttpServletRequest request,
			HttpServletResponse response) throws JSONException, IOException{
		JSONObject obj = new JSONObject();
		try{
			List<CatvInfo> lst = catvService.getCatvInfo(areaId, televisionType);
			List<CatvInfoBo> boLst = new ArrayList();
			for (CatvInfo catv:lst){
				CatvInfoBo bo = new CatvInfoBo(catv.getId(),catv.getTelevisionName(),catv.getTelevisionMoney());
				boLst.add(bo);
			}
			JSONArray jsonArr=JSONArray.fromObject(boLst);
			obj.put("success", true);
			obj.put("data", jsonArr);
		}catch (Exception e) {
			obj.put("success", false);
			obj.put("data", "获取有线电视设置信息失败！");
		}
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		String retnStr = "";
		if (request.getParameter("callback")!=null)
			retnStr = request.getParameter("callback");
		else
			retnStr = "callback";
		retnStr = retnStr + "(" + obj.toString() + ")";
		out.println(retnStr);
		out.flush();
		out.close();
		return null;
	}
	
	@RequestMapping(value="/saveSetting")
	public ModelAndView savePaySetting(String areaId,Long entId,
			int payType,String paymentCode,String payAddress,String vehicle, 
			String carframe, String engine,HttpServletRequest request,
			HttpServletResponse response) throws JSONException, IOException{
		//0：水费；1：电费；2：燃气；3：固话；4：移动；5：交通；6：物业；7：有线电视；8采暖
		JSONObject obj = new JSONObject();
		try{
			if (StringUtil.isEmptyString(areaId)) {
				obj.put("success", false);
				obj.put("data",RequestUtils.getMessage("noarea",request));
			}else{
				UserBase currentUser = (UserBase) request.getSession().getAttribute(
						SystemConst.USER);
				JfForm form = new JfForm();
				Area area=areaService.getArea(areaId);
				if (entId != null) {
					ChargeEntBo ceb = chargeEntService.getChargeEnt(entId);
					if (ceb != null) {
						form.setEndName(ceb.getEnterpriseName());
					}
	
				}
				form.setAreaId(areaId);
				form.setAreaName(area.getProvince());
				form.setEntId(entId);
				form.setPayAddress(payAddress);
				form.setCarframeNumber(carframe);
				form.setVehicleNumber(vehicle);
				form.setEngineNumber(engine);
				form.setUserId(currentUser.getId());
				form.setUserName(currentUser.getRealName());
				form.setPayType(payType);
				form.setBillNumber(paymentCode);
				form.setBczh(true);
				if (payType==0){
					sfService.saveSetting(form, new Date());
				}else if (payType==1){
					dfService.saveSetting(form, new Date());
				}else if (payType==2){
					rqfService.saveSetting(form, new Date());
				}else if (payType==3){
					ghfService.saveSetting(form, new Date());
				}else if (payType==4){
					ydtxfService.saveSetting(form, new Date());
				}else if (payType==5){
					jtfkfService.saveSetting(form, new Date());
				}else if (payType==6){
					form.setBillNumber(currentUser.getRealName());
					wyfService.saveSetting(form, new Date());
				}else if (payType==7){
					yxfService.saveSetting(form, new Date());
				}else if (payType==8){
					cnfService.saveSetting(form, new Date());
				}
				obj.put("success", true);
				obj.put("data", "设置用户缴费账号信息成功！");
			}
		}catch (Exception e) {
			obj.put("success", false);
			obj.put("data", "设置用户缴费账号信息失败！");
		}
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		String retnStr = "";
		if (request.getParameter("callback")!=null)
			retnStr = request.getParameter("callback");
		else
			retnStr = "callback";
		retnStr = retnStr + "(" + obj.toString() + ")";
		out.println(retnStr);
		out.flush();
		out.close();
		return null;
	}
	
	@RequestMapping(value="/phoneLogin")
	public ModelAndView phoneLogin(String userId,String passwd, HttpServletRequest request,
			HttpServletResponse response) throws JSONException, IOException{
		JSONObject obj = new JSONObject();
		UserBase currentUser = loginService.findUserByLoginCode(userId, MD5.getMD5Str(passwd));
		try{
			if (StringUtil.isEmptyString(userId)) {
				obj.put("success", false);
				obj.put("data",RequestUtils.getMessage("nologin",request));
			}else if (StringUtil.isEmptyString(passwd)) {
				obj.put("success", false);
				obj.put("data",RequestUtils.getMessage("nopassword",request));
			}else if (currentUser == null) {
				obj.put("success", false);
				obj.put("data",RequestUtils.getMessage("login",request));
			}else {
				request.getSession().setAttribute(SystemConst.USER,currentUser);
				obj.put("success", true);
				obj.put("data",request.getSession().getId());
			}
		}catch (Exception e) {
			obj.put("success", false);
			obj.put("data","用户登录失败！");
		}
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		String retnStr = "";
		if (request.getParameter("callback")!=null)
			retnStr = request.getParameter("callback");
		else
			retnStr = "callback";
		retnStr = retnStr + "(" + obj.toString() + ")";
		out.println(retnStr);
		out.flush();
		out.close();
		return null;
	}
	
	@RequestMapping(value="/phoneReg")
	public ModelAndView phoneReg(String userId,String passwd, int regType, String areaId, String vcode, HttpServletRequest request,
			HttpServletResponse response) throws JSONException, IOException{
		JSONObject obj = new JSONObject();
		try{
			if (StringUtil.isEmptyString(userId)) {
				obj.put("success", false);
				obj.put("data",RequestUtils.getMessage("nologin",request));
			}else if (StringUtil.isEmptyString(passwd)) {
				obj.put("success", false);
				obj.put("data",RequestUtils.getMessage("nopassword",request));
			}else{
				UserBase user = new UserBase();
				
				user.setAccountNumber(userId);
				user.setRealName(user.getAccountNumber());
				user.setMobilePhone(user.getAccountNumber());

				user.setPasswd(MD5
						.getMD5Str(passwd));
				UserBase currentUser = loginService.findUserByLoginCode(user.getAccountNumber());
				if(currentUser!=null){
					obj.put("success", false);
					obj.put("data",RequestUtils.getMessage("duplicateuser",request));
				}else{
					user.setRegistType(regType);
					user.setAreaId(areaId);
					user.setRegTime(new Date());
					loginService.saveUser(user);
					request.getSession().setAttribute(SystemConst.USER,user);
					obj.put("success", true);
					obj.put("data",request.getSession().getId());
				}
			}
		}catch (Exception e) {
			obj.put("success", false);
			obj.put("data","用户注册失败！");
		}
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		String retnStr = "";
		if (request.getParameter("callback")!=null)
			retnStr = request.getParameter("callback");
		else
			retnStr = "callback";
		retnStr = retnStr + "(" + obj.toString() + ")";
		out.println(retnStr);
		out.flush();
		out.close();
		return null;
	}

	@RequestMapping(value="/confirmPayment")
	public ModelAndView confirmPayment(String areaId,Long entId,
			int payType,String paymentCode,String payAddress,String vehicle, 
			String carframe, String engine, Double payMoney,
			String beginPeriod, String endPeriod,
			HttpServletRequest request,HttpServletResponse response) throws JSONException, IOException{
		//0：水费；1：电费；2：燃气；3：固话；4：移动；5：交通；6：物业；7：有线电视；8采暖
		ConfirmPaymentBo retnBo = new ConfirmPaymentBo();
		JSONObject jsonObj = new JSONObject();
		try{
			if (StringUtil.isEmptyString(areaId)) {
				jsonObj.put("success", false);
				jsonObj.put("data",RequestUtils.getMessage("noarea",request));
			}else{
				UserBase currentUser = (UserBase) request.getSession().getAttribute(
						SystemConst.USER);
				JfForm form = new JfForm();
				Area area=areaService.getArea(areaId);
				if (entId != null) {
					ChargeEntBo ceb = chargeEntService.getChargeEnt(entId);
					if (ceb != null) {
						form.setEndName(ceb.getEnterpriseName());
					}
				}
				AgentInfo agent = agentService.getAgentByArea(areaId);
				
				form.setAgentId(agent.getId());
				form.setAgentName(agent.getRegistRealName());
				form.setAreaId(areaId);
				form.setAreaName(area.getProvince());
				form.setEntId(entId);
				form.setPayAddress(payAddress);
				form.setCarframeNumber(carframe);
				form.setVehicleNumber(vehicle);
				form.setEngineNumber(engine);
				form.setUserId(currentUser.getId());
				form.setUserName(currentUser.getRealName());
				form.setPayType(payType);
				form.setBillNumber(paymentCode);
				
				FeeRule feeRule = feeService.getFeeRule(payType, new Date());
				if (feeRule != null) {
					try {
						Object obj = ClassUtil.loadClass(feeRule.getRule());
						Double poundage = (Double) ClassUtil.invokeMothod(obj,
								"getPoundage");
						retnBo.setPoundage(poundage);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
				form.setPoundage(retnBo.getPoundage());
				form.setPaymentStatus(0);// 0：创建；
				form.setBusinessType(0);//0：代缴；
				form.setBillType(1);// 1：虚拟账单；
				form.setUserId(currentUser.getId());// 登录用户
				form.setUserName(currentUser.getRealName());
				form.setPaymentMode(0);// 0：银行缴费
				form.setDivideStatus(0);// 0：未生成劳务费划款单据
				form.setPeriodFrequency("month");// month;
				form.setMoneycn(MoneyUtil.toUpperCase(payMoney
						+ form.getPoundage()));
				form.setYear(Integer.valueOf(beginPeriod.substring(0,3)));
				form.setMonth(beginPeriod.substring(5,6));
	
				Long dbId = DbidGenerator.getDbidGenerator().getNextId();
				
				String orderNum = StringUtil.getBillNo(new Date(), dbId);
				form.setOrderNum(orderNum);
				
				if (payType==0){
					sfService.saveBill(form);
				}else if (payType==1){
					dfService.saveBill(form);
				}else if (payType==2){
					rqfService.saveBill(form);
				}else if (payType==3){
					ghfService.saveBill(form);
				}else if (payType==4){
					ydtxfService.saveBill(form);
				}else if (payType==5){
					jtfkfService.saveBill(form);
				}else if (payType==6){
					form.setBillNumber(currentUser.getRealName());
					wyfService.saveBill(form);
				}else if (payType==7){
					yxfService.saveBill(form);
				}else if (payType==8){
					cnfService.saveBill(form);
				}
				retnBo.setOrderNum(orderNum);
				retnBo.setPayMoney(payMoney+retnBo.getPoundage());
				retnBo.setBillNumber(paymentCode);
				retnBo.setPayData(StringUtil.encodeString(paymentCode));
				retnBo.setPayType(payType);
				
				jsonObj.put("success", true);
				jsonObj.put("data",retnBo);
			}
		}catch (Exception e) {
			jsonObj.put("success", false);
			jsonObj.put("data","设置缴费确认失败！");
		}
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		String retnStr = "";
		if (request.getParameter("callback")!=null)
			retnStr = request.getParameter("callback");
		else
			retnStr = "callback";
		retnStr = retnStr + "(" + jsonObj.toString() + ")";
		out.println(retnStr);
		out.flush();
		out.close();
		return null;
	}
	
	@RequestMapping(value="/phoneLogout")
	public ModelAndView phoneLogout(HttpServletRequest request,
			HttpServletResponse response) throws JSONException, IOException{
		JSONObject obj = new JSONObject();
		try{
			request.getSession().removeAttribute(SystemConst.USER);
			request.getSession().invalidate();
			obj.put("success", true);
			obj.put("data","用户注销成功");
		}catch (Exception e) {
			obj.put("success", false);
			obj.put("data","用户注销失败");
		}
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		String retnStr = "";
		if (request.getParameter("callback")!=null)
			retnStr = request.getParameter("callback");
		else
			retnStr = "callback";
		retnStr = retnStr + "(" + obj.toString() + ")";
		out.println(retnStr);
		out.flush();
		out.close();
		return null;
	}
	
	@RequestMapping(value="/getMessage")
	public ModelAndView getMessage(HttpServletRequest request,
			HttpServletResponse response) throws IOException, JSONException{
		JSONObject obj = new JSONObject();
		try{
			UserBase currentUser = (UserBase) request.getSession().getAttribute(
					SystemConst.USER);
			
			obj.put("success", true);
			obj.put("data","");
		}catch (Exception e) {
			obj.put("success", true);
			obj.put("data","查询用户消息信息失败");
		}
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		String retnStr = "";
		if (request.getParameter("callback")!=null)
			retnStr = request.getParameter("callback");
		else
			retnStr = "callback";
		retnStr = retnStr + "(" + obj.toString() + ")";
		out.println(retnStr);
		out.flush();
		out.close();
		return null;
	}
	
	@RequestMapping(value="/getSystemMessage")
	public ModelAndView getSystemMessage(HttpServletRequest request,
			HttpServletResponse response) throws JSONException, IOException{
		JSONObject obj = new JSONObject();
		try{
			UserBase currentUser = (UserBase) request.getSession().getAttribute(
					SystemConst.USER);
			List<NoticeInfo> noticeLst = userService.findNoticeByUserId(currentUser.getId());
			List<NoticeInfoBo> retnLst = new ArrayList();
			for (NoticeInfo notice:noticeLst){
				NoticeInfoBo bo = new NoticeInfoBo(notice.getId(),notice.getServerContent().toString(),notice.getCreateTime(),notice.getNoticeType());
				retnLst.add(bo);
			}
			obj.put("success", true);
			obj.put("data",retnLst);
		}catch (Exception e) {
			obj.put("success", true);
			obj.put("data","查询系统信息失败！");
		}
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		String retnStr = "";
		if (request.getParameter("callback")!=null)
			retnStr = request.getParameter("callback");
		else
			retnStr = "callback";
		retnStr = retnStr + "(" + obj.toString() + ")";
		out.println(retnStr);
		out.flush();
		out.close();
		return null;
	}
	
	@RequestMapping(value="/getHisPayment")
	public ModelAndView getHisPayment(HttpServletRequest request,
			HttpServletResponse response) throws JSONException, IOException{
		JSONObject obj = new JSONObject();
		try{
			UserBase currentUser = (UserBase) request.getSession().getAttribute(
					SystemConst.USER);
			
			obj.put("success", true);
			obj.put("data","");
		}catch (Exception e) {
			obj.put("success", true);
			obj.put("data","查询用户消息信息失败！");
		}
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		String retnStr = "";
		if (request.getParameter("callback")!=null)
			retnStr = request.getParameter("callback");
		else
			retnStr = "callback";
		retnStr = retnStr + "(" + obj.toString() + ")";
		out.println(retnStr);
		out.flush();
		out.close();
		return null;
	}
	
}



/** 
* 节点比较器 
*/  
class NodeIDComparator implements Comparator {  
	// 按照节点编号比较  
	public int compare(Object o1, Object o2) {  
		int j1 = Integer.parseInt(((AreaBo)o1).id);  
		int j2 = Integer.parseInt(((AreaBo)o2).id);  
		return (j1 < j2 ? -1 : (j1 == j2 ? 0 : 1));  
	}   
}

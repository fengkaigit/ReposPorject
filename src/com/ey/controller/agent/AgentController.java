package com.ey.controller.agent;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jofc2.model.Chart;
import jofc2.model.elements.PieChart;
import jofc2.model.elements.PieChart.Slice;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ey.bo.AgentBo;
import com.ey.bo.CountReportBo;
import com.ey.bo.PaymentBillBo;
import com.ey.bo.QueryBillBO;
import com.ey.consts.SystemConst;
import com.ey.controller.base.BaseController;
import com.ey.dao.entity.AgentInfo;
import com.ey.dao.entity.Area;
import com.ey.dao.entity.BankAccount;
import com.ey.dao.entity.BankInfo;
import com.ey.dao.entity.NoticeInfo;
import com.ey.dao.entity.PaymentBill;
import com.ey.dao.entity.SystemManager;
import com.ey.exception.BusinessException;
import com.ey.service.AgentService;
import com.ey.service.AreaService;
import com.ey.service.StaticService;
import com.ey.util.CookieManager;
import com.ey.util.CurrencyConverter;
import com.ey.util.DateUtil;
import com.ey.util.MD5;
import com.ey.util.RequestUtils;
import com.ey.util.StringUtil;
import com.ey.util.XLSReport;

@Controller
@RequestMapping(value = "/agent")
public class AgentController extends BaseController {

	private static final String LOGIN_PAGE = "login/agentlogin";
	private static final String IFRAME_PAGE = "agent/iframe";
	private static final String BILLLIST_PAGE = "agent/billlist";
	private static final String SELFLIST_PAGE = "agent/selflist";
	private static final String STATUSLIST_PAGE = "agent/statuslist";
	private static final String NOWLIST_PAGE = "agent/nowlist";
	private static final String LIST_PAGE = "agent/list";
	private static final String REDIRECT = "redirect:/agent/list.do";
	private static final String ADD_PAGE = "agent/addagent";
	private static final String INDEX_PAGE = "agent/index";
	private static final String REDIRECTINDEX = "redirect:/agent/index.do";
	private static final String REDIRECTSTATIS = "redirect:/agent/statuslist.do";
	private static final String PASSWORD_PAGE = "agent/modifypass";
	private static final Integer COMPLATESTATUS = 10;
	@Autowired
	private AgentService agentService;

	@Autowired
	private AreaService areaService;

	@Autowired
	private StaticService staticService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(String verify, String loginCode, String password,
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		if (StringUtil.isEmptyString(loginCode)) {
			mav.addObject("message",
					RequestUtils.getMessage("nologin", request));
			mav.setViewName(LOGIN_PAGE);
			return mav;
		}
		if (StringUtil.isEmptyString(password)) {
			mav.addObject("message",
					RequestUtils.getMessage("nopassword", request));
			mav.setViewName(LOGIN_PAGE);
			return mav;
		}
		if (StringUtil.isEmptyString(verify)) {
			mav.addObject("message",
					RequestUtils.getMessage("noverify", request));
			mav.setViewName(LOGIN_PAGE);
			return mav;
		} else {
			String validateCode = (String) request.getSession().getAttribute(
					"validateCode");
			if (!verify.equalsIgnoreCase(validateCode)) {
				mav.addObject("message",
						RequestUtils.getMessage("invalidateverify", request));
				mav.setViewName(LOGIN_PAGE);
				return mav;
			}
		}

		AgentBo agent = agentService.findAgentByLoginName(loginCode,
				MD5.getMD5Str(password));
		if (agent == null) {
			mav.addObject("message", RequestUtils.getMessage("login", request));
			mav.setViewName(LOGIN_PAGE);
		} else {
			mav.setViewName(INDEX_PAGE);
			mav.addObject(SystemConst.USER, agent);
			request.getSession().setAttribute(SystemConst.AGENT, agent);
			if (request.getParameter("remember") != null) {
				CookieManager.addCookie(response, "agentLoginName", loginCode,
						60 * 60 * 24 * 31);
				CookieManager.addCookie(response, "agentLoginPwd", password,
						60 * 60 * 24 * 31);
			}
		}
		return mav;
	}

	@RequestMapping(value = "/login")
	public String login(HttpServletRequest request, HttpServletResponse response) {
		return LOGIN_PAGE;
	}

	@RequestMapping(value = "/iframe")
	public ModelAndView iframe(ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response) {
		AgentBo agent = (AgentBo) request.getSession().getAttribute(
				SystemConst.AGENT);
		ModelAndView mav = new ModelAndView(IFRAME_PAGE);
		List<CountReportBo> reportlist = agentService.findReportByAgentId(
				agent.getId(), String.valueOf(DateUtil.getYear(new Date())),
				agent.getAreaId());
		mav.addObject("reports", reportlist);
		return mav;
	}

	@RequestMapping(value = "/billlist")
	public String billlist(Long id, ModelMap modelMap,
			@ModelAttribute("page") Integer page,
			@ModelAttribute("rows") Integer rows, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("batchId", id);
		List<PaymentBillBo> billlist = agentService.findBillByBatchId(id, map,
				page, rows);
		Long total = agentService.findBillTotalBatchId(id, map);
		modelMap.addAttribute("bills", billlist);
		modelMap.addAttribute("total", total);
		return BILLLIST_PAGE;
	}

	@RequestMapping(value = "/index")
	public String index(ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response) {
		return INDEX_PAGE;
	}

	@RequestMapping(value = "/logout")
	public String syslogout(HttpServletRequest request,
			HttpServletResponse response) {
		request.getSession().removeAttribute(SystemConst.AGENT);
		request.getSession().invalidate();
		return LOGIN_PAGE;
	}

	@RequestMapping(value = "/list")
	public ModelAndView list(@ModelAttribute("page") Integer page,
			@ModelAttribute("rows") Integer rows, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		List<AgentBo> agentList = agentService.getAllAgent(null, page, rows);
		Long total = agentService.getTotalAgentByParam(null);
		mav.addObject("agentList", agentList);
		mav.addObject("total", total);
		mav.setViewName(LIST_PAGE);
		return mav;
	}

	@RequestMapping(value = "/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id,
			HttpServletRequest request, HttpServletResponse response) {
		AgentBo agent = agentService.getAgentBo(id);
		BankAccount bankAccount = agentService.getBankAccount(id);
		ModelAndView mav = new ModelAndView(ADD_PAGE);
		mav.addObject("agent", agent);
		mav.addObject("bankAcc", bankAccount);
		initAreas(request, mav.getModelMap());
		initBankInfo(request, mav.getModelMap());
		/*
		 * String[] codePaths =
		 * agent.getAreaPath().split(SystemConst.SPLITE_SIGN_STATIC); List<Area>
		 * areaList = null; if(codePaths.length==2){ areaList =
		 * areaService.getAreaByParentId(codePaths[0].trim());
		 * mav.addObject("secarea", areaList); mav.addObject("secAreaId",
		 * codePaths[0].trim()); } if(codePaths.length==3){ areaList =
		 * areaService.getAreaByParentId(codePaths[0].trim());
		 * mav.addObject("secarea", areaList); areaList =
		 * areaService.getAreaByParentId(codePaths[1].trim());
		 * mav.addObject("thirarea", areaList); mav.addObject("secAreaId",
		 * codePaths[0].trim()); mav.addObject("thirAreaId",
		 * codePaths[1].trim()); }
		 */
		return mav;
	}

	@RequestMapping(value = "/del")
	@ResponseBody
	public Object del(String ids, HttpServletRequest request,
			HttpServletResponse response) {
		agentService
				.deleteByAgentIds(ids.split(SystemConst.SPLITE_SIGN_COMMON));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", true);
		map.put("message", RequestUtils.getMessage("delete", request));
		return map;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addOrUpdate(AgentInfo agent, BankAccount bankAccount,
			Long accId, HttpServletRequest request, HttpServletResponse response) {
		bankAccount.setId(accId);
		if (agent.getId() == null) {
			agent.setPasswd(MD5.getMD5Str(SystemConst.INITPASSWORD));
			agent.setDelFlag(false);
			agentService.saveAgent(agent, bankAccount);
		} else {
			AgentInfo agentinfo = agentService.getAgent(agent.getId());
			String password = agentinfo.getPasswd();
			double dot = agentinfo.getRebackDot();
			BeanUtils.copyProperties(agent, agentinfo);
			agentinfo.setPasswd(password);
			agentinfo.setDelFlag(false);
			agentinfo.setRebackDot(dot);
			agentService.updateAgent(agentinfo, bankAccount);
		}

		return REDIRECT;
	}

	@RequestMapping(value = "/add")
	public String add(ModelMap modelMap, SystemManager sysMan,
			HttpServletRequest request, HttpServletResponse response) {
		initAreas(request, modelMap);
		initBankInfo(request, modelMap);
		return ADD_PAGE;
	}

	@RequestMapping(value = "/passwd")
	public String showmodifyPass(SystemManager sysMan,
			HttpServletRequest request, HttpServletResponse response) {
		return PASSWORD_PAGE;
	}

	@RequestMapping(value = "/modpass")
	@ResponseBody
	public Object modifyPass(@RequestParam("oldpasswd") String oldPass,
			@RequestParam("passwd") String newPass,
			@RequestParam("confirmPassword") String confirePass,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		AgentBo agent = (AgentBo) request.getSession().getAttribute(
				SystemConst.AGENT);
		if (agent != null) {
			if (!agent.getPasswd().equalsIgnoreCase(MD5.getMD5Str(oldPass))) {
				map.put("result", false);
				map.put("message",
						RequestUtils.getMessage("oldpasserror", request));
				return map;
			}
			if (!newPass.equalsIgnoreCase(confirePass)) {
				map.put("result", false);
				map.put("message",
						RequestUtils.getMessage("conpasserror", request));
				return map;
			}
			agentService.updatePassById(agent.getId(),
					MD5.getMD5Str(confirePass));
			map.put("result", true);
			map.put("message", RequestUtils.getMessage("modpass", request));
			return map;
		}
		map.put("result", false);
		map.put("message", RequestUtils.getMessage("nomodpass", request));
		return map;
	}

	@RequestMapping(value = "/checkreg")
	@ResponseBody
	public Object checkreg(String loginCode, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", false);
		Long num = agentService.findAgentByLoginName(loginCode);
		if (num > 0) {
			map.put("result", true);
		}
		return map;
	}

	@RequestMapping(value = "/getarea")
	@ResponseBody
	public Object getArea(String id, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		List<Area> areas = areaService.getAreasByCity(id);
		return areas;
	}

	@SuppressWarnings("unused")
	private void initAreas(HttpServletRequest request, ModelMap modelMap) {
		List<Area> areas = areaService.getAreasByCity(SystemConst.ROOTAREAID);
		modelMap.addAttribute("areas", areas);
	}

	@SuppressWarnings("unused")
	private void initBankInfo(HttpServletRequest request, ModelMap modelMap) {
		List<BankInfo> banks = staticService.listBanks();
		modelMap.addAttribute("banks", banks);
	}

	@RequestMapping(value = "/gettest")
	public String test(String id, HttpServletRequest request,
			HttpServletResponse response) {
		String str = null;

		System.out.println(str.length());

		return null;
	}

	@RequestMapping(value = "/queryUserChart")
	public ModelAndView queryUserChart(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		AgentBo agent = (AgentBo) request.getSession().getAttribute(
				SystemConst.AGENT);
		Date date = new Date();
		String currentYearMonth = DateUtil.getYearMonthNowString(date);
		String year = String.valueOf(DateUtil.getYear(date));
		Map<String, Object> monthMap = agentService.findUserByAreaId(year,
				agent.getAreaId());
		Long monthNum = (Long) monthMap.get(currentYearMonth);
		Long totalNum = (Long) monthMap.get("total");
		PieChart chart = new PieChart();
		Slice s1 = new Slice(monthNum, RequestUtils.getMessage("addusernumber",
				request));
		chart.addSlices(s1);
		Slice s2 = new Slice(totalNum, RequestUtils.getMessage("addusertotal",
				request));
		chart.addSlices(s2);

		chart.setColours(new String[] { "#E5CE0E", "#DC69AA" });
		chart.setTooltip("#label#：#val#");

		chart.setRadius(90);
		// chart.setStartAngle(100);
		chart.setAnimate(true);
		chart.setAlpha(0.8f);
		Chart flashChart = new Chart();
		String bgColor = "#ffffff";
		flashChart.setBackgroundColour(bgColor);
		flashChart.addElements(chart);
		response.setHeader("Expires", "-1");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-control", "no-cache");
		response.setHeader("Content-Type", "text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(flashChart.toString());
		return null;

	}

	@RequestMapping(value = "/monthBillChart")
	public ModelAndView monthBillChart(String year, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Long complateNum = 0L;
		Long noComplateNum = 0L;
		String currentYearMonth = DateUtil.getYearMonthNowString(new Date());
		AgentBo agent = (AgentBo) request.getSession().getAttribute(
				SystemConst.AGENT);
		List billlist = agentService.findBillNumByMonth(agent.getId(),
				currentYearMonth);
		for (Object o : billlist) {
			Integer status = (Integer) o;
			if (status == COMPLATESTATUS) {
				complateNum++;
				continue;
			}
			noComplateNum++;
		}
		PieChart chart = new PieChart();
		Slice s1 = new Slice(noComplateNum, RequestUtils.getMessage(
				"billnocomplate", request));
		chart.addSlices(s1);
		Slice s2 = new Slice(complateNum, RequestUtils.getMessage(
				"billcomplate", request));
		chart.addSlices(s2);

		chart.setColours(new String[] { "#B6A2DF", "#2DC7C9" });
		chart.setTooltip("#label#：#val#");

		chart.setRadius(90);
		// chart.setStartAngle(100);
		chart.setAnimate(true);
		chart.setAlpha(0.8f);
		Chart flashChart = new Chart();
		String bgColor = "#ffffff";
		flashChart.setBackgroundColour(bgColor);
		flashChart.addElements(chart);
		response.setHeader("Expires", "-1");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-control", "no-cache");
		response.setHeader("Content-Type", "text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(flashChart.toString());
		return null;

	}

	@RequestMapping(value = "/monthSettleChart")
	public ModelAndView monthSettleChart(String year,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String currentYearMonth = DateUtil.getYearMonthNowString(new Date());
		AgentBo agent = (AgentBo) request.getSession().getAttribute(
				SystemConst.AGENT);
		List<Object[]> settlelist = agentService.findBillSettleByMonth(
				agent.getId(), currentYearMonth);
		PieChart chart = new PieChart();
		for (Object[] o : settlelist) {
			Slice slice = new Slice((Double) o[0], RequestUtils.getMessage(
					(String) o[1], request));
			chart.addSlices(slice);
		}
		chart.setColours(new String[] { "#B6A2DF", "#2DC7C9" });
		chart.setTooltip("#label#：#val#");

		chart.setRadius(90);
		// chart.setStartAngle(100);
		chart.setAnimate(true);
		chart.setAlpha(0.8f);
		Chart flashChart = new Chart();
		String bgColor = "#ffffff";
		flashChart.setBackgroundColour(bgColor);
		flashChart.addElements(chart);
		response.setHeader("Expires", "-1");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-control", "no-cache");
		response.setHeader("Content-Type", "text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(flashChart.toString());
		return null;

	}

	@RequestMapping(value = "/report")
	public ModelAndView report(String year, HttpServletRequest request,
			HttpServletResponse response) {
		AgentBo agent = (AgentBo) request.getSession().getAttribute(
				SystemConst.AGENT);
		List<CountReportBo> reportlist = agentService.findReportByAgentId(
				agent.getId(), year, agent.getAreaId());
		ModelAndView mav = new ModelAndView("agent/report");
		mav.addObject("reports", reportlist);
		return mav;

	}

	@RequestMapping(value = "/asyncReport")
	@ResponseBody
	public Object asyncReport(String year, HttpServletRequest request,
			HttpServletResponse response){
		AgentBo agent = (AgentBo) request.getSession().getAttribute(
				SystemConst.AGENT);
		List<CountReportBo> reportlist = agentService.findReportByAgentId(
				agent.getId(), year, agent.getAreaId());
		return reportlist;

	}

	@RequestMapping(value = "/self")
	@ResponseBody
	public Object doself(Integer page, Integer rows,
			HttpServletRequest request, HttpServletResponse response){
		AgentBo agent = (AgentBo) request.getSession().getAttribute(
				SystemConst.AGENT);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", 0);
		List selflist = agentService.findAgentSelf(agent.getId(), map, page,
				rows);
		return selflist;

	}

	@RequestMapping(value = "/worklist")
	public String worklist(Integer status,
			@ModelAttribute("page") Integer page,
			@ModelAttribute("rows") Integer rows, ModelMap modelMap,
			HttpServletRequest request, HttpServletResponse response){
		AgentBo agent = (AgentBo) request.getSession().getAttribute(
				SystemConst.AGENT);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", status);
		List selflist = agentService.findAgentSelf(agent.getId(), map, page,
				rows);
		Long total = agentService.findAgentSelfTotal(agent.getId(), map);
		modelMap.addAttribute("works", selflist);
		modelMap.addAttribute("total", total);
		if (status != null && status == 0)
			return SELFLIST_PAGE;
		if (status != null && status == 1)
			return NOWLIST_PAGE;
		return SELFLIST_PAGE;

	}

	@RequestMapping(value = "/statuslist")
	public String statuslist(Long id, ModelMap modelMap,
			HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		List<PaymentBillBo> billlist = null;
		Long batchId = null;
		if (id != null) {
			map.put("statusFlag", 1);
			map.put("batchId", id);
			map.put("billId", id);
			map.put("paymentStatus", 3);
			billlist = agentService.findBillByBatchId(id, map, 0, 0);
			if(billlist!=null&&billlist.size()>0)
			    batchId = ((PaymentBillBo)billlist.get(0)).getBatchId();
		}
		modelMap.addAttribute("bills", billlist);
		modelMap.addAttribute("batchId", batchId);
		modelMap.addAttribute("id", id);
		return STATUSLIST_PAGE;
	}
	
	@RequestMapping(value = "/complate")
	@ResponseBody
	public Object  doComplate(String[] chkSel, Long batchId,RedirectAttributes redirectAttributes,
			HttpServletRequest request, HttpServletResponse response){
		agentService.updateBillStatusByIds(Arrays.asList(chkSel), 10);
		agentService.updateStatusByBatchId(batchId, 1);
		//ModelAndView mav = new ModelAndView(REDIRECTSTATIS);
		//redirectAttributes.addAttribute("id", batchId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", true);
		return map;
	}
	
	@RequestMapping(value = "/notice")
	@ResponseBody
	public Object  doNotice(Long billId, NoticeInfo notice,RedirectAttributes redirectAttributes,
			HttpServletRequest request, HttpServletResponse response){
		notice.setCreateTime(new Date());
		notice.setSendStatus(0);
		agentService.saveNotice(notice);
		agentService.updateBillStatusByIds(Arrays.asList(new String[]{billId+""}), 4);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", true);
		//ModelAndView mav = new ModelAndView(REDIRECTSTATIS);
		//redirectAttributes.addAttribute("id", billId);
		return map;
	}
	
	@RequestMapping("/downbatch")   
    public ModelAndView exportExcel(Long id,HttpServletRequest request, HttpServletResponse response)   
            throws Exception {  
        String dirPath = RequestUtils.getContextDirectory(SystemConst.CONTEXT_ATTACHEDIR, request);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("batchId", id);
		List<PaymentBillBo> billlist = agentService.findBillByBatchId(id, map,0, 0);
		String[] title1 = new String[] { "缴费单号", "缴费时间", "应缴金额", "实缴金额","缴费用户", "收费单位", "缴费类型"};
		List headers = new ArrayList();
		headers.add(title1);
		List datas = new ArrayList();
		for (PaymentBillBo bill:billlist) {
			String[] data = new String[] { bill.getId()+"",DateUtil.getDateTimeNow(bill.getCreateTime()),CurrencyConverter.getMoney(bill.getPayMoney())+"",CurrencyConverter.getMoney(bill.getPaidMoney())+"",
					bill.getUserName(),bill.getEntName(), bill.getPaymentTypeName()+"费"};
			datas.add(data);
		}
		Map<String, Integer> mergeColMap = new HashMap();
		Map<String, Integer> mergeRowMap = new HashMap();
		List<String> bottoms = new ArrayList();
		bottoms.add("批次单号:"+id);
		XLSReport report = new XLSReport();
		String fileName = DateUtil.getTimeNowString(new Date())+"_bill";
		File repFile = report.generating(datas, headers, mergeColMap,
				mergeRowMap, dirPath+"/",fileName,new int[]{20,20,20,20,20,20,20,20,20,20,20,20,20},bottoms,20,20,true,true);
        response.setContentType("text/html;charset=utf-8");   
        request.setCharacterEncoding("UTF-8");   
        BufferedInputStream bis = null;   
        BufferedOutputStream bos = null;      
        File file =   new File( dirPath + "/" + fileName+".xls");   
        try {   
            long fileLength = file.length();   
            response.setContentType("application/x-msdownload;");   
            response.setHeader("Content-disposition", "attachment; filename=" + java.net.URLEncoder.encode("缴费单信息.xls", "UTF-8"));   
            response.setHeader("Content-Length", String.valueOf(fileLength));   
            bis = new BufferedInputStream(new FileInputStream(file));   
            bos = new BufferedOutputStream(response.getOutputStream());   
            byte[] buff = new byte[bis.available()];   
            int bytesRead;   
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {   
                bos.write(buff, 0, bytesRead);   
            }   
        } catch (Exception e) {   
            e.printStackTrace();   
        } finally {   
            if (bis != null){ 
                bis.close();   
            }
            if (bos != null){
                bos.close();   
            }
        }   
        file.delete();
        return null;   
    }  
}

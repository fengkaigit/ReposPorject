package com.ey.controller.jf;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jofc2.model.Chart;
import jofc2.model.axis.XAxis;
import jofc2.model.axis.YAxis;
import jofc2.model.elements.BarChart;
import jofc2.model.elements.PieChart;
import jofc2.model.elements.BarChart.Bar;
import jofc2.model.elements.PieChart.Slice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ey.bo.QueryBillBO;
import com.ey.consts.SystemConst;
import com.ey.controller.base.BaseController;
import com.ey.dao.entity.BaseCustomValue;
import com.ey.dao.entity.ChargeEnterprise;
import com.ey.dao.entity.PaymentSetting;
import com.ey.dao.entity.UserBase;
import com.ey.forms.JfForm;
import com.ey.service.ChargeEntService;
import com.ey.service.JfService;
import com.ey.service.SettingService;
import com.ey.service.StaticService;
import com.ey.util.DateUtil;
import com.ey.util.FileUtil;
import com.ey.util.JacksonJsonUtil;
import com.ey.util.StringUtil;
import com.ey.util.WordReport;

@Controller
@RequestMapping(value = "/jf")
public class QueryController extends BaseController {
	@Autowired
	SettingService settingService;
	@Autowired
	JfService jfService;
	@Autowired
	private StaticService staticService;
	@Autowired
	private ChargeEntService chargeEntService;

	@RequestMapping(value = "/query")
	public ModelAndView query(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		List<QueryBillBO> records = queryBills(mav, request, response);
		request.getSession().setAttribute("querybarchartdata", records);
		Double money = 0d;// 金额
		Double sucessMoney = 0d;
		Double faultMoney = 0d;
		Integer totalNum = 0;
		Integer sucessNum = 0;
		Integer faultNum = 0;
		for (QueryBillBO record : records) {
			money = money + record.getMoney();
			sucessMoney = sucessMoney + record.getSucessMoney();
			faultMoney = faultMoney + record.getFaultMoney();
			totalNum = totalNum + record.getTotalNum();
			sucessNum = sucessNum + record.getSucessNum();
			faultNum = faultNum + record.getFaultNum();
		}

		List<Integer> monthes = new ArrayList();
		for (int i = 1; i <= 12; i++) {
			monthes.add(i);
		}
		mav.addObject("monthes", monthes);

		mav.addObject("money", money);
		mav.addObject("sucessMoney", sucessMoney);
		mav.addObject("faultMoney", faultMoney);
		mav.addObject("totalNum", totalNum);
		mav.addObject("sucessNum", sucessNum);
		mav.addObject("faultNum", faultNum);
		mav.addObject("totalList", records);
		mav.setViewName("jf/query/query");
		return mav;
	}

	@RequestMapping(value = "/queryDetail")
	public ModelAndView queryDetail(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		UserBase currentUser = (UserBase) request.getSession().getAttribute(
				SystemConst.USER);
		List<QueryBillBO> details = jfService.getDetails(currentUser.getId(),
				new Integer(year), new Integer(month));
		request.getSession().setAttribute("queryareachartdata", details);
		mav.addObject("details", details);
		mav.setViewName("jf/query/detail");
		return mav;
	}

	@RequestMapping(value = "/queryBarchart")
	public ModelAndView queryBarchart(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		List<QueryBillBO> records = (List<QueryBillBO>) request.getSession()
				.getAttribute("querybarchartdata");
		if (records == null) {
			records = queryBills(null, request, response);
			request.getSession().setAttribute("querybarchartdata", records);
		}

		BarChart chart = new BarChart(BarChart.Style.GLASS);
		// FilledBarChart chart = new FilledBarChart("red","blue");
		// String sMax = "10000" ;
		chart.setColour("#ffffff");
		// chart.setAlpha(0.5f);
		XAxis x = new XAxis();
		Double maxValue = new Double(0.00);
		Double minValue = new Double(0.00);
		for (QueryBillBO record : records) {
			x.addLabels(record.getMonth() + "月");
			Bar bar = new Bar(record.getMoney(), "元 ");
			bar.setColour("#46b4c5");
			bar.setTooltip(record.getMonth() + "月份缴费内容：" + record.getItem()
					+ "<br>缴费额：<strong>" + record.getMoney() + "</strong>元 "); // 鼠标移动上去后的提示
			chart.addBars(bar);
			Double temp = record.getMoney();
			if (maxValue.compareTo(temp) < 0) {
				maxValue = temp;
			}
			if (minValue.compareTo(temp) > 0) {
				minValue = temp;
			}

		}
		chart.setAlpha(0.8f);
		Chart flashChart = new Chart("", "{color: #ffffff;font-size: 14px;}");
		String bgColor = "#ffffff";
		flashChart.setBackgroundColour(bgColor);
		flashChart.addElements(chart);

		YAxis y = new YAxis();
		Integer yMax = new Integer(1);
		Integer yStep = new Integer(1);
		if (maxValue < 1) {
			yMax = new Integer(1);
			yStep = new Integer(1);
		} else if (maxValue < 10) {
			yMax = new Integer(10);
			yStep = new Integer(1);
		} else {
			yMax = new Integer(maxValue.intValue() + 10);
			yStep = new Integer(maxValue.intValue() / 10);
		}
		if (!minValue.isInfinite() && !minValue.isNaN()
				&& minValue.doubleValue() < 0.00) {
			y.setMin(new Integer(minValue.intValue()));
		}

		y.setMax(yMax);

		y.setSteps(yStep);

		flashChart.setYAxis(y);

		flashChart.setXAxis(x);
		response.setHeader("Expires", "-1");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-control", "no-cache");
		response.setHeader("Content-Type", "text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(flashChart.toString());
		return null;
	}

	@RequestMapping(value = "/queryAreachart")
	public ModelAndView queryAreachart(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		List<QueryBillBO> records = (List<QueryBillBO>) request.getSession()
				.getAttribute("queryareachartdata");
		PieChart chart = new PieChart();
		for (QueryBillBO record : records) {
			Slice s = new Slice(record.getMoney() + record.getSucessMoney(),
					record.getPayTypeStr() + "");
			// s.setTip(record.getPayType()+":#val#/#total#（ #percent#）");
			chart.addSlices(s);
		}
		chart.setColours(new String[] { "#77CC6D", "#FF5973", "#6D86CC",
				"#ED9A18", "#9018ED", "#00DDEE", "#4B5B6B", "#F5BBDD",
				"#686E08", "#C77248", "#3333CC", "#D90022", "#EEB644" });
		chart.setTooltip("#label#：#val#/#total#（ #percent#）");

		// chart.setRadius(20);
		// chart.setStartAngle(100);
		chart.setAnimate(true);
		chart.setAlpha(0.8f);
		Chart flashChart = new Chart("本月缴费分析图", "font-size: 14px;");
		String bgColor = "#FAFAFA";
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

	private List<QueryBillBO> queryBills(ModelAndView mav,
			HttpServletRequest request, HttpServletResponse response) {
		UserBase currentUser = (UserBase) request.getSession().getAttribute(
				SystemConst.USER);
		Date date = new Date();
		int curYear = DateUtil.getYear(date);
		int curMonth = DateUtil.getMonth(date);
		String year = request.getParameter("year");
		String startMonth = request.getParameter("startMonth");
		String endMonth = request.getParameter("endMonth");
		if (StringUtil.isEmptyString(year)) {
			year = curYear + "";
		}
		if (StringUtil.isEmptyString(startMonth)) {
			startMonth = "1";
		}
		if (StringUtil.isEmptyString(endMonth)) {
			endMonth = curMonth + "";
		}
		List<QueryBillBO> records = jfService.getTotalRecords(currentUser
				.getId(), new Integer(year), startMonth, endMonth);
		if (mav != null) {
			mav.addObject("year", curYear);
			mav.addObject("startMonth", startMonth);
			mav.addObject("endMonth", endMonth);
		}

		return records;
	}

	@RequestMapping(value = "/autoComplete")
	public ModelAndView autoComplete(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		response.setHeader("Expires", "-1");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-control", "no-cache");
		response.setHeader("Content-Type", "text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		// System.out.println("name1:==="+request.getParameter("query"));
		String paymentType = request.getParameter("paymentType");//
		String name = request.getParameter("query");//
		System.out.println("name=" + name);
		if (name != null && (!name.trim().equals(""))) {
			name = new String(name.getBytes("iso-8859-1"), "utf-8");
			System.out.println("name2=" + name);
		}
		UserBase currentUser = (UserBase) request.getSession().getAttribute(
				SystemConst.USER);
		List<PaymentSetting> settings = settingService.getSettingByBillNumber(
				name, new Integer(paymentType), currentUser.getId());
		for (PaymentSetting setting : settings) {
			out.println(setting.getBillNumber() + "\t" + "N");
			// System.out.println(key.getName() + "\t" + "N");
		}
		out.flush();
		out.close();
		return null;
	}

	@RequestMapping(value = "/jqautoComplete")
	public ModelAndView jqautoComplete(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// response.setHeader("Expires","-1");
		// response.setHeader("Pragma","no-cache");
		// response.setHeader("Cache-control","no-cache");
		// response.setHeader("Content-Type", "text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		// System.out.println("name1:==="+request.getParameter("query"));
		String paymentType = request.getParameter("paymentType");//
		String name = request.getParameter("q");//
		System.out.println("name=" + name);
		if (name != null && (!name.trim().equals(""))) {
			name = new String(name.getBytes("iso-8859-1"), "utf-8");
			System.out.println("name2=" + name);
		}
		UserBase currentUser = (UserBase) request.getSession().getAttribute(
				SystemConst.USER);
		List<PaymentSetting> settings = settingService.getSettingByBillNumber(
				name, new Integer(paymentType), currentUser.getId());
		// for(PaymentSetting setting:settings){
		// out.println(setting.getBillNumber() + "\t" + "N");
		// System.out.println(key.getName() + "\t" + "N");
		// }
		String json = JacksonJsonUtil.beanToJson(settings);
		//System.out.println(json);
		out.println(json);
		out.flush();
		out.close();
		return null;
	}

	@RequestMapping(value = "/expWord")
	public ModelAndView expWord(HttpServletRequest request,
			HttpServletResponse response) {
		String payType = request.getParameter("payType");
		int type = new Integer(payType).intValue();
		String sessionKey = StringUtil.getSessionKey(type);
		if (sessionKey == null) {
			return null;
		}
		JfForm form = (JfForm) request.getSession().getAttribute(sessionKey);
		if (form == null) {
			return null;
		}
		List<BaseCustomValue> paymentTypes = staticService
				.listValues("payment_type");
		String label = staticService.getLabel(paymentTypes, type);
		if (label == null) {
			return null;
		}
		UserBase currentUser = (UserBase) request.getSession().getAttribute(
				SystemConst.USER);
		String path = System.getProperty("java.io.tmpdir");
		String date = DateUtil.getDateTime("yyyyMMddHHmmss", new Date());
		String fileName = date + currentUser.getId() + ".doc";
		String fileNameDoc = path + "/" + fileName;
		File file = new File(fileNameDoc);
		if (file.exists()) {
			try {
				file.delete();
			} catch (Exception ex) {

			}
		}
		Map<String, Object> mainMap = new HashMap();
		mainMap.put("billNo", form.getBillNo());
		mainMap.put("flowNO", form.getBillId());
		mainMap.put("money", (form.getBillMoney() + form.getPoundage()) + "");
		mainMap.put("payType", label + "费");
		mainMap.put("endName", form.getEndName());
		mainMap.put("billNumber", form.getBillNumber());
		mainMap.put("payAddress", form.getPayAddress());
		mainMap.put("zq", form.getYear() + "年" + form.getMonth() + "月");
		mainMap.put("billMoney", form.getBillMoney());
		mainMap.put("poundage", form.getPoundage());
		mainMap.put("moneycn", form.getMoneycn());
		mainMap.put("bankName", form.getBankName());
		mainMap.put("bankAccount", form.getBankAccount());
		mainMap.put("payReason", "");
		List<Map<String, Object>> detail = null;
		List<List<Map<String, Object>>> details = null;
		String moban = "/jf_model.xml";
		if (type == 4) {
			moban = "/sj_model.xml";
			detail = new ArrayList();
			details = new ArrayList();
			String[] mobiles = form.getMobiles();
			double[] bms = form.getBillMoneys();
			for (int i = 0; i < mobiles.length; i++) {
				Map<String, Object> dm = new HashMap();
				dm.put("mobile", mobiles[i]);
				dm.put("billMoney", bms[i]);
				detail.add(dm);
			}
			details.add(detail);
		}
		if (type == 5) {
			moban = "/jt_model.xml";
		}
		InputStream in = this.getClass().getResourceAsStream(moban);
		try {
			String content = WordReport.buildWordReport(in, mainMap, details);
			FileUtil.makeFile(content, fileNameDoc, "utf-8");
			in.close();

			File fileDoc = new File(fileNameDoc);
			if (fileDoc != null && fileDoc.exists()) {
				FileUtil.downLoadFile(fileDoc, response, fileName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/viewbillimg")
	public ModelAndView viewbillimg(HttpServletRequest request,
			HttpServletResponse response) throws IOException, URISyntaxException {
		String entId = request.getParameter("entId");
		String payType = request.getParameter("payType");
		String path = System.getProperty("java.io.tmpdir");
		String fileName = "billimg_" + entId + "_" + payType + ".jpg";
		String fileNameJpg = path + "/" + fileName;
		File file = new File(fileNameJpg);
		if (!file.exists()) {
			ChargeEnterprise cet = chargeEntService
					.getChargeEnterprise(new Long(entId));
			if (cet != null) {
				byte[] bytes = cet.getExPic();
				if (bytes != null && bytes.length > 0) {
					FileUtil.wirteStringToFile(bytes, file);
				} else {
					
					//URI url = new URI("http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/images/no_billImage.jpg");
					//file = new File(url);
					response.sendRedirect(request.getContextPath()+"/images/no_billImage.jpg");
					return null;
				}
			}
		}
		FileUtil.downLoadImg(file, response, fileName);
		return null;
	}
}

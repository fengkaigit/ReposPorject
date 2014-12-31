package com.ey.controller.feerule;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ey.bo.BaseRuleFeeBo;
import com.ey.consts.SystemConst;
import com.ey.controller.base.BaseController;
import com.ey.dao.entity.Area;
import com.ey.dao.entity.BaseCustomValue;
import com.ey.dao.entity.BaseRuleFee;
import com.ey.service.AreaService;
import com.ey.service.FeeService;
import com.ey.service.StaticService;
import com.ey.util.StringUtil;

@Controller
@RequestMapping(value = "/feerule")
public class RuleFeeController extends BaseController {
	@Autowired
	private FeeService feeService;
	@Autowired
	private StaticService staticService;
	@Autowired
	private AreaService areaService;

	@RequestMapping(value = "/list")
	public ModelAndView list(Integer paymentType, String areaId, String cityId,
			@ModelAttribute("page") Integer page,
			@ModelAttribute("rows") Integer rows, HttpServletRequest request,
			HttpServletResponse response) {
		List<BaseCustomValue> paymentTypes = staticService
				.listValues("payment_type");
		Long total = feeService.findRulesTotalByArea(paymentType, areaId,
				cityId);
		long totalPage = (total % rows == 0 ? total / rows : total / rows + 1);
		if (page.longValue() > totalPage) {
			page = new Integer(totalPage + "");
			request.setAttribute("page", page);
		}
		List<BaseRuleFeeBo> list = feeService.findRulesByArea(paymentType,
				areaId, cityId, page, rows);
		// if(list!=null&&list.size()>0){

		Map<Integer, String> map = new HashMap();
		for (BaseCustomValue pt : paymentTypes) {
			map.put(pt.getId().getDataValue(), pt.getPropChName());
		}
		for (BaseRuleFeeBo st : list) {
			st.setPaymentTypeName(map.get(st.getPaymentType()));
		}
		// }
		ModelAndView mav = new ModelAndView();
		mav.addObject("ruleList", list);
		mav.addObject("total", total);
		mav.addObject("areaId", areaId);
		mav.addObject("cityId", cityId);
		mav.addObject("paymentType", paymentType);
		mav.addObject("paymentTypes", paymentTypes);
		mav.addObject("areas", initAreas(SystemConst.ROOTAREAID, request));
		if (!StringUtil.isEmptyString(areaId)) {
			mav.addObject("childareas", initAreas(areaId, request));
		}
		mav.setViewName("feerule/list");
		return mav;
	}

	@RequestMapping(value = "/edit")
	public ModelAndView edit(Integer paymentType, String areaId,
			HttpServletRequest request, HttpServletResponse response) {

		List<BaseCustomValue> paymentTypes = staticService
				.listValues("payment_type");
		ModelAndView mav = new ModelAndView();
		mav.addObject("paymentTypes", paymentTypes);
		mav.addObject("areas", initAreas(SystemConst.ROOTAREAID, request));
		if (paymentType != null && !StringUtil.isEmptyString(areaId)) {
			BaseRuleFee baseRuleFee = feeService
					.getFeeRule(paymentType, areaId);
			Area city = areaService.getArea(areaId);
			mav.addObject("pareaId", city.getCity());
			mav.addObject("citys", initAreas(city.getCity(), request));
			mav.addObject("baseRuleFee", baseRuleFee);
		}
		mav.setViewName("feerule/form");
		return mav;
	}

	@RequestMapping(value = "/save")
	public ModelAndView save(HttpServletRequest request,
			HttpServletResponse response, BaseRuleFee ruleFee) {
		String message = "设置成功";
		try {
			feeService.saveFeeRule(ruleFee);
		} catch (Exception ex) {
			message = "设置失败";
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("message", message);
		mav.setViewName("feerule/result");
		return mav;
	}

	@RequestMapping(value = "/del")
	public ModelAndView del(Integer paymentType, String areaId,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String message = "数据删除失败";
		if (!StringUtil.isNullObject(paymentType)
				&& !StringUtil.isEmptyString(areaId)) {
			try {
				feeService.delFeeRules(paymentType, areaId);
				message = "数据已删";
			} catch (Exception ex) {
				message = "数据删除失败";
			}
		}
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print(message);
		out.close();
		return null;
	}

	private Object initAreas(String id, HttpServletRequest request) {
		List<Area> areas = areaService.getAreasByCity(id);
		return areas;
	}
}

package com.ey.controller.jf;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ey.consts.SystemConst;
import com.ey.controller.base.BaseController;
import com.ey.dao.entity.Area;
import com.ey.dao.entity.BaseCustomValue;
import com.ey.dao.entity.ChargeEnterprise;
import com.ey.dao.entity.PaymentSetting;
import com.ey.dao.entity.UserBase;
import com.ey.service.AreaService;
import com.ey.service.ChargeEntService;
import com.ey.service.SettingService;
import com.ey.service.StaticService;
import com.ey.util.StringUtil;

@Controller
@RequestMapping(value = "/setting")
public class SettingController extends BaseController {
	@Autowired
	private AreaService areaService;
	@Autowired
	private ChargeEntService chargeEntService;
	@Autowired
	SettingService settingService;
	@Autowired
	private StaticService staticService;

	@RequestMapping(value = "/list")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) {
		String paymentType = request.getParameter("paymentType");
		Integer payType = null;
		if (!StringUtil.isEmptyString(paymentType)) {
			payType = new Integer(paymentType);
		}
		UserBase currentUser = (UserBase) request.getSession().getAttribute(
				SystemConst.USER);
		List<PaymentSetting> list = settingService.list(currentUser.getId(),
				payType);
		ModelAndView mav = new ModelAndView();
		mav.addObject("paymentSettings", list);
		mav.setViewName("jf/setting/list");
		return mav;
	}

	@RequestMapping(value = "/editframe")
	public ModelAndView editframe(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("jf/setting/frame");
		return mav;
	}

	@RequestMapping(value = "/edit")
	public ModelAndView edit(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		String id = request.getParameter("id");
		String paymentType = request.getParameter("paymentType");
		PaymentSetting setting = null;
		if (!StringUtil.isEmptyString(id)) {
			setting = (PaymentSetting) settingService.getObjectById(
					PaymentSetting.class, new Long(id));
		}
		UserBase currentUser = (UserBase) request.getSession().getAttribute(
				SystemConst.USER);
		List<BaseCustomValue> paymentTypes = staticService
				.listValues("payment_type");
		int type = buildEditParams(mav, setting, paymentTypes,paymentType);
		if (!StringUtil.isEmptyString(paymentType)){
			type = new Integer(paymentType);
		}
		List<BaseCustomValue> groups = staticService
				.listValues("bill_group_type");
		List<Area> areaList = areaService.getAreasByCity("0");
		List<Area> cityList = null;
		List<ChargeEnterprise> charges = null;
		if (setting != null && setting.getId() != null) {
			Area city = areaService.getArea(setting.getAreaId().toString());
			cityList = areaService.getAreasByCity(city.getCity());
			charges = chargeEntService.getChargesByArea(setting.getAreaId()
					.toString(), type);
			mav.addObject("parent", city.getCity());
			mav.addObject("areaId", setting.getAreaId().toString());
		} else {
			if (StringUtil.isEmptyString(currentUser.getAreaId())) {
				if (areaList != null && areaList.size() > 0) {
					cityList = areaService.getAreasByCity(areaList.get(0)
							.getId());
					if (cityList != null && cityList.size() > 0) {
						charges = chargeEntService.getChargesByArea(cityList
								.get(0).getId(), type);
					}

				}
			} else {
				Area city = areaService.getArea(currentUser.getAreaId());
				cityList = areaService.getAreasByCity(city.getCity());
				charges = chargeEntService.getChargesByArea(currentUser
						.getAreaId(), type);
				mav.addObject("parent", city.getCity());
				mav.addObject("areaId", currentUser.getAreaId());
			}
		}

		if (areaList == null) {
			areaList = new ArrayList();
		}
		if (cityList == null) {
			cityList = new ArrayList();
		}
		if (charges == null) {
			charges = new ArrayList();
		}
		mav.addObject("paymentType", paymentType);
		mav.addObject("groups", groups);
		mav.addObject("paymentTypes", paymentTypes);
		mav.addObject("areaList", areaList);
		mav.addObject("cityList", cityList);
		mav.addObject("charges", charges);
		mav.setViewName("jf/setting/form");
		return mav;
	}

	private int buildEditParams(ModelAndView mav, PaymentSetting setting,
			List<BaseCustomValue> paymentTypes,String paymentType) {
		int type = 0;
		if (setting == null) {
			if (paymentTypes != null && paymentTypes.size() > 0) {
				type = paymentTypes.get(0).getId().getDataValue();
			}
			setting = new PaymentSetting();
			if(!StringUtil.isEmptyString(paymentType)){
				setting.setPaymentType(new Integer(paymentType));
			}
		} else {
			type = setting.getPaymentType();
		}
		mav.addObject("paymentSetting", setting);
		return type;
	}

	@RequestMapping(value = "/save")
	public ModelAndView save(HttpServletRequest request,
			HttpServletResponse response, PaymentSetting setting) {
		ModelAndView mav = new ModelAndView();
		String message = "设置成功";
		try {
			buildSetting(setting, request);
			settingService.saveSetting(setting);
		} catch (Exception ex) {
			message = "设置失败";
		}
		mav.addObject("message", message);
		mav.setViewName("jf/setting/result");
		return mav;
	}

	private void buildSetting(PaymentSetting setting, HttpServletRequest request) {
		UserBase currentUser = (UserBase) request.getSession().getAttribute(
				SystemConst.USER);
		setting.setUserId(currentUser.getId());
		setting.setAreaName(areaService.getArea(setting.getAreaId())
				.getProvince());
		setting.setEntName(chargeEntService.getChargeEnterprise(
				setting.getEntId()).getEnterpriseName());
		List<BaseCustomValue> paymentTypes = staticService
				.listValues("payment_type");
		List<BaseCustomValue> groups = staticService
				.listValues("bill_group_type");
		for (BaseCustomValue paymentType : paymentTypes) {
			if (paymentType.getId().getDataValue().intValue() == setting
					.getPaymentType().intValue()) {
				setting.setPaymentTypeName(paymentType.getPropChName());
				break;
			}
		}
		for (BaseCustomValue group : groups) {
			if (group.getId().getDataValue().intValue() == setting.getGroupId()
					.intValue()) {
				setting.setGroupName(group.getPropChName());
				break;
			}
		}
		if (setting.getCreateTime() == null) {
			setting.setCreateTime(new Date());
		}
		setting.setDelFlag(0);
	}

	@RequestMapping(value = "/del")
	public ModelAndView del(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		String message = "数据已删";
		if (!StringUtil.isEmptyString(id)) {
			try {
				settingService.delSetting(new Long(id));
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
	@RequestMapping(value = "/refreshEnt")
	public ModelAndView refreshEnt(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String areaId = request.getParameter("areaId");
		String paymentType = request.getParameter("paymentType");
		List<ChargeEnterprise> ents = chargeEntService.getChargesByArea(areaId, new Integer(paymentType));
		StringBuffer buffer = new StringBuffer("");
		for(ChargeEnterprise ent:ents){
			if(buffer.length()>0){
				buffer.append(";");
			}
			buffer.append(ent.getId()+","+ent.getEnterpriseName());
		}
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print(buffer.toString());
		out.close();
		return null;
	}
	
}

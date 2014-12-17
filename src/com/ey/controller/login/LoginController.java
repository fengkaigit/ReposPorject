package com.ey.controller.login;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ey.consts.SystemConst;
import com.ey.controller.base.BaseController;
import com.ey.dao.entity.Area;
import com.ey.dao.entity.BaseCustomValue;
import com.ey.dao.entity.UserBase;
import com.ey.forms.UserForm;
import com.ey.service.AreaService;
import com.ey.service.LoginService;
import com.ey.service.StaticService;
import com.ey.util.MD5;
import com.ey.util.RequestUtils;
import com.ey.util.StringUtil;
import com.ey.util.VerifyCodeUtil;

@Controller
public class LoginController extends BaseController {

	@Autowired
	private LoginService loginService;
	@Autowired
	private StaticService staticService;
	@Autowired
	private AreaService areaService;

	@RequestMapping(value = "/dologin")
	public ModelAndView login(HttpServletRequest request,
			HttpServletResponse response, UserForm form) {
		if (!StringUtil.isEmptyString(form.getForwardUrl())) {
			request.setAttribute("forwardUrl", form.getForwardUrl());
		}
		ModelAndView mav = new ModelAndView();
		if (StringUtil.isEmptyString(form.getLoginCode())) {
			mav.addObject("message", RequestUtils
					.getMessage("nologin", request));
			mav.setViewName("login/login");
			return mav;
		}
		if (StringUtil.isEmptyString(form.getPassword())) {
			mav.addObject("message", RequestUtils.getMessage("nopassword",
					request));
			mav.setViewName("login/login");
			return mav;
		}

		if (StringUtil.isEmptyString(form.getVerify())) {
			mav.addObject("message", RequestUtils.getMessage("noverify",
					request));
			mav.setViewName("login/login");
			return mav;
		} else {
			String validateCode = (String) request.getSession().getAttribute(
					"validateCode");
			if (!form.getVerify().equalsIgnoreCase(validateCode)) {
				mav.addObject("message", RequestUtils.getMessage(
						"invalidateverify", request));
				mav.setViewName("login/login");
				return mav;
			}
		}
		UserBase currentUser = loginService.findUserByLoginCode(form
				.getLoginCode(), MD5.getMD5Str(form.getPassword()));

		if (currentUser == null) {
			mav.addObject("message", RequestUtils.getMessage("login", request));
			mav.setViewName("login/login");
		} else {
			if (StringUtil.isEmptyString(form.getForwardUrl())) {
				mav.setViewName("redirect:/main.do");
			} else {
				String contextPath = request.getContextPath();
				if (contextPath != null && contextPath.length() > 1) {
					mav.setViewName("redirect:"
							+ form.getForwardUrl()
									.replaceFirst(contextPath, ""));
				} else {
					mav.setViewName("redirect:" + form.getForwardUrl());
				}

			}

			mav.addObject(SystemConst.USER, currentUser);
			request.getSession().setAttribute(SystemConst.USER, currentUser);
		}
		return mav;
	}

	@RequestMapping(value = "/getVerify")
	public ModelAndView getVerify(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		VerifyCodeUtil verify = new VerifyCodeUtil();
		StringBuffer randomCode = new StringBuffer("");
		BufferedImage bufferImg = verify.generateCode(randomCode);
		HttpSession session = request.getSession();
		session.setAttribute("validateCode", randomCode.toString());
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		ServletOutputStream sos = response.getOutputStream();
		ImageIO.write(bufferImg, "jpeg", sos);
		sos.close();
		return null;
	}

	@RequestMapping(value = "/reg")
	public ModelAndView reg(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		List<BaseCustomValue> values = staticService
				.listValues("person_account_type");
		List<Area> areas = areaService.getAreasByCity("0");
		List<Area> cityList = null;
		if (areas != null && areas.size() > 0) {
			cityList = areaService.getAreasByCity(areas.get(0).getId());
		}
		if (areas == null) {
			areas = new ArrayList();
		}
		if (cityList == null) {
			cityList = new ArrayList();
		}
		mav.addObject("cityList", cityList);
		mav.addObject(SystemConst.PERSON_ACCOUNT_TYPE, values);
		mav.addObject(SystemConst.AREA, areas);
		mav.setViewName("login/reg");
		return mav;
	}

	@RequestMapping(value = "/login")
	public ModelAndView gologin(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login/login");
		return mav;
	}

	@RequestMapping(value = "/doreg")
	public ModelAndView doreg(HttpServletRequest request,
			HttpServletResponse response, UserBase user) {
		String verify = request.getParameter("verify");

		ModelAndView mav = new ModelAndView();
		if (StringUtil.isEmptyString(user.getAccountNumber())) {
			mav.addObject("message", RequestUtils
					.getMessage("nologin", request));
			mav.setViewName("login/reg");
			return mav;
		}
		user.setRealName(user.getAccountNumber());
		user.setMobilePhone(user.getAccountNumber());
		/*
		 * if (StringUtil.isEmptyString(user.getRealName())) {
		 * mav.addObject("message", RequestUtils.getMessage("norealname",
		 * request)); mav.setViewName("login/reg"); return mav; }
		 */
		if (StringUtil.isEmptyString(user.getPasswd())) {
			mav.addObject("message", RequestUtils.getMessage("nopassword",
					request));
			mav.setViewName("login/reg");
			return mav;
		}

		if (StringUtil.isEmptyString(verify)) {
			mav.addObject("message", RequestUtils.getMessage("noverify",
					request));
			mav.setViewName("login/reg");
			return mav;
		} else {
			String validateCode = (String) request.getSession().getAttribute(
					"validateCode");
			if (!verify.equalsIgnoreCase(validateCode)) {
				mav.addObject("message", RequestUtils.getMessage(
						"invalidateverify", request));
				mav.setViewName("login/reg");
				return mav;
			}
		}
		// user.setPasswd(user.getPasswd());
		UserBase currentUser = loginService.findUserByLoginCode(user
				.getAccountNumber());
		if (currentUser != null) {
			mav.addObject("message", RequestUtils.getMessage("duplicateuser",
					request));
			mav.setViewName("login/reg");
		} else {
			try {
				user.setRegTime(new Date());
				loginService.saveUser(user);
				mav.setViewName("redirect:/main.do");
				mav.addObject(SystemConst.USER, user);
				request.getSession().setAttribute(SystemConst.USER, user);
			} catch (Exception ex) {
				mav.addObject("message", RequestUtils.getMessage(
						"registererror", request));
				mav.setViewName("login/reg");
				return mav;
			}
		}
		return mav;
	}

	@RequestMapping(value = "/logout")
	public ModelAndView logout(HttpServletRequest request,
			HttpServletResponse response) {
		request.getSession().removeAttribute(SystemConst.USER);
		request.getSession().invalidate();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/main.do");
		return mav;
	}

	@RequestMapping(value = "/checkreg")
	public ModelAndView checkreg(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String accountNumber = request.getParameter("accountNumber");
		UserBase user = loginService.findUserByLoginCode(accountNumber);
		String message = "此登录名可以注册!";
		if (user != null) {
			message = "此登录名已存在,请填写其它用户名!";
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

	@RequestMapping(value = "/refeshCity")
	public ModelAndView refeshCity(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String areaId = request.getParameter("areaId");
		List<Area> citys = areaService.getAreasByCity(areaId);
		StringBuffer buffer = new StringBuffer("");
		for (Area city : citys) {
			if (buffer.length() > 0) {
				buffer.append(";");
			}
			buffer.append(city.getId() + "," + city.getProvince());
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

	@RequestMapping(value = "/resetMobile")
	@ResponseBody
	public Object resetMobile(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String,String> map = new HashMap();
		String mobile = request.getParameter("newmobile");
		String result = null;
		if (StringUtil.isEmptyString(mobile)) {
			result = "请输入手机号码";
		} else {
			try {
				UserBase ub = (UserBase) request.getSession().getAttribute(
						SystemConst.USER);
				UserBase ubd = loginService.findUserByLoginCode(ub
						.getAccountNumber());
				ubd.setMobilePhone(mobile);
				loginService.saveUser(ubd);
				request.getSession().setAttribute(SystemConst.USER, ubd);
				result = "手机号码重设成功";
			} catch (Exception ex) {
				result = "手机号码重设失败";
			}
		}
		map.put("result", result);
		return map;
	}

	@RequestMapping(value = "/resetPassword")
	@ResponseBody
	public Object resetPassword(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String,String> map = new HashMap();
		String oldpass = request.getParameter("oldpass");
		String newepass1 = request.getParameter("newepass1");
		String newepass2 = request.getParameter("newepass2");
		String result = null;
		UserBase ub = (UserBase) request.getSession().getAttribute(
				SystemConst.USER);
		if (StringUtil.isEmptyString(oldpass)) {
			result = "请输入原密码";
		}
		if (StringUtil.isEmptyString(newepass1)) {
			result = "请输入新密码";
		}
		if (StringUtil.isEmptyString(newepass2)) {
			result = "请输入确认密码";
		}
		if (!newepass1.equals(newepass2)) {
			result = "两次密码不一致,请确认";
		}
		if (!oldpass.equals(ub.getPasswd())) {
			result = "原密码错误";
		}
		if (!StringUtil.isEmptyString(result)) {
			map.put("result", result);
			return map;
		}
		try {

			UserBase ubd = loginService.findUserByLoginCode(ub
					.getAccountNumber());
			ubd.setPasswd(newepass1);
			loginService.saveUser(ubd);
			request.getSession().setAttribute(SystemConst.USER, ubd);
			result = "ok";
		} catch (Exception ex) {
			result = "密码未能修改";
		}
		map.put("result", result);
		return map;
	}
}

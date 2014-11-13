package com.ey.controller.jf;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ey.consts.SystemConst;
import com.ey.controller.base.BaseController;
import com.ey.dao.entity.UserBase;
import com.ey.service.LoginService;
import com.ey.util.MD5;
import com.ey.util.RequestUtils;
import com.ey.util.StringUtil;

@Controller
@RequestMapping(value="/sf")
public class SfController extends BaseController {
	@RequestMapping(value = "/gosf")
	public ModelAndView gosf(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("jf/sf");
		return mav;
	}
	
}

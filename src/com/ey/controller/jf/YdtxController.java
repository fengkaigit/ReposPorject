package com.ey.controller.jf;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ey.controller.base.BaseController;

@Controller
@RequestMapping(value="/ydtx")
public class YdtxController extends BaseController {
	@RequestMapping(value = "/first")
	public ModelAndView first(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("jf/ydtx/first");
		return mav;
	}
	
}

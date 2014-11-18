package com.ey.controller.about;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ey.controller.base.BaseController;

@Controller
@RequestMapping(value="/ej")
public class AboutController extends BaseController {
	@RequestMapping(value = "/security")
	public ModelAndView security(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("about/security");
		return mav;
	}
	@RequestMapping(value = "/about")
	public ModelAndView about(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("about/about");
		return mav;
	}
	@RequestMapping(value = "/ieda")
	public ModelAndView ieda(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("ieda/ieda");
		return mav;
	}
	
}

package com.ey.controller.jf;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ey.controller.base.BaseController;

@Controller
@RequestMapping(value="/df")
public class DfController extends BaseController {
	@RequestMapping(value = "/first")
	public ModelAndView first(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("jf/df/first");
		return mav;
	}
	@RequestMapping(value = "/second")
	public ModelAndView second(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("jf/df/second");
		return mav;
	}
	@RequestMapping(value = "/third")
	public ModelAndView third(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("jf/df/third");
		return mav;
	}
	@RequestMapping(value = "/fourth")
	public ModelAndView fourth(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("jf/df/fourth");
		return mav;
	}
}

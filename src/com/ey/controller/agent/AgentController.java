package com.ey.controller.agent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ey.controller.base.BaseController;
import com.ey.service.AgentService;
import com.ey.service.SysManService;


@Controller
@RequestMapping(value="/agent")
public class AgentController extends BaseController {
   
	@Autowired
    private AgentService agentService;
	
	@RequestMapping(value="/list")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response){
		return null;
	}
}

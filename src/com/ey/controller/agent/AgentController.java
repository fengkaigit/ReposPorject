package com.ey.controller.agent;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ey.bo.AgentBo;
import com.ey.consts.SystemConst;
import com.ey.controller.base.BaseController;
import com.ey.dao.entity.AgentInfo;
import com.ey.dao.entity.SystemManager;
import com.ey.service.AgentService;
import com.ey.service.SysManService;
import com.ey.util.MD5;
import com.ey.util.RequestUtils;
import com.ey.util.StringUtil;


@Controller
@RequestMapping(value="/agent")
public class AgentController extends BaseController {
   
	private static final String LOGIN_PAGE="login/agentlogin";
	private static final String LIST_PAGE = "agent/list";
	private static final String REDIRECT = "redirect:/agent/list.do";
	private static final String ADD_PAGE = "agent/addagent";
	@Autowired
    private AgentService agentService;
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
    public ModelAndView login(String verify,String loginCode,String password,HttpServletRequest request,HttpServletResponse response){  	  
	  ModelAndView mav = new ModelAndView();
  	  if (StringUtil.isEmptyString(loginCode)) {
		mav.addObject("message", RequestUtils.getMessage("nologin",
				request));
		mav.setViewName(LOGIN_PAGE);
		return mav;
	  }
	  if (StringUtil.isEmptyString(password)) {
		mav.addObject("message", RequestUtils.getMessage("nopassword",
				request));
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
	  
		AgentBo agent = agentService.findAgentByLoginName(loginCode,MD5.getMD5Str(password));
  	  if(agent==null){
  		  mav.addObject("message", RequestUtils.getMessage("login", request));
  		  mav.setViewName(LOGIN_PAGE);
  	  }else{
  		  mav.setViewName(REDIRECT);
  		  mav.addObject(SystemConst.USER,agent);
  		  request.getSession().setAttribute(SystemConst.USER, agent);
  	  }
  	  return mav;
    }
	
	@RequestMapping(value="/login")
    public String login(HttpServletRequest request,HttpServletResponse response){
  	  return LOGIN_PAGE;
    }
	
	@RequestMapping(value="/logout")
    public String syslogout(HttpServletRequest request,HttpServletResponse response){
		request.getSession().removeAttribute(SystemConst.USER);
		request.getSession().invalidate();
		return LOGIN_PAGE;
    }
	
	@RequestMapping(value="/list")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		List<AgentBo> agentList = agentService.getAllAgent(null, 0, 0);
		mav.addObject("agentList", agentList);
		mav.setViewName("LIST_PAGE");
		return mav;
	}
	
	@RequestMapping(value="/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id,HttpServletRequest request,HttpServletResponse response){
	  AgentBo agent = agentService.getAgent(id);
	  ModelAndView mav = new ModelAndView(ADD_PAGE);
	  mav.addObject("agent", agent);
	  return mav;
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String addOrUpdate(AgentBo agent,HttpServletRequest request,HttpServletResponse response){
		if(agent.getId() == null){
			agent.setPasswd(MD5.getMD5Str(agent.getPasswd()));
			agentService.saveAgent(agent);
		}
		else{
			AgentBo agentbo = agentService.getAgent(agent.getId());
			String password = agentbo.getPasswd();
			BeanUtils.copyProperties(agent, agentbo);
			agentbo.setPasswd(password);
			agentService.updateAgent(agentbo);
		}
	    
	    return REDIRECT;
	}
	
	@RequestMapping(value="/add")
	public String add(SystemManager sysMan,HttpServletRequest request,HttpServletResponse response){
	  return ADD_PAGE;
	}
	
	@RequestMapping(value="/modpass")
	@ResponseBody
	public Object modifyPass(String oldPass,String newPass,String confirePass,HttpServletRequest request,HttpServletResponse response){
	  Map<String,Object> map = new HashMap<String,Object>();
	  AgentBo agent = (AgentBo)request.getSession().getAttribute(SystemConst.USER);
	  if(agent!=null){
		  if(!agent.getPasswd().equalsIgnoreCase(MD5.getMD5Str(oldPass))){
			  map.put("result",false);
			  map.put("message",RequestUtils.getMessage("oldpasserror", request));
			  return map;
		  }
		  if(!newPass.equalsIgnoreCase(confirePass)){
			  map.put("result",false);
			  map.put("message",RequestUtils.getMessage("conpasserror", request));
			  return map;
		  }
		  agentService.updatePassById(agent.getId(), MD5.getMD5Str(confirePass));
		  map.put("result",true);
		  map.put("message",RequestUtils.getMessage("modpass", request));
		  return map;
	  }
	  map.put("result",false);
	  map.put("message",RequestUtils.getMessage("nomodpass", request));
	  return map;
	}
	
	@RequestMapping(value = "/checkreg")
	@ResponseBody
	public Object checkreg(String loginCode,HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("result",false);
		Long num = agentService.findAgentByLoginName(loginCode);
		if(num>0){
			map.put("result",true);
		}
		return map;
	}
}

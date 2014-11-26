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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ey.bo.AgentBo;
import com.ey.consts.SystemConst;
import com.ey.controller.base.BaseController;
import com.ey.dao.entity.AgentInfo;
import com.ey.dao.entity.Area;
import com.ey.dao.entity.BankAccount;
import com.ey.dao.entity.BankInfo;
import com.ey.dao.entity.SystemManager;
import com.ey.exception.BusinessException;
import com.ey.service.AgentService;
import com.ey.service.AreaService;
import com.ey.service.StaticService;
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
	private static final String INDEX_PAGE = "agent/index";
	@Autowired
    private AgentService agentService;
	
	@Autowired
    private AreaService areaService;
	
	@Autowired
	private StaticService staticService;
	
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
  		  mav.setViewName(INDEX_PAGE);
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
		mav.setViewName(LIST_PAGE);
		return mav;
	}
	
	@RequestMapping(value="/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id,HttpServletRequest request,HttpServletResponse response){
	  AgentBo agent = agentService.getAgentBo(id);
	  BankAccount bankAccount = agentService.getBankAccount(id);
	  ModelAndView mav = new ModelAndView(ADD_PAGE);
	  mav.addObject("agent", agent);
	  mav.addObject("bankAcc", bankAccount);
	  initAreas(request,mav.getModelMap());
	  initBankInfo(request,mav.getModelMap());
	  /*String[] codePaths = agent.getAreaPath().split(SystemConst.SPLITE_SIGN_STATIC);
	  List<Area> areaList = null;
	  if(codePaths.length==2){
		  areaList = areaService.getAreaByParentId(codePaths[0].trim());
	      mav.addObject("secarea", areaList);
	      mav.addObject("secAreaId", codePaths[0].trim());
	  }
	  if(codePaths.length==3){
		  areaList = areaService.getAreaByParentId(codePaths[0].trim());
	      mav.addObject("secarea", areaList);
		  areaList = areaService.getAreaByParentId(codePaths[1].trim());
		  mav.addObject("thirarea", areaList);
		  mav.addObject("secAreaId", codePaths[0].trim());
		  mav.addObject("thirAreaId", codePaths[1].trim());
	  }*/
	  return mav;
	}
	
	@RequestMapping(value="/del")
	@ResponseBody
	public Object del(String ids,HttpServletRequest request,HttpServletResponse response){
		agentService.deleteByAgentIds(ids.split(SystemConst.SPLITE_SIGN_COMMON));
  	  Map<String,Object> map = new HashMap<String,Object>();
	  map.put("result",true);
	  map.put("message",RequestUtils.getMessage("delete", request));
	  return map;
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String addOrUpdate(AgentInfo agent,BankAccount bankAccount,Long accId,HttpServletRequest request,HttpServletResponse response){
		bankAccount.setId(accId);
		if(agent.getId() == null){
			agent.setPasswd(MD5.getMD5Str(SystemConst.INITPASSWORD));
			agent.setDelFlag(false);
			agentService.saveAgent(agent,bankAccount);
		}
		else{
			AgentInfo agentinfo = agentService.getAgent(agent.getId());
			String password = agentinfo.getPasswd();
			double dot = agentinfo.getRebackDot();
			BeanUtils.copyProperties(agent, agentinfo);
			agentinfo.setPasswd(password);
			agentinfo.setDelFlag(false);
			agentinfo.setRebackDot(dot);
			agentService.updateAgent(agentinfo,bankAccount);
		}
	    
	    return REDIRECT;
	}
	
	@RequestMapping(value="/add")
	public String add(ModelMap modelMap,SystemManager sysMan,HttpServletRequest request,HttpServletResponse response){
	  initAreas(request,modelMap);
	  initBankInfo(request,modelMap);
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
	
	@RequestMapping(value = "/getarea")
	@ResponseBody
	public Object getArea(String id,HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		List<Area> areas = areaService.getAreasByCity(id);
		return areas;
	}
	@SuppressWarnings("unused")
	private void initAreas(HttpServletRequest request,ModelMap modelMap){
		 List<Area> areas = areaService.getAreasByCity(SystemConst.ROOTAREAID);
		  modelMap.addAttribute("areas", areas);
	}
	@SuppressWarnings("unused")
	private void initBankInfo(HttpServletRequest request,ModelMap modelMap){
		 List<BankInfo> banks = staticService.listBanks();
		  modelMap.addAttribute("banks", banks);
	}
	
	@RequestMapping(value = "/gettest")
	public String test(String id,HttpServletRequest request,
			HttpServletResponse response){
		String str=null;
		
		System.out.println(str.length());
	   
		return null;
	}
}

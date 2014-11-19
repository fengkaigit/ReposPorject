package com.ey.controller.sysman;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ey.consts.SystemConst;
import com.ey.dao.entity.SystemManager;
import com.ey.dao.entity.UserBase;
import com.ey.entity.User;
import com.ey.service.SysManService;
import com.ey.util.MD5;
import com.ey.util.RequestUtils;
import com.ey.util.StringUtil;

@Controller
@RequestMapping(value="/sysman")
public class SysManController {
	
	@Autowired
    private SysManService sysManService;
	
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
    public ModelAndView sysLogin(String verify,String loginCode,String password,HttpServletRequest request,HttpServletResponse response){  	  
	  ModelAndView mav = new ModelAndView();
  	  if (StringUtil.isEmptyString(loginCode)) {
		mav.addObject("message", RequestUtils.getMessage("nologin",
				request));
		mav.setViewName("login/sysManLogin");
		return mav;
	  }
	  if (StringUtil.isEmptyString(password)) {
		mav.addObject("message", RequestUtils.getMessage("nopassword",
				request));
		mav.setViewName("login/sysManLogin");
		return mav;
	  }
		if (StringUtil.isEmptyString(verify)) {
			mav.addObject("message",
					RequestUtils.getMessage("noverify", request));
			mav.setViewName("login/sysManLogin");
			return mav;
		} else {
			String validateCode = (String) request.getSession().getAttribute(
					"validateCode");
			if (!verify.equalsIgnoreCase(validateCode)) {
				mav.addObject("message",
						RequestUtils.getMessage("invalidateverify", request));
				mav.setViewName("login/sysManLogin");
				return mav;
			}
		}
	  
	  SystemManager currentManager = sysManService.findManagerByLoginName(loginCode,MD5.getMD5Str(password));
  	  if(currentManager==null){
  		  mav.addObject("message", RequestUtils.getMessage("login", request));
  		  mav.setViewName("login/sysManLogin");
  	  }else{
  		  mav.setViewName("redirect:/sysman/list.do");
  		  mav.addObject(SystemConst.USER,currentManager);
  		  request.getSession().setAttribute(SystemConst.USER, currentManager);
  	  }
  	  return mav;
    }
	@RequestMapping(value="/login")
    public String sysLogin(HttpServletRequest request,HttpServletResponse response){
  	  return "login/sysManLogin";
    }
	
	@RequestMapping(value="/logout")
    public String syslogout(HttpServletRequest request,HttpServletResponse response){
		request.getSession().removeAttribute(SystemConst.USER);
		request.getSession().invalidate();
		return "login/sysManLogin";
    }
	
	@RequestMapping(value="/list")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		List<SystemManager> sysManList = sysManService.findManagers(null, 0, 0);
		mav.addObject("syslist", sysManList);
		mav.setViewName("sysman/index");
		return mav;
	}
	
	@RequestMapping(value="/del")
	@ResponseBody
	public Object del(String ids,HttpServletRequest request,HttpServletResponse response){
	  sysManService.deleteBySysManIds(ids.split(SystemConst.SPLITE_SIGN_COMMON));
  	  Map<String,Object> map = new HashMap<String,Object>();
	  map.put("result",true);
	  map.put("message",RequestUtils.getMessage("delete", request));
	  return map;
	}
	
	@RequestMapping(value="/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id,HttpServletRequest request,HttpServletResponse response){
	  SystemManager sysManager = sysManService.getSySManager(id);
	  ModelAndView mav = new ModelAndView("sysman/addsysman");
	  mav.addObject("sysMan", sysManager);
	  return mav;
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String addOrUpdate(SystemManager sysMan,HttpServletRequest request,HttpServletResponse response){
		if(sysMan.getId() == null)
			sysMan.setPasswd(MD5.getMD5Str(sysMan.getPasswd()));
		else{
			SystemManager sysManager = sysManService.getSySManager(sysMan.getId());
			sysMan.setPasswd(sysManager.getPasswd());
		}
	    sysManService.saveSysMan(sysMan);
	    return "redirect:/sysman/list.do";
	}
	
	@RequestMapping(value="/add")
	public String add(SystemManager sysMan,HttpServletRequest request,HttpServletResponse response){
	  return "sysman/addsysman";
	}
	
	@RequestMapping(value="/modpass")
	@ResponseBody
	public Object modifyPass(String oldPass,String newPass,String confirePass,HttpServletRequest request,HttpServletResponse response){
	  Map<String,Object> map = new HashMap<String,Object>();
	  SystemManager sysManObj = (SystemManager)request.getSession().getAttribute(SystemConst.USER);
	  if(sysManObj!=null){
		  if(!sysManObj.getPasswd().equalsIgnoreCase(MD5.getMD5Str(oldPass))){
			  map.put("result",false);
			  map.put("message",RequestUtils.getMessage("oldpasserror", request));
			  return map;
		  }
		  if(!newPass.equalsIgnoreCase(confirePass)){
			  map.put("result",false);
			  map.put("message",RequestUtils.getMessage("conpasserror", request));
			  return map;
		  }
		  sysManService.updatePassById(sysManObj.getId(), MD5.getMD5Str(confirePass));
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
		SystemManager manager = sysManService.findManagerByLoginName(loginCode);
		if(manager!=null){
			map.put("result",true);
		}
		return map;
	}

}

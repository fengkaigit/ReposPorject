package com.ey.controller.announce;

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
import com.ey.dao.entity.SysAnnouncement;
import com.ey.dao.entity.SystemManager;
import com.ey.service.AnnounceService;
import com.ey.service.AreaService;
import com.ey.util.MD5;
import com.ey.util.RequestUtils;

@Controller
@RequestMapping(value="/announce")
public class AnnounceController extends BaseController {
	
	private static final String LIST_PAGE = "announce/list";
	private static final String REDIRECT = "redirect:/announce/list.do";
	private static final String ADD_PAGE = "announce/announceform";
	
	@Autowired
    private AnnounceService announceService;
	
	@Autowired
    private AreaService areaService;
	
	@RequestMapping(value="/list")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		List<SysAnnouncement> announces = announceService.getAnnouncesByQueryParam(null, 0, 0);
		mav.addObject("announces", announces);
		mav.setViewName(LIST_PAGE);
		return mav;
	}
	
	
	@RequestMapping(value="/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id,HttpServletRequest request,HttpServletResponse response){
	  SysAnnouncement announce = announceService.getSysAnnouncement(id);
	  ModelAndView mav = new ModelAndView(ADD_PAGE);
	  mav.addObject("announce", announce);
	  initAreas(request,mav.getModelMap());
	  return mav;
	}
	
	@RequestMapping(value="/del")
	@ResponseBody
	public Object del(String ids,HttpServletRequest request,HttpServletResponse response){
	  announceService.deleteAnnounceByIds(ids.split(SystemConst.SPLITE_SIGN_COMMON));
  	  Map<String,Object> map = new HashMap<String,Object>();
	  map.put("result",true);
	  map.put("message",RequestUtils.getMessage("delete", request));
	  return map;
	}
	
	@RequestMapping(value="/add")
	public String add(ModelMap modelMap,SystemManager sysMan,HttpServletRequest request,HttpServletResponse response){
	  initAreas(request,modelMap);
	  return ADD_PAGE;
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String addOrUpdate(SysAnnouncement announce,HttpServletRequest request,HttpServletResponse response){
		if(announce.getId() == null){
			announceService.saveObject(announce);
		}
		else{
			announceService.updateObject(announce);
		}
	    
	    return REDIRECT;
	}
	

	@SuppressWarnings("unused")
	private void initAreas(HttpServletRequest request,ModelMap modelMap){
		 List<Area> areas = areaService.getAreasByCity(SystemConst.ROOTAREAID);
		 modelMap.addAttribute("areas", areas);
	}
}

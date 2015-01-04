package com.ey.controller.announce;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.ey.bo.AgentBo;
import com.ey.consts.SystemConst;
import com.ey.controller.base.BaseController;
import com.ey.dao.entity.Area;
import com.ey.dao.entity.BaseCustomValue;
import com.ey.dao.entity.SysAnnouncement;
import com.ey.dao.entity.SystemManager;
import com.ey.dao.entity.UserBase;
import com.ey.service.AnnounceService;
import com.ey.service.AreaService;
import com.ey.service.StaticService;
import com.ey.util.DateUtil;
import com.ey.util.RequestUtils;
import com.ey.util.StringUtil;

@Controller
@RequestMapping(value="/announce")
public class AnnounceController extends BaseController {
	
	private static final String LIST_PAGE = "announce/list";
	private static final String REDIRECT = "redirect:/announce/list.do";
	private static final String ADD_PAGE = "announce/announceform";
	private static final String SHOW_PAGE = "announce/showgg";
	
	@Autowired
    private AnnounceService announceService;
	
	@Autowired
    private AreaService areaService;
	
	@Autowired
	private StaticService staticService;
	
	
	@RequestMapping(value="/list")
	public ModelAndView list(@ModelAttribute("page") Integer page,@ModelAttribute("rows") Integer rows,HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		List<SysAnnouncement> announces = announceService.getAnnouncesByQueryParam(null, page,rows);
		Long total = announceService.getAnnouncesTotalByQueryParam(null);
		mav.addObject("announces", announces);
		mav.addObject("total", total);
		mav.setViewName(LIST_PAGE);
		return mav;
	}
	
	@RequestMapping(value="/agentgglist")
	@ResponseBody
	public Object agentgglist(Integer group,Integer page,Integer rows,HttpServletRequest request,HttpServletResponse response){
    	Map<String,Object> paramMap = new HashMap<String,Object>();
    	paramMap.put("group", group);
    	paramMap.put("retentionTime", new Date());
		List<SysAnnouncement> announces = getAgentGgList(paramMap, page, rows,request);
		return announces;
	}
	@RequestMapping(value="/gglist")
	@ResponseBody
	public Object gglist(Integer group,Integer page,Integer rows,HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> paramMap = new HashMap<String,Object>();
    	paramMap.put("group", group);
    	paramMap.put("retentionTime", new Date());
		List<SysAnnouncement> announces = getGgList(paramMap,page,rows,request);
		return announces;
	}
	
	private List<SysAnnouncement> getAgentGgList(Map<String,Object> paramMap,Integer page,Integer rows,HttpServletRequest request){
		AgentBo agent = (AgentBo)request.getSession().getAttribute(SystemConst.AGENT);
        //Map<String,Object> queryMap = new HashMap<String,Object>();
    	paramMap.put("home", true);
    	paramMap.put("status", 0);
    	paramMap.put("homeparea", agent.getParentAreaId());
    	paramMap.put("homearea", agent.getAreaId());
        //paramMap.putAll(queryMap);
		List<SysAnnouncement> announces = announceService.getAnnouncesByQueryParam(paramMap, page, rows);
		return announces;
	}
	private List<SysAnnouncement> getGgList(Map<String,Object> paramMap,Integer page,Integer rows,HttpServletRequest request){
		UserBase user = (UserBase)request.getSession().getAttribute(SystemConst.USER);
    	Map<String,Object> queryMap = new HashMap<String,Object>();
		if(user!=null){
		  Area area = areaService.getArea(user.getAreaId());
		  paramMap.put("home", true);
		  paramMap.put("status", 0);
		  paramMap.put("homeparea", area.getCity());
		  paramMap.put("homearea", user.getAreaId());
		}else{
			paramMap.put("scope",0);
			paramMap.put("status",0);
		}
		List<SysAnnouncement> announces = announceService.getAnnouncesByQueryParam(paramMap, page,rows);
		return announces;
	}
	
	@RequestMapping(value="/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id,HttpServletRequest request,HttpServletResponse response){
	  SysAnnouncement announce = announceService.getSysAnnouncement(id);
	  Area area = areaService.getArea(announce.getAreaId());
	  ModelAndView mav = new ModelAndView(ADD_PAGE);
	  mav.addObject("announce", announce);
	  mav.addObject("startDate", DateUtil.getDate(announce.getCreateTime()));
	  String areaEncodePath = area.getEncodePath();
	  if(!area.getId().equals(SystemConst.ROOTAREAID))
		  areaEncodePath = areaEncodePath.substring(8);
	  mav.addObject("areaPath", areaEncodePath);
	  initAreas(request,mav.getModelMap());
	  initAnnStatus(request,mav.getModelMap());
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
	  initAnnStatus(request,modelMap);
	  modelMap.put("startDate", DateUtil.getDate(new Date()));
	  return ADD_PAGE;
	}
	
	@RequestMapping(value="/showgg")
	public String showusergg(Long id,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response){
	  SysAnnouncement sysAnnounce = announceService.getSysAnnouncement(id);
	  //announceService.updateStatusById(id, 1);
		Integer group = sysAnnounce.getAnnouncementGroup();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<SysAnnouncement> annlist = null;
		paramMap.put("group", group);
		paramMap.put("retentionTime", new Date());
		if (group == 1)
			annlist = getGgList(paramMap, 0, 0, request);
		else
			annlist = getAgentGgList(paramMap, 0, 0, request);
		modelMap.addAttribute("announce", sysAnnounce);
		modelMap.addAttribute("announces", annlist);
		modelMap.addAttribute("newSize", annlist.size());
		return SHOW_PAGE;
	}
	
	@RequestMapping(value="/more")
	public String more(Integer group,ModelMap modelMap,@ModelAttribute("page") Integer page,@ModelAttribute("rows") Integer rows,HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<SysAnnouncement> newlist = null;
		List<SysAnnouncement> annAllList = null;
		Long total = null;
		paramMap.put("group", group);
		if (group == 1){
			annAllList = getGgList(paramMap, page,rows, request);
		    total = announceService.getAnnouncesTotalByQueryParam(paramMap);
		    paramMap.put("retentionTime", new Date());
			newlist = getGgList(paramMap, 0,0, request);
		}
		else{
			annAllList = getAgentGgList(paramMap, page, rows, request);
			total = announceService.getAnnouncesTotalByQueryParam(paramMap);
			paramMap.put("retentionTime", new Date());
			newlist = getAgentGgList(paramMap, 0,0, request);
		}
		modelMap.addAttribute("announces", newlist);
		modelMap.addAttribute("announceall", annAllList);
		modelMap.addAttribute("total", total);
		modelMap.addAttribute("newSize", newlist!=null?newlist.size():0);
		return "announce/moregg";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String addOrUpdate(String startDate,SysAnnouncement announce,HttpServletRequest request,HttpServletResponse response){
		
		Date date = DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss",startDate+" 00:00:00");
		Date retentionTime = null;
		//SysAnnouncement sysAnnounce = null;
		/*if(announce.getId() == null){
			date = new Date();
		}else{
		    sysAnnounce = announceService.getSysAnnouncement(announce.getId());
			date = sysAnnounce.getCreateTime();
			try {
				ConvertUtils.register(new DateConverter(null), java.util.Date.class);
				BeanUtils.copyProperties(sysAnnounce, announce);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		String validStr = announce.getRetentionFlag();
		if(!StringUtil.isEmptyString(validStr)){
			if(validStr.endsWith(SystemConst.DAY)){
				retentionTime = DateUtil.getAfterDay(date, Integer.valueOf(validStr.replace(SystemConst.DAY, "").trim()));
			}else if(validStr.endsWith(SystemConst.WEEK)){
				retentionTime = DateUtil.getAfterWeek(date, Integer.valueOf(validStr.replace(SystemConst.WEEK, "").trim()));
			}else if(validStr.endsWith(SystemConst.MONTH)){
				retentionTime = DateUtil.getAfterMonth(date, Integer.valueOf(validStr.replace(SystemConst.MONTH, "").trim()));
			}else if(validStr.endsWith(SystemConst.YEAR)){
				retentionTime = DateUtil.getAfterYear(date, Integer.valueOf(validStr.replace(SystemConst.YEAR, "").trim()));
			}
			
		}
		announce.setCreateTime(date);
		announce.setRetentionTime(retentionTime);
		if(announce.getId() == null){
			//announce.setCreateTime(date);
			//announce.setRetentionTime(retentionTime);
			announceService.saveObject(announce);
		}
		else{
			//sysAnnounce.setCreateTime(date);
			//sysAnnounce.setRetentionTime(retentionTime);
			announceService.updateObject(announce);
		}
	    return REDIRECT;
	}
	

	@SuppressWarnings("unused")
	private void initAreas(HttpServletRequest request,ModelMap modelMap){
		 List<Area> areas = areaService.getAreasByCity(SystemConst.ROOTAREAID);
		 modelMap.addAttribute("areas", areas);
	}
	
	@SuppressWarnings("unused")
	private void initAnnStatus(HttpServletRequest request,ModelMap modelMap){
		List<BaseCustomValue> customValues = staticService.listValues("system_announce_status");
		modelMap.addAttribute("status", customValues);
	}
}

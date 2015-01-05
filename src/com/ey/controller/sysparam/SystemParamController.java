package com.ey.controller.sysparam;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ey.bo.AgentBo;
import com.ey.consts.SystemConst;
import com.ey.controller.base.BaseController;
import com.ey.dao.entity.Area;
import com.ey.dao.entity.BankAccount;
import com.ey.dao.entity.BankInfo;
import com.ey.dao.entity.BaseCustomProp;
import com.ey.dao.entity.BaseCustomValue;
import com.ey.dao.entity.BaseCustomValueId;
import com.ey.dao.entity.NoticeInfo;
import com.ey.dao.entity.TransferRate;
import com.ey.dao.entity.TransferRateId;
import com.ey.service.AreaService;
import com.ey.service.StaticService;
import com.ey.util.DateUtil;
import com.ey.util.RequestUtils;

@Controller
@RequestMapping(value = "/sysparam")
public class SystemParamController extends BaseController {

	private static final String PROPLIST_PAGE = "sysparam/customproplist";
	private static final String DATAVALUELIST_PAGE = "sysparam/customvaluelist";
	private static final String RATELIST_PAGE = "sysparam/ratelist";
	private static final String NOTICELIST_PAGE = "sysparam/noticelist";
	
	@Autowired
	private StaticService staticService;
	
	@Autowired
	private AreaService areaService;
	
	@RequestMapping(value = "/customproplist")
	public ModelAndView customproplist(@ModelAttribute("page") Integer page,
			@ModelAttribute("rows") Integer rows, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		List props = staticService.findCustomProps(null, page, rows);
		Long total = staticService.getTotalCustomProp(null);
		mav.addObject("props", props);
		mav.addObject("total", total);
		mav.setViewName(PROPLIST_PAGE);
		return mav;
	}
	
	@RequestMapping(value = "/ratelist")
	public ModelAndView ratelist(@ModelAttribute("page") Integer page,
			@ModelAttribute("rows") Integer rows, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		List rates = staticService.findTransferRate(null, page, rows);
		Long total = staticService.getTotalTransferRate(null);
		mav.addObject("rates", rates);
		mav.addObject("total", total);
		mav.addObject("payOrgs",RequestUtils.getPayTypeOrg(request));
		mav.setViewName(RATELIST_PAGE);
		return mav;
	}
	
	@RequestMapping(value = "/customvaluelist")
	public ModelAndView customvaluelist(String customPropName,@ModelAttribute("page") Integer page,
			@ModelAttribute("rows") Integer rows, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> queryParamMap = new HashMap<String, Object>();
		queryParamMap.put("customPropName", customPropName);
		ModelAndView mav = new ModelAndView();
		List dataValues = staticService.findCustomValues(queryParamMap, page, rows);
		Long total = staticService.getTotalCustomValue(queryParamMap);
		mav.addObject("dataValues", dataValues);
		mav.addObject("total", total);
		List props = staticService.findCustomProps(queryParamMap, 0, 0);
		mav.addObject("props", props);
		mav.setViewName(DATAVALUELIST_PAGE);
		return mav;
	}
	
	@RequestMapping(value = "/noticelist")
	public ModelAndView noticelist(@ModelAttribute("page") Integer page,
			@ModelAttribute("rows") Integer rows, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		List notices = staticService.findNoticeInfos(null, page, rows);
		Long total = staticService.getTotalNoticeInfo(null);
		mav.addObject("notices", notices);
		mav.addObject("total", total);
		mav.addObject("areas",RequestUtils.initAreas(request));
		mav.addObject("noticeTypes",RequestUtils.getPayTypeName(request,"system_notice_type"));
		mav.addObject("noticeModes",RequestUtils.getPayTypeName(request,"system_notice_mode"));
		mav.setViewName(NOTICELIST_PAGE);
		return mav;
	}
	
	@RequestMapping(value = "/porpedit")
	@ResponseBody
	public Object porpedit(String id,
			HttpServletRequest request, HttpServletResponse response) {
		BaseCustomProp prop = (BaseCustomProp)staticService.getObject(BaseCustomProp.class, id);
		return prop;
	}
	
	@RequestMapping(value = "/dvedit")
	@ResponseBody
	public Object dvedit(String customPropName,Integer customDataValue,
			HttpServletRequest request, HttpServletResponse response) {
		BaseCustomValue dataValue = (BaseCustomValue)staticService.getObject(BaseCustomValue.class,new BaseCustomValueId(customPropName,customDataValue));
		return dataValue;
	}
	
	@RequestMapping(value = "/rateedit")
	@ResponseBody
	public Object rateedit(Long id,
			HttpServletRequest request, HttpServletResponse response) {
		TransferRate rate = (TransferRate)staticService.getObject(TransferRate.class, id);
		return rate;
	}
	
	@RequestMapping(value = "/noticeedit")
	@ResponseBody
	public Object noticeedit(Long id,
			HttpServletRequest request, HttpServletResponse response) {
		NoticeInfo notice = (NoticeInfo)staticService.getObject(NoticeInfo.class, id);
		return notice;
	}
	
	@RequestMapping(value = "/delprop")
	@ResponseBody
	public Object delprop(String[] ids,HttpServletRequest request, HttpServletResponse response) {
		staticService.deleteCustomPropByIds(ids);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", true);
		return map;
	}
	
	@RequestMapping(value = "/delcusv")
	@ResponseBody
	public Object delcusv(String[] ids,HttpServletRequest request, HttpServletResponse response) {
		if(ids!=null){
			List<Object[]> values = new ArrayList<Object[]>();
			  for(String id:ids){
				  String[] array = id.split(SystemConst.SPLITE);
				  values.add(array);
			  }
		      staticService.deleteCustomValueByIds((Object[][])values.toArray(new Object[values.size()][2]));
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", true);
		return map;
	}
	
	@RequestMapping(value = "/delrate")
	@ResponseBody
	public Object delrate(TransferRate rate ,HttpServletRequest request, HttpServletResponse response) {
		staticService.deleteObject(rate);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", true);
		return map;
	}
	
	@RequestMapping(value = "/delnotice")
	@ResponseBody
	public Object delnotice(NoticeInfo notice ,HttpServletRequest request, HttpServletResponse response) {
		staticService.deleteObject(notice);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", true);
		return map;
	}
	
	@RequestMapping(value = "/saveprop")
	@ResponseBody
	public Object saveprop(BaseCustomProp prop,HttpServletRequest request, HttpServletResponse response) {
		staticService.saveOrUpdateObject(prop);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", true);
		return map;
	}
	
	@RequestMapping(value = "/savecusvalue")
	@ResponseBody
	public Object savecusvalue(BaseCustomValue dataValue,BaseCustomValueId dataValueId,HttpServletRequest request, HttpServletResponse response) {
		dataValue.setId(dataValueId);
		staticService.saveObject(dataValue);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", true);
		return map;
	}
	
	@RequestMapping(value = "/saverate")
	@ResponseBody
	public Object saverate(TransferRate rate ,HttpServletRequest request, HttpServletResponse response) {
		if(rate.getId()==null)
		   staticService.saveObject(rate,true);
		else
			staticService.updateObject(rate);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", true);
		return map;
	}
	
	@RequestMapping(value = "/savenotice")
	@ResponseBody
	public Object savenotice(String newDate,NoticeInfo notice,HttpServletRequest request, HttpServletResponse response) {
	    notice.setMassFlag(true);
		if(notice.getId()==null){
			notice.setCreateTime(new Date());
		   staticService.saveObject(notice,true);
		}
		else{
		   notice.setCreateTime(DateUtil.convertStringToDate(SystemConst.timePattern,newDate));
		   staticService.updateObject(notice);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", true);
		return map;
	}
	@SuppressWarnings("unused")
	private Object initAreas(String id,HttpServletRequest request) {
		List<Area> areas = areaService.getAreasByCity(id);
		return areas;
	}
}

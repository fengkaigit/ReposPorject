package com.ey.controller.sysparam;

import java.util.ArrayList;
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
import com.ey.dao.entity.BankAccount;
import com.ey.dao.entity.BankInfo;
import com.ey.dao.entity.BaseCustomProp;
import com.ey.dao.entity.BaseCustomValue;
import com.ey.dao.entity.BaseCustomValueId;
import com.ey.dao.entity.TransferRate;
import com.ey.dao.entity.TransferRateId;
import com.ey.service.StaticService;

@Controller
@RequestMapping(value = "/sysparam")
public class SystemParamController extends BaseController {

	private static final String PROPLIST_PAGE = "sysparam/customproplist";
	private static final String DATAVALUELIST_PAGE = "sysparam/customvaluelist";
	private static final String RATELIST_PAGE = "sysparam/ratelist";
	
	@Autowired
	private StaticService staticService;
	
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
		mav.addObject("banks",initBankInfo(request));
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
	public Object rateedit(TransferRateId id,
			HttpServletRequest request, HttpServletResponse response) {
		TransferRate rate = (TransferRate)staticService.getObject(TransferRate.class, id);
		return rate;
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
	public Object delrate(TransferRateId id,HttpServletRequest request, HttpServletResponse response) {
		staticService.deleteObject(new TransferRate(id));
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
	public Object saverate(TransferRateId id ,HttpServletRequest request, HttpServletResponse response) {
		staticService.saveOrUpdateObject(new TransferRate(id));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", true);
		return map;
	}
	
	@SuppressWarnings("unused")
	private List initBankInfo(HttpServletRequest request) {
		List<BankInfo> banks = staticService.listBanks();
		return banks;
	}
}
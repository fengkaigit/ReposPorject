package com.ey.controller.chargeent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ey.bo.AgentBo;
import com.ey.bo.ChargeEntBo;
import com.ey.consts.SystemConst;
import com.ey.controller.base.BaseController;
import com.ey.dao.entity.AgentInfo;
import com.ey.dao.entity.Area;
import com.ey.dao.entity.BankAccount;
import com.ey.dao.entity.BankInfo;
import com.ey.dao.entity.BaseCustomValue;
import com.ey.dao.entity.ChargeEnterprise;
import com.ey.dao.entity.SystemManager;
import com.ey.service.AreaService;
import com.ey.service.ChargeEntService;
import com.ey.service.StaticService;
import com.ey.util.MD5;
import com.ey.util.RequestUtils;
import com.ey.util.StringUtil;

import java.io.File;
import java.io.IOException;


@Controller
@RequestMapping(value="/charge")
public class ChargeEntController extends BaseController{

	private static final String LIST_PAGE = "charge/list";
	private static final String REDIRECT = "redirect:/charge/list.do";
	private static final String ADD_PAGE = "charge/addcharge";
	@Autowired
	private ChargeEntService chargeEntService;
	
	@Autowired
	private StaticService staticService;
	
	@Autowired
    private AreaService areaService;
	
	@RequestMapping(value="/list")
	public ModelAndView list(@ModelAttribute("page") Integer page,@ModelAttribute("rows") Integer rows,HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		List<ChargeEntBo> chargeList = chargeEntService.getAllChargeEnt(null, page,rows);
		Long total = chargeEntService.getTotalByParam(null);
		mav.addObject("charges", chargeList);
		mav.addObject("total", total);
		mav.setViewName(LIST_PAGE);
		return mav;
	}
	
	@RequestMapping(value="/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id,HttpServletRequest request,HttpServletResponse response) throws IOException{
	  ChargeEntBo chargeBo = chargeEntService.getChargeEnt(id);
	  BankAccount bankAccount = chargeEntService.getBankAccount(id);
	  ModelAndView mav = new ModelAndView(ADD_PAGE);
	  mav.addObject("charge", chargeBo);
	  mav.addObject("bankAcc", bankAccount);
	  mav.addObject("cusvalues",RequestUtils.getPayTypeName(request,"payment_type"));
	  mav.addObject("areas",RequestUtils.initAreas(request));
	  mav.addObject("banks", RequestUtils.initBankInfo(request));
	  return mav;
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String addOrUpdate(ChargeEnterprise chargeEnt,BankAccount bankAccount,Long accId,@RequestParam("file")  MultipartFile uploadFile,HttpServletRequest request,HttpServletResponse response) throws RuntimeException, IOException {
		bankAccount.setId(accId);
		if(!StringUtil.isEmptyString(uploadFile.getOriginalFilename()))
			   chargeEnt.setExPic(uploadFile.getBytes());
		if(chargeEnt.getId() == null){
			chargeEnt.setDelFlag(false);
			chargeEntService.saveChargeEnt(chargeEnt,bankAccount);
		}
		else{
			ChargeEnterprise chargeEntInfo = chargeEntService.getChargeEnterprise(chargeEnt.getId());
			byte[] pic = chargeEntInfo.getExPic();
			BeanUtils.copyProperties(chargeEnt, chargeEntInfo);
			if(chargeEntInfo.getExPic()==null)
				chargeEntInfo.setExPic(pic);
			chargeEntInfo.setDelFlag(false);
			chargeEntService.updateChargeEnt(chargeEntInfo,bankAccount);
		}
	    
	    return REDIRECT;
	}
	
	@RequestMapping(value="/add")
	public String add(ModelMap modelMap,SystemManager sysMan,HttpServletRequest request,HttpServletResponse response){
	  modelMap.addAttribute("areas",RequestUtils.initAreas(request));
	  modelMap.addAttribute("cusvalues",RequestUtils.getPayTypeName(request,"payment_type"));
	  modelMap.addAttribute("banks", RequestUtils.initBankInfo(request));
	  return ADD_PAGE;
	}
	
	@RequestMapping(value="/del")
	@ResponseBody
	public Object del(String ids,HttpServletRequest request,HttpServletResponse response){
	  chargeEntService.deleteChargeEntByIds(ids.split(SystemConst.SPLITE_SIGN_COMMON));
  	  Map<String,Object> map = new HashMap<String,Object>();
	  map.put("result",true);
	  map.put("message",RequestUtils.getMessage("delete", request));
	  return map;
	}
}

package com.ey.controller.sysman;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ey.bo.AgentBo;
import com.ey.bo.CountReportBo;
import com.ey.bo.PaymentBillBo;
import com.ey.consts.SystemConst;
import com.ey.controller.base.BaseController;
import com.ey.dao.entity.SystemManager;
import com.ey.dao.entity.UserBase;
import com.ey.entity.User;
import com.ey.service.AgentService;
import com.ey.service.SysManService;
import com.ey.util.DateUtil;
import com.ey.util.MD5;
import com.ey.util.RequestUtils;
import com.ey.util.StringUtil;
import com.ey.util.CookieManager;

@Controller
@RequestMapping(value="/sysman")
public class SysManController extends BaseController{
	
	private static final String IFRAME_PAGE = "sysman/iframe";
	private static final String INDEX_PAGE = "sysman/index";
	@Autowired
    private SysManService sysManService;
	
	@Autowired
	private AgentService agentService;
	
	
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
  		  mav.setViewName(INDEX_PAGE);
  		  mav.addObject(SystemConst.USER,currentManager);
  		  request.getSession().setAttribute(SystemConst.MANAGER, currentManager);
  		/*       添加cookie信息                  */
			if(request.getParameter("remember")!=null){
				CookieManager.addCookie(response,"sysLoginName",loginCode,60*60*24*31);
				CookieManager.addCookie(response,"sysLoginPwd",password,60*60*24*31);
			}
	   //end
  	  }
  	  return mav;
    }
	
	@RequestMapping(value = "/index")
	public String index(ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response) {
		return INDEX_PAGE;
	}
	
	@RequestMapping(value = "/iframe")
	public ModelAndView iframe(ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView(IFRAME_PAGE);
		return mav;
	}
	
	@RequestMapping(value="/login")
    public String sysLogin(HttpServletRequest request,HttpServletResponse response){
  	  return "login/sysManLogin";
    }
	
	@RequestMapping(value = "/selfMore")
	public ModelAndView selfMore(Integer doflag,@ModelAttribute("page") Integer page,@ModelAttribute("rows") Integer rows,HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", 1);
		map.put("errorFlag", true);
		map.put("errHandleFlag", doflag==0?false:true);
		List selflist = agentService.findAgentSelf(null, map, page,
				rows);
		Long total = agentService.findAgentSelfTotal(null, map);
		ModelAndView mav = new ModelAndView("sysman/errjflist");
		mav.addObject("works", selflist);
		mav.addObject("total", total);
		return mav;

	}
	
	@RequestMapping(value = "/showErrNotice")
	@ResponseBody
	public Object showErrNotice(Long batchId,Long  billId,Integer doflag,HttpServletRequest request, HttpServletResponse response){
		List noticelist = agentService.getNoticeByBillId(batchId,billId, doflag);
		if(noticelist!=null&&noticelist.size()>0)
			return noticelist.get(0);
		return null;

	}
	
	@RequestMapping(value = "/sysmanSelf")
	@ResponseBody
	public Object sysmanSelf(Integer page, Integer rows,HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", 1);
		map.put("errorFlag", true);
		map.put("errHandleFlag", false);
		List selflist = agentService.findAgentSelf(null, map, page,rows);
		return selflist;

	}
	@RequestMapping(value = "/doOutBatch")
	@ResponseBody
	public Object doOutBatch(Integer page, Integer rows,HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		List selflist = agentService.getOutBatchBill( map, page,rows);
		return selflist;

	}
	
	@RequestMapping(value = "/doOutMore")
	public ModelAndView doOutMore(Integer page, Integer rows,HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		List selflist = agentService.getOutBatchBill( map, page,rows);
		Long total = agentService.getCountOutBatchBill(map);
		ModelAndView mav = new ModelAndView("sysman/outjflist");
		mav.addObject("works", selflist);
		mav.addObject("total", total);
		return mav;

	}
	
	@RequestMapping(value = "/errbilllist")
	public String errbilllist(Long id, Integer errflag,ModelMap modelMap,
			@ModelAttribute("page") Integer page,
			@ModelAttribute("rows") Integer rows, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("batchId", id);
		if(errflag!=null){
			map.put("errorFlag", errflag==1?true:false);
		}
		getBillList(map,page,rows,request,modelMap);
		return "sysman/errpaylist";
	}
	
	@RequestMapping(value = "/outbilllist")
	public String outbilllist(Long id,ModelMap modelMap,
			@ModelAttribute("page") Integer page,
			@ModelAttribute("rows") Integer rows, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("batchId", id);
		map.put("outDate",true);
		map.put("paymentStatus", 3);
		getBillList(map,page,rows,request,modelMap);
		return "sysman/outpaylist";
	}
	private void getBillList(Map<String, Object> map,Integer page,Integer rows,HttpServletRequest request,ModelMap modelMap){
		List<PaymentBillBo> billlist = agentService.findBillByBatchId(null, map,
				page, rows);
		Long total = agentService.findBillTotalBatchId(null, map);
		modelMap.addAttribute("bills", billlist);
		modelMap.addAttribute("total", total);
		Map<Integer,String> payStatusMaps = RequestUtils.getPayTypeName(request, "payment_status");
		Map<Integer,String> errorPayStatusMaps =  RequestUtils.getPayTypeName(request,"payment_error_status");
		payStatusMaps.putAll(errorPayStatusMaps);
		modelMap.addAttribute("paystatus",payStatusMaps);
	}
	@RequestMapping(value="/logout")
    public String syslogout(HttpServletRequest request,HttpServletResponse response){
		List list = (List)request.getSession().getAttribute(SystemConst.CUSTOMPROPTYPE);
		if(list!=null&&list.size()>0){
			for(Object o:list) {
			     request.getSession().removeAttribute(o+"");
			}
		}
		request.getSession().removeAttribute(SystemConst.MANAGER);
		request.getSession().removeAttribute(SystemConst.AREAS);
		request.getSession().removeAttribute(SystemConst.BANK);
		request.getSession().removeAttribute(SystemConst.CUSTOMPROPTYPE);
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
		if(sysMan.getId() == null){
			sysMan.setPasswd(MD5.getMD5Str(SystemConst.INITPASSWORD));
			sysManService.saveSysMan(sysMan);
		}
		else{
			SystemManager sysManager = sysManService.getSySManager(sysMan.getId());
			//String password = sysManager.getPasswd();
			//BeanUtils.copyProperties(sysMan, sysManager);
			sysManager.setManagerRealname(sysMan.getManagerRealname());
			sysManager.setEMail(sysMan.getEMail());
			sysManager.setMobilePhone(sysMan.getMobilePhone());
			sysManService.updateSysman(sysManager);
		}
	    
	    return "redirect:/sysman/list.do";
	}
	
	@RequestMapping(value="/add")
	public String add(SystemManager sysMan,HttpServletRequest request,HttpServletResponse response){
	  return "sysman/addsysman";
	}
	
	@RequestMapping(value="/passwd")
	public String showmodifyPass(SystemManager sysMan,HttpServletRequest request,HttpServletResponse response){
	  return "sysman/modifypass";
	}
	
	@RequestMapping(value="/modpass")
	@ResponseBody
	public Object modifyPass(@RequestParam("oldpasswd") String oldPass,@RequestParam("passwd") String newPass,@RequestParam("confirmPassword") String confirePass,HttpServletRequest request,HttpServletResponse response){
	  Map<String,Object> map = new HashMap<String,Object>();
	  SystemManager sysManObj = (SystemManager)request.getSession().getAttribute(SystemConst.MANAGER);
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
		  sysManObj.setPasswd(MD5.getMD5Str(confirePass));
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
		Long num = sysManService.findManagerByLoginName(loginCode);
		if(num>0){
			map.put("result",true);
		}
		return map;
	}

}

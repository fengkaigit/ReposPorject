package com.ey.controller.about;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ey.bo.AgentBo;
import com.ey.consts.SystemConst;
import com.ey.controller.base.BaseController;
import com.ey.dao.entity.Feedback;
import com.ey.dao.entity.UserBase;
import com.ey.service.SysManService;
import com.ey.util.RequestUtils;

@Controller
@RequestMapping(value="/ej")
public class AboutController extends BaseController {
	
	private static final String LIST_PAGE = "ieda/list";
	private static final String REDIRECT = "redirect:/ej/list.do";
	
	@Autowired
    private SysManService sysManService;
	
	
	@RequestMapping(value = "/security")
	public ModelAndView security(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("about/security");
		return mav;
	}
	@RequestMapping(value = "/about")
	public ModelAndView about(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("about/about");
		return mav;
	}
	@RequestMapping(value = "/ieda")
	public ModelAndView ieda(HttpServletRequest request,
			HttpServletResponse response) {		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("ieda/ieda");
		UserBase user = (UserBase)request.getSession().getAttribute(SystemConst.USER);
        if(user!=null){
        	Map<String,Object> queryMap = new HashMap<String,Object>();
    		queryMap.put("areaId", user.getAreaId());
    		List<Feedback> feedlist = sysManService.findFeedBacks(queryMap, 0, 0);
    		mav.addObject("feedbacks", feedlist);
        }
		return mav;
	}
	
	@RequestMapping(value = "/saveFeed")
	@ResponseBody
	public Object saveFeed(Feedback feedBack,HttpServletRequest request,
			HttpServletResponse response) {
		UserBase user = (UserBase)request.getSession().getAttribute(SystemConst.USER);
		if(user!=null){
		  feedBack.setUserId(user.getId());
		  feedBack.setAreaId(user.getAreaId());
		}
		feedBack.setBackFlag(0);
		feedBack.setViewTime(new Date());
		sysManService.saveFeedBack(feedBack);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("result",true);
		return map;
	}
	
	@RequestMapping(value="/list")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		List<Feedback> feedlist = sysManService.findFeedBacks(null, 0, 0);
		mav.addObject("feedbacks", feedlist);
		mav.setViewName(LIST_PAGE);
		return mav;
	}
	
	@RequestMapping(value="/areafeed")
	public ModelAndView areafeed(HttpServletRequest request,HttpServletResponse response){
		UserBase user = (UserBase)request.getSession().getAttribute(SystemConst.USER);
		ModelAndView mav = new ModelAndView();
		Map<String,Object> queryMap = new HashMap<String,Object>();
		queryMap.put("areaId", user.getAreaId());
		List<Feedback> feedlist = sysManService.findFeedBacks(queryMap, 0, 0);
		mav.addObject("feedbacks", feedlist);
		mav.setViewName(LIST_PAGE);
		return mav;
	}
	
	@RequestMapping(value="/reply")
	@ResponseBody
	public Object reply(Long id,String replyContent,HttpServletRequest request,HttpServletResponse response){
		sysManService.updateReplyFeedById(id, replyContent);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("result",true);
		return map;
	}
	
	@RequestMapping(value="/del")
	@ResponseBody
	public Object del(String ids,HttpServletRequest request,HttpServletResponse response){
	  sysManService.deleteFeedBackByIds(ids.split(SystemConst.SPLITE_SIGN_COMMON));
  	  Map<String,Object> map = new HashMap<String,Object>();
	  map.put("result",true);
	  map.put("message",RequestUtils.getMessage("delete", request));
	  return map;
	}
	
	@RequestMapping(value="/getFeed")
	@ResponseBody
	public Object getfeed(Long id,HttpServletRequest request,HttpServletResponse response){
	  Feedback feedBack = sysManService.getFeedBack(id);
  	  Map<String,Object> map = new HashMap<String,Object>();
	  map.put("result",true);
	  map.put("feed",feedBack);
	  return map;
	}
	
	
}

package com.ey.controller.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ey.consts.SystemConst;
import com.ey.controller.base.BaseController;
import com.ey.entity.User;
import com.ey.service.LoginService;
import com.ey.service.UserService;
import com.ey.util.MD5;
import com.ey.util.RequestUtils;


@Controller
@SessionAttributes("USER")
public class LoginController extends BaseController {
     
	 @Autowired
     private LoginService loginService;
	
	 @RequestMapping(value="/login")
     public ModelAndView login(User user,HttpServletRequest request,HttpServletResponse response){
   	  User currentUser = loginService.findUserByLoginCode(user.getLoginCode(),MD5.getMD5Str(user.getPassword()));
   	  ModelAndView mav = new ModelAndView();
   	  if(currentUser==null){
   		  mav.addObject("message", RequestUtils.getMessage("login", request));
   		  mav.setViewName("login/login");
   	  }else{
   		  mav.setViewName("redirect:/user/list.do");
   		  mav.addObject(SystemConst.USER,currentUser);
   	  }
   	  return mav;
     }
}

package com.ey.controller.user;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;


import com.ey.consts.SystemConst;
import com.ey.controller.base.BaseController;
import com.ey.entity.User;
import com.ey.service.UserService;
import com.ey.util.MD5;



@RestController
@SessionAttributes("USER")
public class UserController extends BaseController {
	
      @Autowired
      private UserService userService;
      
      @RequestMapping(value="/info")
      public ModelAndView getTestInfo(HttpServletRequest request,HttpServletResponse response){
    	  List<User> users = userService.findUsers();
    	  ModelAndView view = new ModelAndView("test");
    	  view.addObject("key1", users);
    	  return view;
      }
      
      @RequestMapping(value="/infos")
      public ModelAndView getTestInfos(ModelMap model){
    	  //List<User> users = userService.findUsers();
    	  //model.addAttribute("key1", users);
    	  ModelAndView mav = new ModelAndView("redirect:info.do");
    	  return mav;
      }
      
      @RequestMapping(value="/login",method=RequestMethod.POST)
      public ModelAndView login(User user,HttpServletRequest request,HttpServletResponse response){
    	  User currentUser = userService.findUserByLoginCode(user.getLoginCode(),MD5.getMD5Str(user.getPassword()));
    	  ModelAndView mav = new ModelAndView();
    	  if(currentUser==null){
    		  mav.addObject("message", this.getText("login", request));
    		  mav.setViewName("login/login");
    	  }else{
    		  mav.setViewName("redirect:/info.do");
    		  mav.addObject(SystemConst.USER,currentUser);
    	  }
    	  return mav;
      }
      
      @RequestMapping(value="/user/login",method=RequestMethod.POST)
      public ModelAndView login1(String loginCode,String password,HttpServletRequest request,HttpServletResponse response){
    	  User currentUser = userService.findUserByLoginCode(loginCode,MD5.getMD5Str(password));
    	  ModelAndView mav = new ModelAndView();
    	  if(currentUser==null){
    		  mav.addObject("message", this.getText("login", request));
    		  mav.setViewName("login/login");
    	  }else{
    		  mav.setViewName("redirect:/info.do");
    		  mav.addObject(SystemConst.USER,currentUser);
    	  }
    	  return mav;
      }
      
      @RequestMapping(value="/show")
      @ResponseBody
      public Object getShow(Long id,HttpServletRequest request,HttpServletResponse response){
    	  User user = userService.findUserById(id);
    	  return user;
      }
      @RequestMapping(value="/show/{id}")
      public ModelAndView show(@PathVariable("id") Long id,HttpServletRequest request,HttpServletResponse response){
    	  User user = userService.findUserById(id);
    	  return new ModelAndView("addOrUpdate","user",user);
      }
      @RequestMapping(value="/show/add")
      public ModelAndView show(HttpServletRequest request,HttpServletResponse response){
    	  return new ModelAndView("addOrUpdate");
      }
      @RequestMapping(value="/add")
      public ModelAndView add(User user,HttpServletRequest request,HttpServletResponse response){
    	  user.setPassword(MD5.getMD5Str(user.getPassword()));
    	  userService.saveObject(user);
    	  ModelAndView view = new ModelAndView("saveok");
    	  view.addObject("message", this.getText("add", request));
    	  return view;
      }
      @RequestMapping(value="/update")
      public ModelAndView update(User user,HttpServletRequest request,HttpServletResponse response) {
    	  user.setPassword(MD5.getMD5Str(user.getPassword()));
    	  userService.update(user);
    	  ModelAndView view = new ModelAndView("saveok");
    	  view.addObject("message", this.getText("update", request));
    	  return view;
      }
      @RequestMapping(value="/del")
      public ModelAndView del(User user,HttpServletRequest request,HttpServletResponse response){
    	  userService.delete(user);
    	  ModelAndView view = new ModelAndView("saveok");
    	  view.addObject("message", this.getText("delete", request));
    	  return view;
      }
}

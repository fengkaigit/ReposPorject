package com.ey.controller.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;


import com.ey.consts.SystemConst;
import com.ey.controller.base.BaseController;
import com.ey.entity.User;
import com.ey.service.UserService;
import com.ey.util.MD5;
import com.ey.util.RequestUtils;



@Controller
@RequestMapping(value="/user")
public class UserController extends BaseController {
	
      @Autowired
      private UserService userService;
      
      @RequestMapping(value="/list")
      public ModelAndView getTestInfo(HttpServletRequest request,HttpServletResponse response){
    	  List<User> users = userService.findUsers();
    	  ModelAndView view = new ModelAndView("test/list");
    	  view.addObject("key1", users);
    	  return view;
      }
      
      @RequestMapping(value="/uploadform")
      public ModelAndView uploadform(ModelMap model){
    	  ModelAndView mav = new ModelAndView("test/upload");
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
    	  return new ModelAndView("test/addOrUpdate","user",user);
      }
      @RequestMapping(value="/show/add")
      public ModelAndView show(HttpServletRequest request,HttpServletResponse response){
    	  return new ModelAndView("test/addOrUpdate");
      }
      @RequestMapping(value="/add")
      public ModelAndView add(User user,HttpServletRequest request,HttpServletResponse response){
    	  user.setPassword(MD5.getMD5Str(user.getPassword()));
    	  userService.saveObject(user);
    	  ModelAndView view = new ModelAndView("redirect:list.do");
    	  view.addObject("message", RequestUtils.getMessage("add", request));
    	  return view;
      }
      @RequestMapping(value="/update")
      public ModelAndView update(User user,HttpServletRequest request,HttpServletResponse response) {
    	  user.setPassword(MD5.getMD5Str(user.getPassword()));
    	  userService.update(user);
    	  ModelAndView view = new ModelAndView("redirect:list.do");
    	  view.addObject("message", RequestUtils.getMessage("update", request));
    	  return view;
      }
      @RequestMapping(value="/del")
      @ResponseBody
      public Object del(User user,HttpServletRequest request,HttpServletResponse response) throws IOException{
    	  userService.delete(user);
    	 
    	  Map<String,Object> map = new HashMap<String,Object>();
    	  map.put("message","删除成功");
    	  return map;
      }
}

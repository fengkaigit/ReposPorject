package com.ey.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ey.consts.SystemConst;
import com.ey.util.RequestUtils;

public class CommonInterceptor implements  HandlerInterceptor {

	private String loginUrl;//利用正则映射到需要拦截的路径    
	private List<String> excludeMappingUrls;
	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}
    
	public void setExcludeMappingUrls(List<String> excludeMappingUrls) {
		this.excludeMappingUrls = excludeMappingUrls;
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		// TODO Auto-generated method stub
		 String requestUrl = request.getRequestURI();
		 if(!contains(requestUrl)){
			 HttpSession session =request.getSession();  
		     if(session.getAttribute(SystemConst.USER)==null){  
		    	    request.setAttribute("forwardUrl", requestUrl);
		            request.getRequestDispatcher(loginUrl).forward(request, response);
		           
		            return false;   
		      }    
		 }
		return true;
	}
	private boolean contains(String requestUrl){
		for(String url:excludeMappingUrls){
			if(requestUrl.endsWith(url))
				return true;
		}
		return false;
	}

}

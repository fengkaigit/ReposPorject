package com.ey.exception;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

public class CustomSimpleMappingExceptionResolver extends
		SimpleMappingExceptionResolver {

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,  
            HttpServletResponse response, Object handler, Exception ex) {
		// TODO Auto-generated method stub
		String viewName = determineViewName(ex, request);  
        if (viewName != null) {// JSP格式返回  
            if (!(request.getHeader("accept").indexOf("application/json") > -1 || (request  
                    .getHeader("X-Requested-With")!= null && request  
                    .getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))) {  
                // 如果不是异步请求  
                Integer statusCode = determineStatusCode(request, viewName);  
                if (statusCode != null) {  
                    applyStatusCodeIfPossible(request, response, statusCode);  
                }  
                return getModelAndView(viewName, ex, request);  
            } else {// JSON格式返回  
                try {  
                    PrintWriter writer = response.getWriter();  
                    writer.write(ex.getMessage());  
                    writer.flush();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
                return null;  
  
            }  
        } else {  
            return null;  
        }  
	}
   
}

/**  
		* Desc:   
        * @title sessionListen.java   
        * @author hujie@ieds.com.cn  
        * @update 2012-4-6 下午03:30:19  
        * @version V1.0 
		* Note: This content is restricted to IEDS within the company 
			circulated to prohibit leakage as well as for other commercial purposes 
        */
package com.ey.listener;


import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ey.dao.common.dbid.DatabaseDbidGenerator;
import com.ey.dao.common.dbid.DbidGenerator;
import com.ey.service.InitDbIdService;

/**  
 * Desc:     
 * @version 1.0  
 * @author hujie@ieds.com.cn  
 * @update 2012-4-6 下午03:30:19  
 */

public class CommonListener implements HttpSessionListener,ServletContextListener{ 
	
	@Override
	public void sessionCreated(HttpSessionEvent event) {
	}
	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ServletContext servletContext = arg0.getServletContext();
		WebApplicationContext wctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		InitDbIdService initDbIdService = (InitDbIdService)wctx.getBean("initDdbIdService");
		initDbIdService.execute();
	}

}

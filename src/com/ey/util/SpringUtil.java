package com.ey.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import org.apache.log4j.Logger;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.beans.DirectFieldAccessor;


public class SpringUtil {
	static protected Logger logger = Logger.getLogger(SpringUtil.class);
	private static final String ADVISED_FIELD_NAME = "advised";   
	  
	private static final String    
	CLASS_JDK_DYNAMIC_AOP_PROXY = "org.springframework.aop.framework.JdkDynamicAopProxy";   
	public static Class getTargetClass(Object candidate) {   
	    if (!org.springframework.aop.support.AopUtils.isJdkDynamicProxy(candidate)) {   
	        return org.springframework.aop.support.AopUtils.getTargetClass(candidate);   
	    }   
	  
	    return getTargetClassFromJdkDynamicAopProxy(candidate);   
	}   
	  
	private static Class getTargetClassFromJdkDynamicAopProxy(Object candidate) {   
	    try {   
	        InvocationHandler invocationHandler = Proxy.getInvocationHandler(candidate);   
	        if (!invocationHandler.getClass().getName().equals(CLASS_JDK_DYNAMIC_AOP_PROXY)) {   
	            //��Ŀǰ��spring�汾���⴦��Զ����ִ�У�����Ժ�spring��dynamic proxyʵ�ֱ��   
	        	logger.warn("the invocationHandler of JdkDynamicProxy isn`t the instance of "  
	 + CLASS_JDK_DYNAMIC_AOP_PROXY);   
	            return candidate.getClass();   
	        }   
	        AdvisedSupport advised = (AdvisedSupport) new DirectFieldAccessor(invocationHandler).getPropertyValue(ADVISED_FIELD_NAME);   
	        Class targetClass = advised.getTargetClass();   
	        if (Proxy.isProxyClass(targetClass)) {   
	            // Ŀ���໹�Ǵ��?�ݹ�   
	            Object target = advised.getTargetSource().getTarget();   
	            return getTargetClassFromJdkDynamicAopProxy(target);   
	        }   
	        return targetClass;   
	    } catch (Exception e) {   
	    	logger.error("get target class from " + CLASS_JDK_DYNAMIC_AOP_PROXY + " error", e);   
	        return candidate.getClass();   
	    }   
	}  

}

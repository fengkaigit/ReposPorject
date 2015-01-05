package com.ey.util;

import com.ey.consts.SystemConst;
import com.ey.dao.entity.Area;
import com.ey.dao.entity.BankInfo;
import com.ey.dao.entity.BaseCustomValue;
import com.ey.dao.entity.PaymentOrg;
import com.ey.service.AreaService;
import com.ey.service.StaticService;
import com.ey.service.common.SpringWiredBean;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.util.UrlPathHelper;

/**
 * HttpServletRequest帮助类
 */
public class RequestUtils {
	private static final Logger log = LoggerFactory
			.getLogger(RequestUtils.class);
    private static final SpringWiredBean wiredBean = SpringWiredBean.getInstance();
	/**
	 * 获取QueryString的参数，并使用URLDecoder以UTF-8格式转码。如果请求是以post方法提交的，
	 * 那么将通过HttpServletRequest#getParameter获取。
	 * 
	 * @param request
	 *            web请求
	 * @param name
	 *            参数名称
	 * @return
	 */
	public static String getQueryParam(HttpServletRequest request, String name) {
		if (StringUtils.isBlank(name)) {
			return null;
		}
		if (request.getMethod().equalsIgnoreCase(SystemConst.POST)) {
			return request.getParameter(name);
		}
		String s = request.getQueryString();
		if (StringUtils.isBlank(s)) {
			return null;
		}
		try {
			s = URLDecoder.decode(s, SystemConst.UTF8);
		} catch (UnsupportedEncodingException e) {
			log.error("encoding " + SystemConst.UTF8 + " not support?", e);
		}
		String[] values = parseQueryString(s).get(name);
		if (values != null && values.length > 0) {
			return values[values.length - 1];
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> getQueryParams(HttpServletRequest request) {
		Map<String, String[]> map;
		if (request.getMethod().equalsIgnoreCase(SystemConst.POST)) {
			map = request.getParameterMap();
		} else {
			String s = request.getQueryString();
			if (StringUtils.isBlank(s)) {
				return new HashMap<String, Object>();
			}
			try {
				s = URLDecoder.decode(s, SystemConst.UTF8);
			} catch (UnsupportedEncodingException e) {
				log.error("encoding " + SystemConst.UTF8 + " not support?", e);
			}
			map = parseQueryString(s);
		}

		Map<String, Object> params = new HashMap<String, Object>(map.size());
		int len;
		for (Map.Entry<String, String[]> entry : map.entrySet()) {
			len = entry.getValue().length;
			if (len == 1) {
				params.put(entry.getKey(), entry.getValue()[0]);
			} else if (len > 1) {
				params.put(entry.getKey(), entry.getValue());
			}
		}
		return params;
	}

	/**
	 * 
	 * Parses a query string passed from the client to the server and builds a
	 * <code>HashTable</code> object with key-value pairs. The query string
	 * should be in the form of a string packaged by the GET or POST method,
	 * that is, it should have key-value pairs in the form <i>key=value</i>,
	 * with each pair separated from the next by a &amp; character.
	 * 
	 * <p>
	 * A key can appear more than once in the query string with different
	 * values. However, the key appears only once in the hashtable, with its
	 * value being an array of strings containing the multiple values sent by
	 * the query string.
	 * 
	 * <p>
	 * The keys and values in the hashtable are stored in their decoded form, so
	 * any + characters are converted to spaces, and characters sent in
	 * hexadecimal notation (like <i>%xx</i>) are converted to ASCII characters.
	 * 
	 * @param s
	 *            a string containing the query to be parsed
	 * 
	 * @return a <code>HashTable</code> object built from the parsed key-value
	 *         pairs
	 * 
	 * @exception IllegalArgumentException
	 *                if the query string is invalid
	 * 
	 */
	public static Map<String, String[]> parseQueryString(String s) {
		String valArray[] = null;
		if (s == null) {
			throw new IllegalArgumentException();
		}
		Map<String, String[]> ht = new HashMap<String, String[]>();
		StringTokenizer st = new StringTokenizer(s, "&");
		while (st.hasMoreTokens()) {
			String pair = (String) st.nextToken();
			int pos = pair.indexOf('=');
			if (pos == -1) {
				continue;
			}
			String key = pair.substring(0, pos);
			String val = pair.substring(pos + 1, pair.length());
			if (ht.containsKey(key)) {
				String oldVals[] = (String[]) ht.get(key);
				valArray = new String[oldVals.length + 1];
				for (int i = 0; i < oldVals.length; i++) {
					valArray[i] = oldVals[i];
				}
				valArray[oldVals.length] = val;
			} else {
				valArray = new String[1];
				valArray[0] = val;
			}
			ht.put(key, valArray);
		}
		return ht;
	}

	public static Map<String, String> getRequestMap(HttpServletRequest request,
			String prefix) {
		return getRequestMap(request, prefix, false);
	}

	public static Map<String, String> getRequestMapWithPrefix(
			HttpServletRequest request, String prefix) {
		return getRequestMap(request, prefix, true);
	}

	@SuppressWarnings("unchecked")
	private static Map<String, String> getRequestMap(
			HttpServletRequest request, String prefix, boolean nameWithPrefix) {
		Map<String, String> map = new HashMap<String, String>();
		Enumeration<String> names = request.getParameterNames();
		String name, key, value;
		while (names.hasMoreElements()) {
			name = names.nextElement();
			if (name.startsWith(prefix)) {
				key = nameWithPrefix ? name : name.substring(prefix.length());
				value = StringUtils.join(request.getParameterValues(name), ',');
				map.put(key, value);
			}
		}
		return map;
	}

	/**
	 * 获取访问者IP
	 * 
	 * 在一般情况下使用Request.getRemoteAddr()即可，但是经过nginx等反向代理软件后，这个方法会失效。
	 * 
	 * 本方法先从Header中获取X-Real-IP，如果不存在再从X-Forwarded-For获得第一个IP(用,分割)，
	 * 如果还不存在则调用Request .getRemoteAddr()。
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");
		if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
			return ip;
		}
		ip = request.getHeader("X-Forwarded-For");
		if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个IP值，第一个为真实IP。
			int index = ip.indexOf(',');
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		} else {
			return request.getRemoteAddr();
		}
	}

	/**
	 * 获得当的访问路径
	 * 
	 * HttpServletRequest.getRequestURL+"?"+HttpServletRequest.getQueryString
	 * 
	 * @param request
	 * @return
	 */
	public static String getLocation(HttpServletRequest request) {
		UrlPathHelper helper = new UrlPathHelper();
		StringBuffer buff = request.getRequestURL();
		String uri = request.getRequestURI();
		String origUri = helper.getOriginatingRequestUri(request);
		buff.replace(buff.length() - uri.length(), buff.length(), origUri);
		String queryString = helper.getOriginatingQueryString(request);
		if (queryString != null) {
			buff.append("?").append(queryString);
		}
		return buff.toString();
	}

	/**
	 * 获得请求的session id，但是HttpServletRequest#getRequestedSessionId()方法有一些问题。
	 * 当存在部署路径的时候，会获取到根路径下的jsessionid。
	 * 
	 * @see HttpServletRequest#getRequestedSessionId()
	 * 
	 * @param request
	 * @return
	 */
	public static String getRequestedSessionId(HttpServletRequest request) {
		String sid = request.getRequestedSessionId();
		String ctx = request.getContextPath();
		// 如果session id是从url中获取，或者部署路径为空，那么是在正确的。
		if (request.isRequestedSessionIdFromURL() || StringUtils.isBlank(ctx)) {
			return sid;
		} else {
			// 手动从cookie获取
			Cookie cookie = CookieUtils.getCookie(request,
					SystemConst.JSESSION_COOKIE);
			if (cookie != null) {
				return cookie.getValue();
			} else {
				return request.getSession().getId();
			}
		}

	}
	/**
	 * 获得国际化资源
	 * 
	 * @param key
	 * @param request
	 * @return
	 */
	public static String getMessage(String key,HttpServletRequest request){
		 Locale locale = RequestContextUtils.getLocale(request);
		 ApplicationContext ctx = RequestContextUtils.getWebApplicationContext(request);
		 return ctx.getMessage(key, null,locale);
	}
	/**
	 * 获得附件上传目录
	 *
	 * @param request
	 * @return
	 */
	public static String getContextDirectory(String contextName,HttpServletRequest request){
    	String initPath = request.getSession().getServletContext().getInitParameter(contextName);
    	if(initPath==null){
    		initPath = request.getSession().getServletContext().getRealPath("/")  + "\\" + contextName+"\\";
    	}
    	File file = new File(initPath);   
        if (!file.exists()) {   
            file.mkdir();   
        } 
    	return initPath;
    }
	public static List<BankInfo> initBankInfo(HttpServletRequest request) {
		List<BankInfo> banks = (List<BankInfo>)request.getSession().getAttribute(SystemConst.BANK);
		if(banks==null){
			 StaticService staticService = (StaticService)wiredBean.getBean("staticService");
			 banks = staticService.listBanks();
		     request.getSession().setAttribute(SystemConst.BANK, banks);
		}
		return banks;
	}
	public static List<Area> initAreas(HttpServletRequest request) {
		List<Area> areas = (List<Area>)request.getSession().getAttribute(SystemConst.AREAS);
		if(areas==null){
			   AreaService areaService = (AreaService)wiredBean.getBean("areaService");
		       areas = areaService.getAreasByCity(SystemConst.ROOTAREAID);
			   request.getSession().setAttribute(SystemConst.AREAS, areas);
		}
		return areas;
	}
	public static Map<Integer,String> getPayTypeName(HttpServletRequest request,String typeCode){
		Map<Integer,String> dataValueMap = (Map<Integer,String>)request.getSession().getAttribute(typeCode);
		if(dataValueMap==null){
			List customlist = (List)request.getSession().getAttribute(SystemConst.CUSTOMPROPTYPE);
			dataValueMap = new HashMap<Integer,String>();
			 StaticService staticService = (StaticService)wiredBean.getBean("staticService");
		    List<BaseCustomValue> customValues = staticService.listValues(typeCode);
		    if(customValues!=null&&customValues.size()>0){
              for(BaseCustomValue value:customValues){
        	        dataValueMap.put(value.getId().getDataValue(), value.getPropChName());
              }
		    }
		    if(customlist==null)
				customlist = new ArrayList();
		    customlist.add(typeCode);
		    request.getSession().setAttribute(typeCode, dataValueMap);
		    request.getSession().setAttribute(SystemConst.CUSTOMPROPTYPE, customlist);
		}
        return dataValueMap;
	}
	public static Map<String,String> getPayTypeOrg(HttpServletRequest request){
		Map<String,String> paymentOrgMap = (Map<String,String>)request.getSession().getAttribute(SystemConst.PAYMENTORGS);
		if(paymentOrgMap==null){
			paymentOrgMap = new LinkedHashMap<String,String>();
			 StaticService staticService = (StaticService)wiredBean.getBean("staticService");
		    List<PaymentOrg> paymentOrgs = staticService.listPaymentOrg();
		    if(paymentOrgs!=null&&paymentOrgs.size()>0){
              for(PaymentOrg value:paymentOrgs){
            	  paymentOrgMap.put(value.getPayOrgCode(), value.getPayOrgName());
              }
		    }
		    request.getSession().setAttribute(SystemConst.PAYMENTORGS, paymentOrgMap);
		}
        return paymentOrgMap;
	}
	public static void main(String[] args) {
	}
}

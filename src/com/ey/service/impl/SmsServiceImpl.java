package com.ey.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Service;

import com.ey.service.SmsService;
import com.ey.util.MD5;
import com.ey.util.MessageUtil;
import com.ey.util.StringUtil;
@Service("smsService")
public class SmsServiceImpl implements SmsService {
	private String smsurl = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";//"http://www.ztsms.cn:8800/sendSms.do";
	private String loginCode="ejiaofei";
	private String password="Ejf12345";
	private String productId="191919";

	@Override
	public String sendSms(String content,String mobile) {
		String result = "发送失败";
		if (StringUtil.isEmptyString(content)) {
			return result;
		}
		NameValuePair[] data = {
				/*new NameValuePair("username", loginCode),
				new NameValuePair("password", password),
				new NameValuePair("mobile", "15048354468"),
				new NameValuePair("content", content),
				new NameValuePair("productid", productId)*/
				new NameValuePair("account", "cf_nmsex"), 
			    new NameValuePair("password", "nm123qwe"),
			    new NameValuePair("mobile", mobile), 
			    new NameValuePair("content", content)
		};

		HttpClient httpClient = new HttpClient();
		PostMethod postMethod = new PostMethod(smsurl);
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
		postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		postMethod.setRequestBody(data);
		try {
			int statusCode = httpClient.executeMethod(postMethod);
			if (statusCode == HttpStatus.SC_MOVED_PERMANENTLY
					|| statusCode == HttpStatus.SC_MOVED_TEMPORARILY) {
				Header locationHeader = postMethod
						.getResponseHeader("location");

				String location = null;
				if (locationHeader != null) {
					location = locationHeader.getValue();
					System.out.println("The page was redirectedto:" + location);
				} else {
					System.out.println("Location field value is null");
				}
				return result;
			}
			if (statusCode != HttpStatus.SC_OK) {
				System.out
						.println("Method failed" + postMethod.getStatusLine());
				return result;
			}
			String resultString = null;
			System.out.println(postMethod.getResponseBodyAsString());
			/*System.out.println(new String(postMethod.getResponseBodyAsString()
					.getBytes("iso8859-1"), "GBK"));*/
			// resultString =
			// postMethod.getResponseBodyAsString().substring(30,35).toUpperCase();
			resultString = postMethod.getResponseBodyAsString();
			Document doc;
			
			try {
				doc = DocumentHelper.parseText(resultString);
				Element root = doc.getRootElement();
				String code = root.elementText("code");	
				String msg = root.elementText("msg");	
				String smsid = root.elementText("smsid");
				System.out.println("code="+code);
				System.out.println("msg="+msg);
				System.out.println("smsid="+smsid);			
				return msg;
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			/*if (resultString.equals("UTF-8")) {
				resultString = new String(postMethod.getResponseBodyAsString()
						.getBytes("iso8859-1"), "UTF-8");
			} else {
				resultString = new String(postMethod.getResponseBodyAsString()
						.getBytes("iso8859-1"), "GBK");
			}*/
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public static void main(String[] args){
		SmsServiceImpl ss = new SmsServiceImpl();
		String code = "5543";
		String money = "220.8";
		String password = "8888";
		//ss.sendSms("欢迎您注册（易缴365）便民缴费平台，您本次注册的验证码为："+code+",请在[10]分钟内使用，过期失效！ 【易缴365】");
		//ss.sendSms("{缴费提醒}: 您好！您15年3月份的电费账单为 "+money+"元，请及时登陆ejiao365.com缴费以免耽误您用电。 【易缴365】");
		//ss.sendSms("尊敬的用户您好：您已经注册开通了（易缴365）的便民缴费平台，用户名是您的手机号，初始密码为："+password+"，首次登陆后请及时修改； 【易缴365】");
		String result = ss.sendSms(MessageUtil.getMessageFormatByType(0),"15048354468");
		System.out.println("result="+result);
	}
}

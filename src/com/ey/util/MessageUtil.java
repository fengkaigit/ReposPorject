package com.ey.util;

public class MessageUtil {
	public static String getMessageFormatByType(int type){
		 Double code = Math.random()*9000+1000;
		return "您的验证码是：【"+code.intValue()+"】。请不要把验证码泄露给其他人。";
		
	}
	public static void main(String[] args){
		System.out.println(getMessageFormatByType(0));
	}
}

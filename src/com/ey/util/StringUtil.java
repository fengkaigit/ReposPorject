package com.ey.util;

import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class StringUtil {

	public static final String ID_SEPARATOR = ",";

	public static final String EMPTY_STRING = "";

	public static final String COMMA_SPACE = ", ";

	public static final char DOT = '.';

	private final static Log log = LogFactory.getLog(StringUtil.class);

	/**
	 * Decode a string using Base64 encoding.
	 * 
	 * @param str
	 * @return String
	 */
	public static String decodeString(String str) {
		return new String(Base64.decode(str));
	}


	public static String encodePassword(String password, String algorithm) {
		byte[] unencodedPassword = password.getBytes();
		MessageDigest md = null;
		try {
			// first create an instance, given the provider
			md = MessageDigest.getInstance(algorithm);
		}
		catch (Exception e) {
			log.error("Exception: " + e);

			return password;
		}
		md.reset();
		md.update(unencodedPassword);
		byte[] encodedPassword = md.digest();
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < encodedPassword.length; i++) {
			if ((encodedPassword[i] & 0xff) < 0x10) {
				buf.append("0");
			}
			buf.append(Long.toString(encodedPassword[i] & 0xff, 16));
		}
		return buf.toString();
	}

	public static String encodeString(String str) {
		return Base64.encode(str.getBytes());
	}

	public static Long[] splitStringToLongArray(String s) {
		Long[] result = null;
		if (s != null && s.length() > 0) {
			String[] strArray = s.split(ID_SEPARATOR);
			if (strArray != null && strArray.length > 0) {
				result = new Long[strArray.length];
				for (int i = 0; i < strArray.length; i++) {
					try {
						result[i] = Long.valueOf(strArray[i]);
					}
					catch (NumberFormatException e) {
						if (log.isDebugEnabled()) {
							log.debug(e.getMessage());
						}
					}
				}
			}
		}
		return result;
	}

	public static List splitStringToStringList(String s) {
		List result = null;
		if (s != null) {
			result = new ArrayList(Arrays.asList(s.split(ID_SEPARATOR)));
		}
		return result;
	}

	public static String toString(Object o) {
		return o == null ? "" : o.toString();
	}

	public static String uniteArrayToString(Object[] aArray) {
		String result = "";

		if (aArray != null) {
			for (int i = 0; i < aArray.length; i++) {
				result += ID_SEPARATOR + aArray[i].toString();
			}
			if (result.length() > 0) {
				result = result.substring(ID_SEPARATOR.length());
			}
		}
		return result;
	}

	/**
	 * 
	 * @param aList
	 * @return ID_SEPARATOR
	 */
	public static String[] uniteListToString(List aList) {
		String[] result = null;
		if (aList != null) {
			Iterator itr = aList.iterator();
			if (itr.hasNext()) {
				Object item = itr.next();
				if (item instanceof Object[]) {
					result = new String[((Object[]) item).length];
					for (int j = 0; j < result.length; j++) {
						result[j] = "";
					}
					for (Iterator i = aList.iterator(); i.hasNext();) {
						Object[] items = (Object[]) i.next();
						for (int j = 0; j < items.length; j++) {
							result[j] += ID_SEPARATOR + items[j].toString();
						}
					}
					for (int j = 0; j < result.length; j++) {
						if (result[j].length() > 0) {
							result[j] = result[j].substring(ID_SEPARATOR.length());
						}
					}
				}
				else {
					result = new String[]{""};
					for (Iterator i = aList.iterator(); i.hasNext();) {
						result[0] += ID_SEPARATOR + i.next().toString();
					}
					if (result[0].length() > 0) {
						result[0] = result[0].substring(ID_SEPARATOR.length());
					}
				}
			}
		}
		return result;
	}

	public static String convertArrayToSplitString(Object[] array, String splitStr, boolean hasYinHao) {
		String toString = "";
		for (int i = 0; i < array.length; i++) {
			if (i > 0) {
				toString += splitStr;
			}
			if(hasYinHao){
				toString += "'" + array[i].toString() + "'";
			}
			else{
				toString += array[i].toString();
			}
		}
		return toString;
	}

	public static String[] divideString(String source, String divideFlag) {
		if (source == null) {
			return null;
		}
		if (source.equals("")) {
			return new String[]{""};
		}
		if (source == null || source.equals("")) {
			return new String[]{source};
		}
		StringTokenizer st = new StringTokenizer(source, divideFlag);
		int count = st.countTokens();
		String apple[] = new String[count];
		for (int ii = 0; ii < count; ii++) {
			apple[ii] = st.nextToken();
		}
		return apple;
	}

	public final static String getStringByArray(String[] values) {
		StringBuffer valueStr = new StringBuffer();
		if (!TypeChecker.isEmpty(values)) {
			for (int i = 0; i < values.length; i++) {
				valueStr.append(values[i]);
			}
		}
		return valueStr.toString();
	}

	public static String delEnter(String str) {
		String finalStr = "";
		if (str == null || str.equals("")) {
			return str;
		}
		for (int ii = 0; ii < str.length(); ii++) {
			if ((str.charAt(ii) != 13) && (str.charAt(ii) != 10)) {
				finalStr += str.charAt(ii);
			}
		} // end for
		return finalStr;
	}
	/**
	 * 
			* Desc:  获取字符串的真实长度，汉字算两位
	        * @param value 字符串对象
	        * @return  字符串长度   
	        * @version 1.0  
	        * @author wanghaoran@ieds.com.cn  
	        * @update 2012-4-5 下午06:08:42
	 */
	public static int StringTrueLength(String value) {
		if(value==null||value.length()==0){
			return 0;
		}
		int valueLength = 0;
		char[] chars = value.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			byte[] bytes = ("" + chars[i]).getBytes();
			if (bytes.length >= 2) {
				valueLength += 2;
			} else {
				valueLength += 1;
			}
		}

		return valueLength;
	}
	public static String formatDataValue(double data){
		DecimalFormat ddf = new DecimalFormat("#,##0.00");
		return ddf.format(data);
	}
	public static boolean isInteger(String value){
		try{
			Integer.valueOf(value);
			return true;
		}catch(Exception e){
			return false;
			
		}
	}
	public static boolean isChinese(String str){
		String regEx = "[\\u4e00-\\u9fa5]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		if(m.find())
			return true;
		return false;
	}
	public static boolean isEmptyString(String string){
		if(string==null||string.trim().equals("")){
			return true;
		}
		return false;
	}
	public static boolean isNullObject(Object obj){
		if(obj==null){
			return true;
		}
		return false;
	}
	//生成规则：年（4位）+月（2位）+日（2位）+时（2位）+分（2位）+秒（2位）+8位流水号
	public static String getBillNo(Date date,Long dbId){
		String timeStr = DateUtil.getDateTime("yyyyMMddHHmmss", date);
		String flowNo = dbId.toString();
		int leng = flowNo.length();
		int size = 8;
		for(int i=0;i<(size-leng);i++){
			flowNo = "0"+flowNo;
		}
		return timeStr+flowNo;
	}
}

package com.ey.util;

public class HtmlUtil {
	static public String StripHT(String strHtml) {
		
		return strHtml.replaceAll("<br>", "\n").replaceAll("&nbsp;", " ").replaceAll("<.+?>", "");
	}

}

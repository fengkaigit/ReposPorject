package com.ey.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtil {
	static public String getSysDateFmt(String s){
		if(s == null || "".equals(s)){
			s="yyyyMMdd";
		}
		Calendar rightNow = Calendar.getInstance();
        SimpleDateFormat fmt = new SimpleDateFormat(s);
        String sysDatetime = fmt.format(rightNow.getTime()); 
        return sysDatetime;
	}
	 public static long getBetweenHourNumber(Date d1,Date d2) {
		    long dayNumber = 0;
		    //1小时=60分钟=3600秒=3600000
		    //long mins = 60L * 1000L;
		    long hours = 60L*60L * 1000L;
		    //long day= 24L * 60L * 60L * 1000L;计算天数之差
		    try {
		       if(d1.after(d2)){
		    	   dayNumber = (d1.getTime() - d2.getTime()) / hours;
		       }else{
		    	   dayNumber = (d2.getTime() - d1.getTime()) / hours;
		       }
		      
		    } catch (Exception e) {
		       e.printStackTrace();
		    }
		    return  dayNumber;
		    }
	 public static void main(String[] args){
		 Date d1 = DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss","2012-06-15 15:10:10");
		 Date d2 = DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss","2012-06-15 12:50:10");
		 System.out.println(getBetweenHourNumber(d1,d2));
		 long hours = getBetweenHourNumber(d1,d2);
		 String delayTimeStr="";
			long days = hours/24;
			long lhour = hours%24;
			if(days>0){
				delayTimeStr = delayTimeStr+days+"天";
				if(lhour>0){
					delayTimeStr = delayTimeStr+lhour+"小时";
				}
			}else{
				delayTimeStr = delayTimeStr+lhour+"小时";
			}
			System.out.println(delayTimeStr);
	 }
}

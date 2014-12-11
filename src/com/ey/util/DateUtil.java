package com.ey.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DateUtil {
	private static Log log = LogFactory.getLog(DateUtil.class);

	private static String defaultDatePattern = null;

	private static String timePattern = "yyyy-MM-dd HH:mm:ss";

	private static String timePatternString = "yyyyMMddHHmmss";
	
	private static String timePatternMonth = "yyyy-MM";

	private static String timePatternEnd = "yyyy-MM-dd 23:59:59";

	private static String timePatternSim = "MM-dd HH:mm";
	private static String timePatternSimSec = "yyyy-MM-dd HH:mm";
	private static String timecnpattern = "yyyy年M月d日";

	public static synchronized String getDatePattern() {
		defaultDatePattern = "yyyy-MM-dd";
		return defaultDatePattern;
	}

	public static final String getDate(Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";
		if (aDate != null) {
			df = new SimpleDateFormat(getDatePattern());
			returnValue = df.format(aDate);
		}
		return (returnValue);
	}

	public static final Date convertStringToDate(String aMask, String strDate) {
		SimpleDateFormat df = null;
		Date date = null;
		df = new SimpleDateFormat(aMask);
		if (log.isDebugEnabled()) {
			// log.debug("converting '" + strDate + "' to date with mask '" +
			// aMask + "'");
		}
		try {
			date = df.parse(strDate);
		} catch (ParseException pe) {
			log.error("ParseException: " + pe);
		}
		return (date);
	}

	public static String getTimeNow(Date theTime) {
		return getDateTime(timePatternSim, theTime);
	}

	public static String getTimeNowNoSec(Date theTime) {
		return getDateTime(timePatternSimSec, theTime);
	}

	public static String getDateTimeNow(Date theTime) {
		return getDateTime(timePattern, theTime);
	}

	public static String getTimeEndNow(Date theTime) {
		return getDateTime(timePatternEnd, theTime);
	}

	public static String getTimeNowString(Date theTime) {
		return getDateTime(timePatternString, theTime);
	}
	
	public static String getYearMonthNowString(Date theTime) {
		return getDateTime(timePatternMonth, theTime);
	}

	public static Calendar getToday() throws ParseException {
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat(getDatePattern());
		String todayAsString = df.format(today);
		Calendar cal = new GregorianCalendar();
		cal.setTime(convertStringToDate(todayAsString));
		return cal;
	}

	public static final String getDateTime(String aMask, Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";
		if (aDate == null) {
			log.error("aDate is null!");
		} else {
			df = new SimpleDateFormat(aMask);
			returnValue = df.format(aDate);
		}
		return (returnValue);
	}

	public static final String convertDateToString(Date aDate) {
		return getDateTime(getDatePattern(), aDate);
	}

	public static Date convertStringToDate(String strDate) {
		Date aDate = null;
		if (log.isDebugEnabled()) {
			log.debug("converting date with pattern: " + getDatePattern());
		}
		aDate = convertStringToDate(getDatePattern(), strDate);
		return aDate;
	}

	public static Date getTodayFrom() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static Date getTodayTo() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}

	public static Date getCurrentTime() {
		return new Date();
	}

	public static String getCurSeaDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int month = cal.get(cal.MONTH) + 1;
		int year = cal.get(cal.YEAR);
		if (month >= 1 && month <= 3) {
			return year + "-01-01";
		} else if (month >= 4 && month <= 6) {
			return year + "-04-01";
		} else if (month >= 7 && month <= 9) {
			return year + "-07-01";
		} else {
			return year + "-10-01";
		}
	}

	/**
	 * Desc: Date转换为中文格式
	 * 
	 * @param date
	 *            日期
	 * @return 中文日期
	 * @version 1.0
	 * @author luhualiang@ieds.com.cn
	 * @update 2012-7-23 上午10:31:36
	 */
	public static String DateToCN(Date date) {
		if (null == date || "".equals(date)) {
			return null;
		}
		String[] CN = { "〇", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		StringBuffer cn = new StringBuffer();
		String year = String.valueOf(calendar.get(Calendar.YEAR));
		for (int i = 0; i < year.length(); i++) {
			cn.append(CN[year.charAt(i) - 48]);
		}
		cn.append("年");
		int mon = calendar.get(Calendar.MONTH) + 1;
		if (mon < 10) {
			cn.append(CN[mon]);
		} else if (mon < 20) {
			if (mon == 10) {
				cn.append("十");
			} else {
				cn.append("十").append(CN[mon % 10]);
			}
		}
		cn.append("月");
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		if (day < 10) {
			cn.append(CN[day]);
		} else if (day < 20) {
			if (day == 10) {
				cn.append("十");
			} else {
				cn.append("十").append(CN[day % 10]);
			}
		} else if (day < 30) {
			if (day == 20) {
				cn.append("二十");
			} else {
				cn.append("二十").append(CN[day % 10]);
			}
		} else {
			cn.append("三十").append(CN[day % 10]);
		}
		cn.append("日");
		return cn.toString();
	}

	public static Date getAfterWeek(Date date, int seed) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(cal.DAY_OF_MONTH, 1 * seed * 7);
		// System.out.println(cal.getTime());
		// String dateStr = FormatDateTime.date2String(cal.getTime(),
		// "yyyy-MM-dd");
		return cal.getTime();

	}
	
	public static Date getAfterDay(Date date, int seed) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(cal.DAY_OF_MONTH, seed);
		// System.out.println(cal.getTime());
		// String dateStr = FormatDateTime.date2String(cal.getTime(),
		// "yyyy-MM-dd");
		return cal.getTime();

	}

	public static Date getAfterMonth(Date date, int seed) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, 1 * seed);
		// System.out.println(cal.getTime());
		// String dateStr = FormatDateTime.date2String(cal.getTime(),
		// "yyyy-MM-dd");
		return cal.getTime();

	}

	public static String dateToCnDate(String date) {
		String result = "";
		String[] cnDate = new String[] { "○", "一", "二", "三", "四", "五", "六",
				"七", "八", "九" };
		String ten = "十";
		String[] dateStr = date.split("-");
		for (int i = 0; i < dateStr.length; i++) {
			for (int j = 0; j < dateStr[i].length(); j++) {
				String charStr = dateStr[i];
				String str = String.valueOf(charStr.charAt(j));
				if (charStr.length() == 2) {
					if (charStr.equals("10")) {
						result += ten;
						break;
					} else {
						if (j == 0) {
							if (charStr.charAt(j) == '1')
								result += ten;
							else if (charStr.charAt(j) == '0')
								result += "";
							else
								result += cnDate[Integer.parseInt(str)] + ten;
						}
						if (j == 1) {
							if (charStr.charAt(j) == '0')
								result += "";
							else
								result += cnDate[Integer.parseInt(str)];
						}
					}
				} else {
					result += cnDate[Integer.parseInt(str)];
				}
			}
			if (i == 0) {
				result += "年";
				continue;
			}
			if (i == 1) {
				result += "月";
				continue;
			}
			if (i == 2) {
				result += "日";
				continue;
			}
		}
		return result;
	}

	public static String getWeekOfDate(Date date) {
		String[] weekDaysName = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五",
				"星期六" };
		String[] weekDaysCode = { "0", "1", "2", "3", "4", "5", "6" };
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		return weekDaysName[intWeek];
	}

	public static Date getAfterYear(Date date, int seed) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, 1 * seed);
		// System.out.println(cal.getTime());
		// String dateStr = FormatDateTime.date2String(cal.getTime(),
		// "yyyy-MM-dd");
		return cal.getTime();

	}

	public static int getYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		// int month = cal.get(cal.MONTH)+1;
		int year = cal.get(cal.YEAR);
		return year;
	}

	public static void main(String[] args) {
		int last = getLastDayOfMonth(2013,2);
		System.out.println(last);
		//String s = UUID.randomUUID().toString();
		//System.out.println(s);
		//System.out.println(s.length());
	}

	public static int getMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int month = cal.get(cal.MONTH) + 1;
		// int year = cal.get(cal.YEAR);
		return month;
	}

	public static int getLastDayOfMonth(Integer year, Integer month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DATE, 1);
		cal.add(Calendar.MONTH, 1);
		cal.add(Calendar.DATE, -1);
		return cal.get(Calendar.DAY_OF_MONTH);

	}

}

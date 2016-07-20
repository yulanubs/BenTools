package com.bentools.utils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static Calendar todayCal = Calendar.getInstance();

	static {
		// 今天
		todayCal.set(Calendar.YEAR, todayCal.get(Calendar.YEAR));
		todayCal.set(Calendar.MONTH, todayCal.get(Calendar.MONTH));
		todayCal.set(Calendar.DAY_OF_MONTH, todayCal.get(Calendar.DAY_OF_MONTH));
		// Calendar.HOUR——12小时制的小时数 Calendar.HOUR_OF_DAY——24小时制的小时数
		todayCal.set(Calendar.HOUR_OF_DAY, 0);
		todayCal.set(Calendar.MINUTE, 0);
		todayCal.set(Calendar.SECOND, 0);
		todayCal.set(Calendar.MILLISECOND, 0);

	}

	/**
	 * 日期比较 判断是否今天，昨天，或以前（0,1,2）
	 * 
	 * @param date
	 * @return
	 */
	public static int compareDate(String date) {
		long today = todayCal.getTimeInMillis();
		long yestoday = todayCal.getTimeInMillis() - 24 * 60 * 60 * 1000;
		try {
			Calendar time = stringToCalendar(date);
			if (time != null) {
				if (time.getTimeInMillis() >= todayCal.getTimeInMillis()) {
					return 0;
				} else if (time.getTimeInMillis() >= (todayCal
						.getTimeInMillis() - 24 * 60 * 60 * 1000)) {
					return 1;
				} else {
					return 9999;
				}
			}
		} catch (Exception ex) {

		}
		return 9999;
	}

	public static Calendar stringToCalendar(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = null;
		Calendar calendar = Calendar.getInstance();
		try {
			date = sdf.parse(str);
			calendar.setTime(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			try {
				date = sdf1.parse(str);
				calendar.setTime(date);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		return calendar;
	}

	public static String dateFormatYMDHM(String str) {
		String formattedTime = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date;
		try {
			date = sdf.parse(str);
			formattedTime = sdf.format(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return str;
		}
		return formattedTime;
	}

	/**
	 * 时间转换为字符串 yyyy-MM-dd
	 * @param strDate
	 * @return
	 */
	public static String dateFormatShort(String strDate) {
		// 准备第一个模板，从字符串中提取出日期数字
		String pat1 = "yyyy-MM-dd";
		// 准备第二个模板，将提取后的日期数字变为指定的格式
		String pat2 = "yyyy/MM/dd";
		SimpleDateFormat sdf1 = new SimpleDateFormat(pat1); // 实例化模板对象
		SimpleDateFormat sdf2 = new SimpleDateFormat(pat2); // 实例化模板对象
		Date d = null;
		try {
			d = sdf1.parse(strDate); // 将给定的字符串中的日期提取出来
		} catch (Exception e) { // 如果提供的字符串格式有错误，则进行异常处理
			e.printStackTrace(); // 打印异常信息
		}
		return sdf2.format(d);

	}


	public static String dateFormatYMDHMS(long time) {
		String formattedTime = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(time);

		formattedTime = sdf.format(date);
		return formattedTime;
	}
	// public static String dateFormatHHMM(String str){
	// String formattedTime = "";
	// SimpleDateFormat sdf= new SimpleDateFormat("HH:mm");
	// Date date;
	// try {
	// date = sdf.parse(str);
	// formattedTime = sdf.format(date);
	// } catch (ParseException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// return str;
	// }
	// return formattedTime;
	// }

}

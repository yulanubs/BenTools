package com.bentools.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.annotation.SuppressLint;

/**
 * 
 * @author Administrator
 * 
 */
public class UtilDateFormat {

	/**
	 * 将时间转换成格式化的字符串
	 * 
	 * @param time单位毫秒
	 * @return
	 */
	public static String transformToTimeStr(int time) {
		time /= 1000;
		int minute = time / 60;
		int hour = minute / 60;
		if (hour == 0) {
			hour = 00;
		}
		int second = time % 60;
		minute %= 60;
		// return String.format("%02d:%02d:%02d", hour, minute, second);
		return String.format("%02d:%02d", minute, second);
	}

	/**
	 * 将时间转换成格式化的字符串
	 * 
	 * @param time单位为秒
	 * @return
	 */
	public static String transformToTimeSECStr(int time) {
		int minute = time / 60;
		int hour = minute / 60;
		if (hour == 0) {
			hour = 00;
		}
		int second = time % 60;
		minute %= 60;
		// return String.format("%02d小时%02d分钟", hour, minute);
		return String.format("%02d:%02d:%02d", hour, minute, second);
		// return String.format("%02d:%02d", minute, second);
	}

	@SuppressWarnings("deprecation")
	@SuppressLint("SimpleDateFormat")
	public static String transformToDate(String dateStop) {
		String date="";
		DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date= format.format(format2.parse(dateStop));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 返回时间点距离当前时间的剩余天数
	 * 
	 * @param dateStop
	 *            到期时间点
	 * @return 天数 int 类型
	 */
	@SuppressWarnings("deprecation")
	@SuppressLint("SimpleDateFormat")
	public static long transformToDiffDays(String dateStop) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		Date d1 = new Date();
		Date d2 = null;
		long diffDays = 999;

		try {
			// d1 = format.parse(dateStart);
			d2 = format.parse(dateStop);
			// if (d2.getYear() == d1.getYear()) {
			long diff = d2.getTime() - d1.getTime();
			diffDays = diff / (24 * 60 * 60 * 1000);
			// }

		} catch (Exception e) {
			e.printStackTrace();
		}

		return diffDays;
	}
	/**
	 * 根据提供的两个时间字符串，算出其之间的时间间隔
	 * */
	public static long vipDays(String dateStop, String dateStart) {
		if(!StrUtil.isEmpty(dateStart) && !StrUtil.isEmpty(dateStop))
		{
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

			Date d1 = null;
			Date d2 = null;
			long diffDays = 999;

			try {
				 d1 = format.parse(dateStart);
				d2 = format.parse(dateStop);
				// if (d2.getYear() == d1.getYear()) {
				long diff = d2.getTime() - d1.getTime();
				diffDays = diff / (24 * 60 * 60 * 1000);
				// }

			} catch (Exception e) {
				e.printStackTrace();
			}

			return diffDays;
		}
		return 0;
	}

	public static String getDay(String cTime) {
		String result = "-1";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			long ctime = sdf.parse(cTime).getTime();

			// 换成服务器返回系统时间
			Calendar now = Calendar.getInstance();
			long ms = 1000 * (now.get(Calendar.HOUR_OF_DAY) * 3600
					+ now.get(Calendar.MINUTE) * 60 + now.get(Calendar.SECOND));// 毫秒数
			long ms_now = now.getTimeInMillis();

			if (ms_now - ctime < ms + 24 * 3600 * 1000) {
				result = "今天";
				// + " " + getWeek(cTime);
			} else if (ms_now - ctime < (ms + 24 * 3600 * 1000 * 2)) {
				result = "昨天";
				// + " " + getWeek(cTime);
			} else if (ms_now - ctime < (ms + 24 * 3600 * 1000 * 3)) {
				result = "前天";
				// + " " + getWeek(cTime);
			} else if (ms_now - ctime < (ms + 24 * 3600 * 1000 * 7)) {
				result = "一周内";
				// + " " + cTime;
			} else if (ms_now - ctime < (ms + 24 * 3600 * 1000 * 30)) {
				result = "一月内";
				// + " " + cTime;
			} else {
				result = "更早";
				// + " " + cTime;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String getWeek(String cTime) {
		String result = "";
		String showDate = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			long ctime = sdf.parse(cTime).getTime();
			Date date = null;
			date = sdf.parse(cTime);
			Calendar cd = Calendar.getInstance();
			cd.setTime(date);
			int mydate = cd.get(Calendar.DAY_OF_WEEK); // 获取指定日期转换成星期几
			switch (mydate) {
			case 1:
				showDate = "日";
				break;
			case 2:
				showDate = "一";
				break;
			case 3:
				showDate = "二";
				break;
			case 4:
				showDate = "三";
				break;
			case 5:
				showDate = "四";
				break;
			case 6:
				showDate = "五";
				break;
			case 0:
				showDate = "六";
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "星期" + showDate;
	}
}

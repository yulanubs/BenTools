package com.bentools.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SellerUtils {

	private static SimpleDateFormat mFromate = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss", Locale.CHINA);

	public static String getApproximateTime(String timeMillions) {
		Date date = null;
		try {
			date = mFromate.parse(timeMillions);
		} catch (ParseException e) {
			e.printStackTrace();
			date = new Date();
		}
		
		long nowTime = System.currentTimeMillis();
		long orderTime = date.getTime();
		long offset = nowTime - orderTime;
		long sec = offset/1000;
		if(sec<=60)
			return String.valueOf(sec) + "秒前";
		long min = sec/60;
		if(min<=60)
			return String.valueOf(min) + "分钟前";
		long hour = min/60;
		if(hour<=24)
			return String.valueOf(hour) + "小时前";
		long day = hour/24;
		if(day<=30)
			return String.valueOf(day) + "天前";
		long mouth = day/30;
		if(mouth<=12)
			return String.valueOf(mouth) + "月前";
		long year = mouth/12;
		return String.valueOf(year) + "年前";
	}
}

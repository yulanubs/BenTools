package com.bentools.utils;

import java.text.SimpleDateFormat;
import java.util.Date;


/** 
 * @author 作者名 易皇星
 * @email  邮箱名 15095887072@163.com
 * @time   2016-5-18 2016-05
 * @TODO 缘分是本书，翻得不经意会错过，读得太认真会流泪！
 */

public class TimeUtils {
	public static String getTime(){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String str = sdf.format(date);
		return str;
		
	}
}

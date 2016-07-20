package com.bentools.utils;

import com.google.gson.Gson;

/** 
 * @author 作者名 易皇星
 * @email  邮箱名 15095887072@163.com
 * @time   2016-5-16 2016-05
 * @TODO 缘分是本书，翻得不经意会错过，读得太认真会流泪！
 */

public class GsonUtils {
	
	
	private static Gson gson = new Gson();
	public static Object getJsonObject(String isr,Class obj){
		gson = new Gson();
		if (null != isr) {
			return (Object) gson.fromJson(isr, obj);  
		}
		return null;
	}
	public static String toJsonStr(Object obj){  
		return gson.toJson(obj, obj.getClass());
	}


}

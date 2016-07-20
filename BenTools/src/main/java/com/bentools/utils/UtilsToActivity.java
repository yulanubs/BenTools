package com.bentools.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * 
 * @classname:MY_ToActivity <BR>
 * @describe：Activity跳转工具类<BR>
 * @author: 朱勋康
 * @extends：<BR>
 * @version:1.0
 * @date:2015-4-18 上午10:41:00
 */
public class UtilsToActivity {
	/**
	 * 
	 * 方法名：toActiviyy <BR>
	 * 此方法描述的是：有参数的Activity跳转 <BR>
	 * 
	 * @param context
	 *            上下文
	 * @param ainfo
	 *            需要封装的对象
	 * @param cl
	 *            目标Activity
	 */
	public static void toActiviyy(Context context, Class cl, Bundle ainfo) {
		Intent intents = new Intent(context, cl);
		//封装数据到Intent对象中
		intents.putExtras(ainfo);
		//启动Intent
		context.startActivity(intents);
		//销毁当前Activity
		context.fileList();
	}

	/**
	 * 
	 * 方法名：toActiviyy <BR>
	 * 此方法描述的是：无参数的Activity跳转 <BR>
	 * 
	 * @param context
	 *            上下文
	 * @param cl
	 *            目标Activity
	 */
	public static void toActiviy(Context context, Class cl) {
		Intent intents = new Intent(context, cl);
		
		//启动Intent
		context.startActivity(intents);
		//销毁当前Activity
		
	}
	/**
	 * 
	 * 方法名：toActiviyy <BR>
	 * 此方法描述的是：无参数的Activity跳转 <BR>
	 * 
	 * @param context
	 *            上下文
	 * @param cl
	 *            目标Activity
	 */
	public static void toActiviyy(Context context, Class cl,String UserCard) {
		Intent intents = new Intent(context, cl);
		intents.putExtra("UserCard", UserCard);
		//启动Intent
		context.startActivity(intents);
		//销毁当前Activity
	}
}

package com.bentools.utils;

import android.content.Context;
import android.widget.Toast;
/**
 * 
	 * @classname:UtilsToast<BR>
     * @describe：土司工具类<BR>
     * @author: 朱勋康
	 * @extends：<BR>
	 * @implements：<UtilInterfaceBR>
     * @version:1.0 
     * @date:2015-4-29 下午3:27:47
 */
public class UtilsToast {
	/**
	 * 
	*方法名： myToast <BR>
	* 此方法描述的是：  土司的方法 <BR>
	* @param context 上下文
	* @param msg 需要土司的消息
	 */
	
	public static void myToast(Context context,String msg){
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}

	
}

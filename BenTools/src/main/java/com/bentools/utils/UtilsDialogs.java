package com.bentools.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;



/**
 * 
 * @classname:MyDialogs <BR>
 * @describe：自定义显示提醒信息对话框工具类<BR>
 * @author: 朱勋康
 * @extends：obj<BR>
 * @version:1.0
 * @date:2015-4-17 下午2:43:33
 */
public class UtilsDialogs  {
	/**
	 * 方法名： <BR>
	 * 此方法描述的是：自定义一个选项信息的对话框 <BR>
	 * 
	 * @param msg
	 *            需要显示的信息
	 * @param context 上下文
	 */
	public  static void showDialog(String msg,Context context) {
		//实例化一个对话框对象
		AlertDialog.Builder  builder =new AlertDialog.Builder(context);
	builder.setMessage(msg).setCancelable(false).setPositiveButton("确认", 
			new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int id) {
					
				}
			});
	//创建对话框
	AlertDialog alert=builder.create();
	//显示对话框
	alert.show();
			}
	}


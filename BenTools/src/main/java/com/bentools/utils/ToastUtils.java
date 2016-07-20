package com.bentools.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {

	private static Toast toast = null;

	/**
	 * 显示Toast (short)
	 * @param context
	 * @param msg
	 */
	public static void ShowToast_short(Context context, String msg) {
		if(context==null)
			return;
		if (toast == null) {
			toast = Toast.makeText(context.getApplicationContext(), msg,
					Toast.LENGTH_SHORT);
		} else {
			toast.setText(msg);
		}
		toast.show();
	}

	/**
	 * 显示Toast (long)
	 * @param context
	 * @param msg
	 */
	public static void ShowToast_long(Context context, String msg) {
		if(context==null)
			return;
		if (toast == null) {
			toast = Toast.makeText(context.getApplicationContext(), msg,
					Toast.LENGTH_LONG);
		} else {
			toast.setText(msg);
		}
		toast.show();
	}

}

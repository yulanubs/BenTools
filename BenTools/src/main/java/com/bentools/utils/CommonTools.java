package com.bentools.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bentools.R;

/**
 * 
 * @ClassName: <BR>
 * @Describe：<BR>
 * @Author: 朱勋康
 * @Extends：<BR>
 * @Implements：<BR>
 * @Version:1.0
 * @date:2015-5-12 上午11:53:26
 */
public class CommonTools  {

	/**
	 * 
	 * 方法名：showShortToast <BR>
	 * 此方法描述的是：自定义吐司 <BR>
	 * 
	 * @param context
	 *            上下文
	 * @param message
	 *            吐司的信息
	 */
	public static void showShortToast(Context context, String message) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.custom_toast, null);
		TextView text = (TextView) view.findViewById(R.id.toast_message);
		text.setText(message);
		Toast toast = new Toast(context);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.BOTTOM, 0, 300);
		toast.setView(view);
		toast.show();
	}

	/**
	 * 
	 * 方法名：dip2px <BR>
	 * 此方法描述的是：根据手机分辨率从dp转成px <BR>
	 * 
	 * @param context
	 * @param dpValue
	 * @return
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 
	 * 方法名：px2dip <BR>
	 * 此方法描述的是：根据手机的分辨率从 px(像素) 的单位 转成为 dp <BR>
	 * 
	 * @param context
	 * @param pxValue
	 * @return
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f) - 15;
	}

	/**
	 * 
	*方法名：getStatusBarHeight  <BR>
	* 此方法描述的是： 获取手机状态栏高度  <BR>
	* @param context
	* @return
	 */
	public static int getStatusBarHeight(Context context) {
		Class<?> c = null;
		Object obj = null;
		java.lang.reflect.Field field = null;
		int x = 0;
		int statusBarHeight = 0;
		try {
			c = Class.forName("com.android.internal.R$dimen");
			obj = c.newInstance();
			field = c.getField("status_bar_height");
			x = Integer.parseInt(field.get(obj).toString());
			statusBarHeight = context.getResources().getDimensionPixelSize(x);
			return statusBarHeight;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return statusBarHeight;
	}

}

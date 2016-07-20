package com.bentools.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/** 
 * @author 作者名 易皇星
 * @email  邮箱名 15095887072@163.com
 * @time   2016-5-21 2016-05
 * @TODO 缘分是本书，翻得不经意会错过，读得太认真会流泪！
 */

public class ScreenUtils {

	
	 private ScreenUtils() {

	    }

	    /**
	     * 获得屏幕高度
	     * 
	     * @param context
	     * @return
	     */

	    public static int getScreenWidth(Context context) {
	        WindowManager wm = (WindowManager) context
	                .getSystemService(Context.WINDOW_SERVICE);
	        DisplayMetrics outMetrics = new DisplayMetrics();
	        wm.getDefaultDisplay().getMetrics(outMetrics);
	        return outMetrics.widthPixels;
	    }

	    /**
	     * 获得屏幕宽度
	     * 
	     * @param context
	     * @return
	     */
	    public static int getScreenHeight(Context context) {
	        WindowManager wm = (WindowManager) context
	                .getSystemService(Context.WINDOW_SERVICE);
	        DisplayMetrics outMetrics = new DisplayMetrics();
	        wm.getDefaultDisplay().getMetrics(outMetrics);
	        return outMetrics.heightPixels;
	    }
}

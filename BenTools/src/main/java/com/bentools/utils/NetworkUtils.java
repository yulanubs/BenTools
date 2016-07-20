package com.bentools.utils;

import java.util.List;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/**
 * 
 * @ClassName:NetworkUtils <BR>
 * @Describe：判断当前网络的类型，以及网络是否可用<BR>
 * @Author: 朱勋康
 * @Extends：UtilClass<BR>
 * @Implements：<BR>
 * @Version:1.0
 * @date:2015-5-12 下午12:09:50
 */
public class NetworkUtils  {

	/**
	 * 
	 * 方法名： isNetworkAvailable <BR>
	 * 此方法描述的是： 网络是否可用 <BR>
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {
		} else {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * 
	 * 方法名：networkStateTips <BR>
	 * 此方法描述的是：网络连接提示 <BR>
	 * 
	 * @param context
	 */
	public static void networkStateTips(Context context) {
		if (!isNetworkAvailable(context)) {
			CommonTools.showShortToast(context, "网络故障，请先检查网络连接");
		}
	}

	/**
	 * 
	 * 方法名：isGpsEnabled <BR>
	 * 此方法描述的是： Gps是否打开 <BR>
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isGpsEnabled(Context context) {
		LocationManager locationManager = ((LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE));
		List<String> accessibleProviders = locationManager.getProviders(true);
		return accessibleProviders != null && accessibleProviders.size() > 0;
	}

	/**
	 * 
	 * 方法名：isWifiEnabled <BR>
	 * 此方法描述的是： wifi是否打开<BR>
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isWifiEnabled(Context context) {
		ConnectivityManager mgrConn = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		TelephonyManager mgrTel = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		return ((mgrConn.getActiveNetworkInfo() != null && mgrConn
				.getActiveNetworkInfo().getState() == NetworkInfo.State.CONNECTED) || mgrTel
				.getNetworkType() == TelephonyManager.NETWORK_TYPE_UMTS);
	}

	/**
	 * 
	 * 方法名： isWifi <BR>
	 * 此方法描述的是： 判断当前网络是否是wifi网络
	 * if(activeNetInfo.getType()==ConnectivityManager.TYPE_MOBILE) { //判断3G网 <BR>
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isWifi(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
		if (activeNetInfo != null
				&& activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * 方法名： is3G <BR>
	 * 此方法描述的是：判断当前网络是否是3G网络 <BR>
	 * 
	 * @param context
	 * @return
	 */
	public static boolean is3G(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
		if (activeNetInfo != null
				&& activeNetInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
			return true;
		}
		return false;
	}
}

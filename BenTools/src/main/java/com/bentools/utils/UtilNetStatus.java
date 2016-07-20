package com.bentools.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;


/**
 * 用于检测网络状态，并返回检测结果
 */
public class UtilNetStatus {


	private static ConnectivityManager manager = null;

	public UtilNetStatus(Context context) {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 是否有网络
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isHasConnection(Context context) {


		if (manager == null) {
			manager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
		}
		NetworkInfo networkInfo = manager.getActiveNetworkInfo();
		return (networkInfo != null && networkInfo.isAvailable());
	}

	/**
	 * 是否有wifi网络
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isWifiConnection(Context context) {
		if (manager == null) {
			manager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
		}
		NetworkInfo info = manager
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		return (info != null && info.isConnectedOrConnecting());
	}

	/**
	 * 是否有gprs网络
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isGPRSConnection(Context context) {
		if (context == null) {
			return false;
		}
		if (manager == null) {
			manager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
		}
		if (manager == null) {
			return false;
		}

		NetworkInfo networkInfo = manager
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		if (networkInfo == null) {
			return false;
		}

		State info = networkInfo.getState();
		if (info != null
				&& (info.toString().equals("CONNECTED") || info.toString()
						.equals("CONNECTING"))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @return 
	 *         判断网络是否可用，并返回网络类型，ConnectivityManager.TYPE_WIFI，ConnectivityManager
	 *         .TYPE_MOBILE，不可用返回-1
	 */
	public static final int getNetWorkConnectionType(Context context) {
		if (manager == null) {
			manager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
		}
		NetworkInfo wifiNetworkInfo = manager
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		NetworkInfo mobileNetworkInfo = manager
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

		if (wifiNetworkInfo != null && wifiNetworkInfo.isAvailable()) {
			return ConnectivityManager.TYPE_WIFI;
		} else if (mobileNetworkInfo != null && mobileNetworkInfo.isAvailable()) {
			return ConnectivityManager.TYPE_MOBILE;
		} else {
			return -1;
		}

	}

}

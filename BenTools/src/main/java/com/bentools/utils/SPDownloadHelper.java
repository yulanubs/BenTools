package com.bentools.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import android.util.Log;

public class SPDownloadHelper {
	

	private static final String DOWNLOAD_RECORDS = "DownloadRecords";

	private SPDownloadHelper() {
		
	}
	
	public static void record(Context context, String id, String path) {
		if (TextUtils.isEmpty(id) || TextUtils.isEmpty(path)) {
			return;
		}
		
		Log.i("SNK", id + path);
		SharedPreferences sp = context.getSharedPreferences(DOWNLOAD_RECORDS, Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString(id, path);
		editor.apply();
	}
	
	public static String getPath(Context context, String id) {
		if (TextUtils.isEmpty(id)) {
			return "";
		} 
		SharedPreferences sp = context.getSharedPreferences(DOWNLOAD_RECORDS, Context.MODE_PRIVATE);
		return sp.getString(id, "");
	}
	

}

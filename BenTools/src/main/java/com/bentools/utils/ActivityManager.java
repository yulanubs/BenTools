package com.bentools.utils;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
/**
 * 
	 * @ClassName: ActivityManager<BR>
     * @Describe：Activity管理器<BR>
     * @Author: Jekshow
	 * @Extends：<BR>
     * @Version:1.0 
     * @date:2016-6-15 上午9:58:10
 */
public class ActivityManager {
	private List<Activity> mList = new ArrayList<Activity>();
	private static ActivityManager instance;

	private ActivityManager() {
	}

	public synchronized static ActivityManager getInstance() {
		if (null == instance) {
			instance = new ActivityManager();
		}
		return instance;
	}

	public void addActivity(Activity activity) {
		mList.add(activity);
	}

	public void exit() {
		try {
			for (Activity activity : mList) {
				if (activity != null)
					activity.finish();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
	}
}

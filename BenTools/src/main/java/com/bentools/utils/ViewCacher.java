package com.bentools.utils;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import android.view.View;

/**
 * 
 * @ClassName:ViewCacher <BR>
 * @Describe：View缓存工具类<BR>
 * @Author: Jekshow
 * @Extends：<BR>
 * @Version:1.0
 * @date:2016-6-15 上午10:03:23
 */
public enum ViewCacher {
	INSTANCE;

	private Map<String, WeakReference<View>> cache = new HashMap<String, WeakReference<View>>();

	public void cacheView(View v) {
		if (v == null) {
			return;
		}
		String key = v.getClass().getName();
		if (cache.containsKey(key)) {
			WeakReference<View> vc = cache.remove(key);
			vc.clear();
		}
		WeakReference<View> vc = new WeakReference<View>(v);
		cache.put(key, vc);
	}

	public View getView(String viewName) {
		WeakReference<View> vc = cache.get(viewName);
		if (vc == null) {
			return null;
		}
		return vc.get();
	}

	public void release() {
		Set<String> keys = cache.keySet();
		Iterator<String> keyIt = keys.iterator();
		while (keyIt.hasNext()) {
			String key = keyIt.next();
			WeakReference<View> c = cache.get(key);
			if (c != null) {
				c.clear();
			}
		}
		cache.clear();
	}

}

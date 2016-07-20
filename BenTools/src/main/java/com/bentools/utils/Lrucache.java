package com.bentools.utils;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.util.LruCache;
/**
 * 
	 * @ClassName: <BR>
     * @Describe：<BR>
     * @Author: Jekshow
	 * @Extends：<BR>
     * @Version:1.0 
     * @date:2016-6-15 上午10:11:18
 */
public class Lrucache {
	private static LruCache<Object, Drawable> uleCache;
	private static int MaxBuffer;
	private Context context;

	public Lrucache(Context context) {
		this.context = context;
		getMemeInfo();
		getLrucache();
	}

	@SuppressLint("NewApi")
	private void getMemeInfo() {
		ActivityManager activityManager = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		int processMeme = activityManager.getMemoryClass();
		MaxBuffer = processMeme * 1024 * 1024 / 6;
	}

	private void getLrucache() {
		if (uleCache == null)
			uleCache = new LruCache<Object, Drawable>(MaxBuffer) {
				protected int sizeOf(Object key, Drawable value) {
					Bitmap bp = ((BitmapDrawable) value).getBitmap();
					int i = bp.getRowBytes() * bp.getHeight();
					return i;
				};
			};
	}

	public void addDrawable(Object key, Drawable value) {
		
		if(key==null||value==null)
		{
			return;
		}
		
		uleCache.put(key, value);
		BenLog.debug(this.toString(), "addDrawable" + uleCache.size());
		BenLog.debug(this.toString(),
				"addDrawable createCount :" + uleCache.hitCount());
		BenLog.debug(this.toString(),
				"addDrawable evictionCount :" + uleCache.evictionCount());
	}

	public Drawable getDrawable(Object key) {
		BenLog.debug(this.toString(), "getDrawable" + uleCache.size());
		return uleCache.get(key);
	}

	public void removeKey(Object key) {
		uleCache.remove(key);
	}
}
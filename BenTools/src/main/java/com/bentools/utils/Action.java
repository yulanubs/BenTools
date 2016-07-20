package com.bentools.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;


public class Action implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6248767504210311030L;

	public static final int ACTION_TYPE_OPENURL = 1;
	public static final int ACTION_TYPE_RUN = 2;
	public static final int ACTION_TYPE_RUNWGT = 3;

	public static final String ULE_ACTION = "ULE_Action";

	/* 类名&参数名|参数值#参数名|参数值 */

	public String actyName;
	public String wgtClass;

	public Map<String, Object> parameters = new HashMap<String, Object>();

	// 优惠券提示信息
	public String mPromtTitle;
	public String mPromtStr;
	public String mCouponType;

	private String TAG = "Action";

	public Action() {
		actyName = "";
		wgtClass = "";
	}

	public Action(Context context, String act, boolean fromPush)
			throws Exception {
		if (fromPush) {
			String newAct = act.replace("**", "&");
			createAction(context, newAct);
		} else {
			createAction(context, act);
		}

	}

	private void createAction(Context context, String act) throws Exception {
		String packageName = context.getPackageName();

		String[] main = act.split("&");
		if (main.length == 0) {
			throw new Exception();
		}
		String[] className = main[0].split("#");
		if (className.length == 0) {
			throw new Exception();
		}
		if (className[0].startsWith(".")) {
			actyName = packageName + className[0];
		} else {
			actyName = packageName + "." + className[0];
		}

		if (className.length > 1) {
			if (className[1].startsWith(".")) {
				wgtClass = packageName + className[1];
			} else {
				wgtClass = packageName + "." + className[1];
			}

		} else {
			wgtClass = "";
		}
		if (main.length > 1) {
			String[] ps = main[1].split("#");
			for (int i = 0; i < ps.length; i++) {
				int index = ps[i].indexOf("|");
				if (index > 0) {
					String key = ps[i].substring(0, index);
					String value = ps[i].substring(index + 1);
					parameters.put(key, value);
				}
			}
		}
	}

	public Action(Context context, String act) throws Exception {

		createAction(context, act);
	}

	//just use for PersonalCenter 20150104
	public static final String SPLIT_CHAR_FIRST = "&&";
	public static final String SPLIT_CHAR_SECOND = "##";
	public static final String SPLIT_CHAR_THIRD = "::";
}

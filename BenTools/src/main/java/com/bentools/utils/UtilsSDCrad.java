package com.bentools.utils;

import android.os.Environment;

/**
 * 
 * @classname: UtilsSDCrad<BR>
 * @describe：Android的SD卡相关工具栏<BR>
 * @author: 朱勋康
 * @extends：UtilClass<BR>
 * @implements：<BR>
 * @version:1.0
 * @date:2015-5-6 下午4:08:15
 */
public class UtilsSDCrad  {
	// 重写构造方法，构造方法私有化，单例模式
	private UtilsSDCrad() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * 方法名： getUtilsSDCrad <BR>
	 * 此方法描述的是：对外提供一个公共的方法获得当前类的实例 <BR>
	 * 
	 * @return UtilsSDCrad类的实例
	 */
	public static UtilsSDCrad getUtilsSDCrad() {
		return new UtilsSDCrad();

	}

	/**
	 * 
	 * 方法名： isSDCrad <BR>
	 * 此方法描述的是： 判断SD卡是否存在 <BR>
	 * 
	 * @return 返回true表示存在，false表示不存在
	 */
	public boolean isSDCrad() {
		// 判断SD卡是否存在
		return Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED);
	}

	/**
	 * 
	 * 方法名：isSDCrads <BR>
	 * 此方法描述的是：提供静态方法判断SD卡是否存在 <BR>
	 * 
	 * @return 返回true表示存在，false表示不存在
	 */
	public static boolean isSDCrads() {
		return getUtilsSDCrad().isSDCrad();

	}
}

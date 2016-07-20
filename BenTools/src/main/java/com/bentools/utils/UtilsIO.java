package com.bentools.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * 
 * @classname:UtilsIO <BR>
 * @describe：输入输出流（IO）相关工具类<BR>
 * @author: 朱勋康
 * @extends：<BR>
 * @implements：<BR>
 * @version:1.0
 * @date:2015-5-6 下午9:38:34
 */
public class UtilsIO  {
	// 重写构造方法，构造方法私有化，单例模式
	private UtilsIO() {
		super();
	}

	/**
	 * 
	 * 方法名：getUtilIO <BR>
	 * 此方法描述的是：公共方法，获得一个getUtilIO实例 <BR>
	 * 
	 * @return UtilsIO实例对象
	 */
	public static UtilsIO getUtilIO() {
		return new UtilsIO();

	}

	// 重写将输入流转换为字符串的方法
	public String parseIsToString(InputStream is) throws Exception {
		// 实例化一个字节数组输出流
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int len = 0;
		// 实例化一个字节数据
		byte buffer[] = new byte[1024 * 2];
		while ((len = is.read(buffer)) != -1) {
			// 写入数据到字节数组输出流中
			baos.write(buffer, 0, len);
		}
		String msg = baos.toString();
		return msg.trim();
	}
	/**
	 * 
	*方法名： parseIsToStrings <BR>
	* 此方法描述的是：将输入流转换为字符串静态方法   <BR>
	* @param is 输入流
	* @return 转换后的字符串
	* @throws Exception
	 */
	public static String parseIsToStrings(InputStream is) throws Exception{
		//调用parseIsToString方法获得一个以输入流转换的字符串
		return getUtilIO().parseIsToString(is);
		
	}
}

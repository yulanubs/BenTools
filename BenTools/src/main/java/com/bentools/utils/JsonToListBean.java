package com.bentools.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 
 * @classname:JsonToListBean <BR>
 * @describe：Json转换为ListBean<BR>
 * @author: 朱勋康
 * @extends：UtilClass<BR>
 * @version:1.0
 * @date:2015-5-15 下午1:44:59
 */
public class JsonToListBean  {
	// 获得服务器上的流，转为json，转为List<Bean>->ListView
	public static <T> List<T> getAllUsersFromJson(InputStream is)
			throws Exception {
		Gson gson = new Gson();
		List<T> lists = new ArrayList<T>();
		// 将IO流转换为字符串
		String json = parseToJson(is);
		// TypeToken<List<Mp3List>> token = new TypeToken<List<Mp3List>>(){
		//
		// };
		// 指定"令牌"以什么方式解析
		TypeToken<List<T>> typeOfT = new TypeToken<List<T>>() {
		};
		// 将字符串转为ListBean
		lists = gson.fromJson(json, typeOfT.getType());

		return lists;
	}

	/**
	 * 
	 * 方法名：parseToJson<BR>
	 * 此方法描述的是：将InputStream输入流转换成字符串
	 * 
	 * @param is
	 * @return
	 * @throws Exception
	 *             String
	 */
	private static String parseToJson(InputStream is) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int len = 0;
		byte buffer[] = new byte[1024];
		while ((len = is.read(buffer)) != -1) {
			baos.write(buffer, 0, len);
		}
		String msg = baos.toString();
		baos.flush();
		baos.close();
		return msg;
	}
/**
 * 
 * 方法名：getGoodsTypeFromJson<BR>  
 * 此方法描述的是： 获得商品类别ListBean  
 * @param <T>
 * @param is
 * @return
 * @throws Exception  List<Type>
 */
	public static <T> List<T> getGoodsTypeFromJson(InputStream is)
			throws Exception {
		Gson gson = new Gson();
		List<T> lists = new ArrayList<T>();
		// 将IO流转换为字符串
		String json = parseToJson(is);
		// TypeToken<List<Mp3List>> token = new TypeToken<List<Mp3List>>(){
		//
		// };
		// 指定"令牌"以什么方式解析
		TypeToken<List<T>> typeOfT = new TypeToken<List<T>>() {
		};
		// 将字符串转为ListBean
		lists = gson.fromJson(json, typeOfT.getType());

		return lists;
	}
}

package com.bentools.utils;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 * @classname:UtilsHttpConnection <BR>
 * @describe：Android网络连接相关工具类<BR>
 * @author: 朱勋康
 * @extends：UtilClass<BR>
 * @implements：<BR>
 * @version:1.0
 * @date:2015-5-6 下午2:27:10
 */
public class UtilsHttpConnection  {
	// 重写构造方法，私有化构造方法，实现单例模式
	private UtilsHttpConnection() {
		super();
	}

	/**
	 * 
	 * 方法名： geUtilsHttpConnection<BR>
	 * 此方法描述的是：提供一个公共的方法获得当前类的实例对象 <BR>
	 * 
	 * @return UtilsHttpConnection的实例
	 */
	public static UtilsHttpConnection geUtilsHttpConnection() {
		// 实例化HttpURLConnection
		return new UtilsHttpConnection();

	}

	// 重写getHttpConnection方法，获得网络连接
	public HttpURLConnection getHttpConnection(String path) throws Exception {
		// 1.将path封装成网络地址对象
		URL url = new URL(path);
		// 2.获得网络连接对象
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		// 3.设置访问方式
		conn.setRequestMethod("GET");
		// 4.设置超时
		conn.setReadTimeout(5000);
		return conn;
	}

	/**
	 * 
	 * 方法名：getHttpConnections（） <BR>
	 * 此方法描述的是： 获得网络连接的静态方法 <BR>
	 * 
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static HttpURLConnection getHttpConnections(String path)
			throws Exception {
		// 调用重写的父类的getHttpConnection获得一个网络连接
		return geUtilsHttpConnection().getHttpConnection(path);
	}

	/**
	 * 
	 * 方法名：getNetResCode <BR>
	 * 此方法描述的是： 获得服务器响应码 <BR>
	 * 
	 * @param conn
	 *            网络连接对象
	 * @return 服务器响应码 200为成功
	 * @throws Exception
	 *             异常处理
	 */
	public int getNetResCode(HttpURLConnection conn) throws Exception {
		int responseCode = conn.getResponseCode();
		return responseCode;
	}
	/**
	 * 
	*方法名：getNetResCodes  <BR>
	* 此方法描述的是： 静态的方法获得网络服务器的响应码  <BR>
	* @param conn
	* @return
	* @throws Exception
	 */
	public static int getNetResCodes(HttpURLConnection conn) throws Exception {
		//获得网络服务器响应码
		return geUtilsHttpConnection().getNetResCode(conn);
		
	}
//重写带数据请求的网络地址拼接方法
	public String getNewPath(String path, Map<String, String> data) {
		//实例化一个字符集缓冲对象
		StringBuffer sb=new StringBuffer(path);
			//1.拼接网络请求地址
			sb.append("?");
			//将map转换为Set集合
			for (Entry<String, String> temp : data.entrySet()){
				//添加名
				sb.append(temp.getKey()).append("=");
				//添加值
				sb.append(temp.getValue()).append("&");
			}
			//去掉最后一个“&”符号
			sb.deleteCharAt(sb.length()-1);
			//将拼接后的地址赋值给新网络地址对象
			return sb.toString();
	}
	/**
	 * 
	*方法名：  getNewPaths<BR>
	* 此方法描述的是：数据请求的网络地址拼接静态调用方法   <BR>
	* @param path 原网络地址
	* @param data 请求数据
	* @return 拼接后的数据请求的网络地址
	 */
	public static String getNewPaths(String path, Map<String, String> data) {
		//调用getNewPath方法返回拼接后的网络地址
		return geUtilsHttpConnection().getNewPath(path, data);
		
	}
}

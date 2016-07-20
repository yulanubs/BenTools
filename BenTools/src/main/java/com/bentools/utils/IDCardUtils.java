/**
 * 
 */
package com.bentools.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 类说明：身份证验证帮助类
 * 
 * @author tianshuguang@tomstaff.com
 * @date 2014-11-14
 * @version 1.0
 */
public class IDCardUtils {

	// 1.如果是15位的号码，根据最末位的奇偶数判断，双数为＂女＂，单数为＂男＂
	// 2.如果是18位的号码，根据号码的倒数第二位的奇偶数判断，双数为＂女＂，单数为＂男＂
	public static int checkSexByID(String idStr) {
		int a = -1;
		if (idStr.length() == 18) {
			try {
				a = Integer.parseInt(idStr.substring(16, 17));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				a = -1;
			}
		} else if (idStr.length() == 15) {
			try {
				a = Integer.parseInt(idStr.substring(14, 15));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				a = -1;
			}
		}
		if (a > 0 && a % 2 == 0) {
			return 1; // 女
		} else {
			return 0;
		}
	}
	

	public static boolean checkTPName(String name) {
		Pattern pattern = Pattern
				.compile("([\u4e00-\u9fa5]+)|([a-zA-Z]+/[a-zA-Z]+(\\s[a-zA-Z]+)?)"); // dyj
		Matcher matcher = pattern.matcher(name);
		return matcher.matches();
	}


}

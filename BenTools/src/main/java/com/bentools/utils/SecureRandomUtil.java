package com.bentools.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SecureRandomUtil {
	public static int NextInt(int n){
		SecureRandom secureRandom = null;
		try {
			secureRandom = SecureRandom.getInstance("SHA1PRNG");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			secureRandom = new SecureRandom();
		}
		return secureRandom.nextInt(n);
	}
	
	public static String getRandomS1(String ranStr, int len) {
		if (ranStr.length() == len) {
			return ranStr;
		}

		int length = ranStr.length();
		if (length > len) {
			return ranStr.substring(length - len, length);
		} else {
			String str = genRandomNumAndLetter(len - length);
			return str+ranStr;
		}
	}

	public static String genRandomNumAndLetter(int pwd_len) {
		// 35是因为数组是从0开始的，52个字母+10个数字
		final int maxNum = 62;
		int i; // 生成的随机数
		int count = 0; // 生成的密码的长度
		char[] str = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a',
				'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
				'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z','A',
				'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
				'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
		StringBuffer pwd = new StringBuffer();
		while (count < pwd_len) {
			// 生成随机数，取绝对值，防止生成负数，

			i = NextInt(maxNum); // 生成的数最大为62-1

			if (i >= 0 && i < str.length) {
				pwd.append(str[i]);
				count++;
			}
		}
		return pwd.toString();
	}
}

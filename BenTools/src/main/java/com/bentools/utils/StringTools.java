package com.bentools.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.widget.TextView;

public class StringTools {

	public static boolean checkEmail(String input) {
		Pattern p = Pattern
				.compile("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\\.([a-zA-Z0-9_-])+)+$");
		Matcher m = p.matcher(input);
		return m.matches();
	}
	
	public static boolean checkTelephoneNum(String input) {
		Pattern pattern = Pattern
				.compile("^1\\d{10}$");
		Matcher matcher = pattern.matcher(input);
		return matcher.matches();
	}
	
	/**
	 * 编码
	 * 
	 * @param text
	 * @param x1
	 * @param x2
	 * @return
	 */
	public static String X2X(String text, String x1, String x2) {
		if (text == null)
			return "";
		String result = "";
		try {
			result = new String(text.getBytes(x1), x2);
		} catch (java.io.UnsupportedEncodingException ex) {
			result = text;
			ex.printStackTrace(System.err);
		}
		return result;
	}
	
	public static String stringMatch(String input, TextView textView,int view_Length) {
		boolean alreadyAppend = false;
		String outPutString = input;
		
		TextPaint paint = textView.getPaint();
		char[] chars = input.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < chars.length; i++) {
			sb.append(chars[i]);
			if (paint.measureText(sb.toString()) >= view_Length
					&& !alreadyAppend) {
				sb.insert(i - 1, "\n");
				alreadyAppend = true;
			}
		}
		outPutString = sb.toString();
		return outPutString;
	}
	
	/**
	 * 字体变小
	 * @param str
	 * @param formatNum 格式的个数，String最后的字符开始
	 * @param colorString 变小的颜色
	 * @param sizeScale 变小的倍数
	 * @return
	 */
	public static SpannableString StrFormatSize(String str,int formatNum, String colorString,
			float sizeScale) {
		if (TextUtils.isEmpty(str))
			str = "";
		SpannableString msp = new SpannableString(str);
		if (!TextUtils.isEmpty(str)) {
			msp.setSpan(new RelativeSizeSpan(sizeScale), str.length() - formatNum,
					str.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			// 设置字体前景色
			msp.setSpan(new ForegroundColorSpan(Color.parseColor(colorString)),
					str.length() - 1, str.length(),
					Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // 设置前景色为洋红色
		}
		return msp;
	}
	/**
	 * 字体变小
	 * @param str
	 * @param start 变小字符的开始索引
	 * @param end 变小字符的结束索引
	 * @param sizeScale 变小的倍数
	 * @return
	 */
	public static SpannableString StrFormatSize(String str,int start,int end,float sizeScale) {
		if (TextUtils.isEmpty(str))
			str = "";
		SpannableString msp = new SpannableString(str);
		if (!TextUtils.isEmpty(str)) {
			msp.setSpan(new RelativeSizeSpan(sizeScale), start,
					end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		}
		return msp;
	}
	/**
	 * 
	 * @param str
	 * @param start 开始变小的索引
	 * @param sizeScale 变小的倍数
	 * @return
	 */
	public static SpannableString StrFormatSize(String str,int start,float sizeScale) {
		if (TextUtils.isEmpty(str))
			str = "";
		SpannableString msp = new SpannableString(str);
		if (!TextUtils.isEmpty(str)) {
			msp.setSpan(new RelativeSizeSpan(sizeScale), start,
					str.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		}
		return msp;
	}
	/**
	 * 
	 * @param str
	 * @param start 开始变小的索引
	 * @param sizeScale 变小的倍数
	 * @return
	 */
	public static SpannableString StrFormatSize(String str,float sizeScale) {
		if (TextUtils.isEmpty(str))
			str = "";
		SpannableString msp = new SpannableString(str);
		if (!TextUtils.isEmpty(str)) {
			msp.setSpan(new RelativeSizeSpan(sizeScale), str.lastIndexOf("."),
					str.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		}
		return msp;
	}
	
//	public static String stringDes(String str){
//		UleLog.debug("stringDes", "加密前" + str);
//		try {
//			str = DESSecret.encryptDES(str, App.password, App.iv);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		UleLog.debug("stringDes", "加密后" + str);
//		return str;
//	}
}

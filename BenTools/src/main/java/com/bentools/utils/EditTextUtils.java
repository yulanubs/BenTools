/**
 * 
 */
package com.bentools.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.text.method.NumberKeyListener;
import android.widget.EditText;

/**
 * 类说明： EditText 控制帮助类
 * 
 * @author tianshuguang@tomstaff.com
 * @date 2014-7-29
 * @version 1.0
 */
public class EditTextUtils {
	
	/**
	 * @description:手机号码输入的控制
	 * @author tianshuguang@tomstaff.com
	 * @date: 2014-10-29-下午3:40:16
	 * @param edit
	 */
	public static void controlPhoneInputLength(final EditText edit,
			final int lower, final int upper) {

		edit.addTextChangedListener(new TextWatcher() {

			private String before = "";

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

				if (ValueUtils.isStrEmpty(s.toString())
						|| this.before.equals(s.toString())) {
					return;
				}

				int len = s.length();
				int end = edit.getSelectionEnd();

				if (len < lower || len > upper) {
					s = s.subSequence(0, upper);
				}

				if (end >= upper) {
					end = upper;
				}

				// 设置光标的位置
				edit.setText(s);
				edit.setSelection(end);
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				before = s.toString();
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		/** 设置输入类型为手机号码,控制输入框智能输入数字,输入框只接收数字0-9 **/
		controlOnlyInputNum(edit);
	}
	
	/**
	 * @author tianshuguang@tomstaff.com
	 * @date 2014-10-29
	 * @version 设置输入类型为数字,控制输入框智能输入数字,输入框只接收数字0-9
	 */
	public static void controlOnlyInputNum(final EditText edit) {
		edit.setKeyListener(new NumberKeyListener() {

			@Override
			public int getInputType() {
				// 0无键盘 1英文键盘 2模拟键盘 3数字键盘
				return 3;
			}

			@Override
			protected char[] getAcceptedChars() {
				char[] myChar = { '1', '2', '3', '4', '5', '6', '7', '8', '9',
						'0' };
				return myChar;
			}
		});
	}

	/**
	 * @author tianshuguang@tomstaff.com
	 * @date 2014-7-29
	 * @version 控制金额不能输入除数字和"."以外的字符
	 */
	public static void controlMoneyNum(final EditText edit) {
		edit.setKeyListener(new NumberKeyListener() {

			@Override
			public int getInputType() {
				// 0无键盘 1英文键盘 2模拟键盘 3数字键盘
				return 3;
			}

			@Override
			protected char[] getAcceptedChars() {
				char[] myChar = { '.', '1', '2', '3', '4', '5', '6', '7', '8',
						'9', '0' };
				return myChar;
			}
		});
	}

	/**
	 * @author tianshuguang@tomstaff.com
	 * @date 2014-7-29
	 * @version 控制不能输入中文
	 */
	public static void controlInputChinese(final EditText editText) {

		editText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				String editable = editText.getText().toString();
				String str = stringFilter(editable.toString());
				if (!editable.equals(str)) {
					editText.setText(str);
					// 设置新的光标所在位置
					editText.setSelection(str.length());
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});

	}
	
	// 字符过滤，只允许字母和数字
	public static String stringFilter(String str) throws PatternSyntaxException {
		// 只允许字母和数字
		String regEx = "[^a-zA-Z0-9]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}
	
	/** 
	 * @author tianshuguang@tomstaff.com
	 * @date 2014-7-29
	 * @version EditText输入长度控制
	 */
	public static void controlEditTextInputLength(EditText edit, int length) {
		edit.setFilters(new InputFilter[] { new InputFilter.LengthFilter(length) });
	}
	
	public boolean isNumber(String str)
    {
        Pattern pattern= Pattern.compile("[0-9]*");
        Matcher match=pattern.matcher(str);
        if(match.matches()==false)
        {
             return false;
        }
        else
        {
             return true;
        }
    }
	
}

package com.bentools.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;

public class PhoneUtility {

	Context _context;
	public static final String ACTION_SMS_SEND = "com.tom.ule.sms_send";
	public static final String ACTION_SMS_DELIVERY = "com.tom.ule.sms_delivery";
	
	public static final int CHINA_MOBILE = 10086;
	public static final int CHINA_UNICOM = 10010;
	public static final int CHINA_TELECOM = 10000;
	public static final int UNKNOW_OPERATOR = -1;
	
	private PhoneUtility(Context ctx){
		this._context = ctx;
	}
	
	private static PhoneUtility shortMessageUtil;
	
	public static PhoneUtility getInstance(Context ctx){
		if (shortMessageUtil == null){
			shortMessageUtil = new PhoneUtility(ctx);
		}
		return shortMessageUtil;
	}
	
	public List<Map<String, String>> getAllContacters(){
		ContentResolver contentResolver = _context.getContentResolver();
		Cursor cursor = contentResolver.query(Phone.CONTENT_URI, 
				new String[]{Phone.DISPLAY_NAME,Phone.NUMBER}, null, null, null);
		List<Map<String, String>> contacters = new ArrayList<Map<String,String>>();
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			Map<String, String> contacter = new HashMap<String, String>();
			String name = cursor.getString(cursor.getColumnIndex(Phone.DISPLAY_NAME));
			String number = cursor.getString(cursor.getColumnIndex(Phone.NUMBER));
			contacter.put(name, number);
			contacters.add(contacter);
			cursor.moveToNext();
		}
		cursor.close();
		return contacters;
	}
	
	public void sendShortMessages(String content,String target){
		SmsManager smg = SmsManager.getDefault();
		Intent sendIntent = new Intent(ACTION_SMS_SEND);
		PendingIntent sendPi = PendingIntent.getBroadcast(_context, 0, sendIntent, 0);
		Intent deliveryIntent = new Intent(ACTION_SMS_DELIVERY);
		PendingIntent deliveryPi = PendingIntent.getBroadcast(_context, 0, deliveryIntent, 0);
		smg.sendTextMessage(target, "", content, sendPi, deliveryPi);
	}
	
	public int getSimOperatorType(){
		TelephonyManager tm = (TelephonyManager) _context.getSystemService(Context.TELEPHONY_SERVICE);
    	String simOperator = tm.getSimOperator();
    	if (simOperator.equals("46000") || simOperator.equals("46002") || simOperator.equals("46007")){
    		return CHINA_MOBILE;
    	}else if (simOperator.equals("46001") || simOperator.equals("46006")){
    		return CHINA_UNICOM;
    	}else if (simOperator.equals("46003") || simOperator.equals("46005")){
    		return CHINA_TELECOM;
    	}else{
    		return UNKNOW_OPERATOR;
    	}
	}
	
	public boolean getSimReady(){
		TelephonyManager tm = (TelephonyManager) _context.getSystemService(Context.TELEPHONY_SERVICE);
		int state = tm.getSimState();
		if (state == TelephonyManager.SIM_STATE_ABSENT){
			return false;
		}else{
			return true;
		}
	}
	
}

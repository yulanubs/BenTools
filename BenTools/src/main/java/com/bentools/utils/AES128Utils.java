package com.bentools.utils;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import android.util.Base64;


/**
 * AES128加解密_秒杀用
 * @author 李云锋
 * @date 2015-11-16
 */
public class AES128Utils {
	
	private static int keyLength = 128;
	
	public static String encode(String secretKey, byte[] iv, String str) throws Exception {
		
		if (secretKey.length() == 0 || secretKey == null) {
			throw new NullPointerException("Please give secretKey");
		}

		if (str.length() == 0 || str == null) {
			throw new NullPointerException("Please give str");
		}

		try {
			SecretKeySpec skeySpec = getKey(secretKey);
			byte[] clearText = str.getBytes("UTF8");

			IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivParameterSpec);

			String encrypedValue = new String(Base64.encode(cipher.doFinal(clearText),Base64.DEFAULT));
			return encrypedValue;

		} catch (Exception e) {
			throw e;
		} 
	}
	
	public static String encode(byte[] secretKey, byte[] iv, byte[] text) throws Exception {
		
		if (secretKey.length == 0 || secretKey == null) {
			throw new NullPointerException("Please give secretKey");
		}

		if (text.length == 0 || text == null) {
			throw new NullPointerException("Please give str");
		}

		try {
			SecretKeySpec skeySpec = getKey(secretKey);

			IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivParameterSpec);

			String encrypedValue = new String(Base64.encode(cipher.doFinal(text),Base64.DEFAULT));
			return encrypedValue;

		} catch (Exception e) {
			throw e;
		} 
	}

	public static String decode(String secretKey, byte[] iv, String str) throws Exception {
		try {
			if(str.length()>8800){
				throw new Exception("9999(非法参数)");
			}
			
			SecretKey key = getKey(secretKey);

			IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

			byte[] encrypedPwdBytes = Base64.decode(str,Base64.DEFAULT);

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
			cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);
			byte[] decrypedValueBytes = (cipher.doFinal(encrypedPwdBytes));

			return new String(decrypedValueBytes);

		} catch (Exception e) {
			throw e;
		}
	}
	
	public static byte[] decode(byte[] password, byte[] iv, String text) throws Exception {

		if (password.length == 0 || password == null) {
			throw new NullPointerException("Please give Password");
		}

		if (text.length() == 0 || text == null) {
			throw new NullPointerException("Please give text");
		}

		byte[] keyBytes = new byte[keyLength / 8];
		// explicitly fill with zeros
		Arrays.fill(keyBytes, (byte) 0x0);
		
		// if password is shorter then key length, it will be zero-padded
		// to key length
		byte[] passwordBytes = password;
		int length = passwordBytes.length < keyBytes.length ? passwordBytes.length : keyBytes.length;
		System.arraycopy(passwordBytes, 0, keyBytes, 0, length);
		SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
		
		// IMPORTANT TO GET SAME RESULTS ON iOS and ANDROID
		// Arrays.fill(iv, (byte) 0x00);
		IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
		
		byte[] encrypedPwdBytes = Base64.decode(text,Base64.DEFAULT);
		// cipher is not thread safe
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
		cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);
		byte[] decrypedValueBytes = (cipher.doFinal(encrypedPwdBytes));
		
		return decrypedValueBytes;
	}

	private static SecretKeySpec getKey(String password) throws UnsupportedEncodingException {

		byte[] keyBytes = new byte[keyLength / 8];
		Arrays.fill(keyBytes, (byte) 0x0);

		byte[] passwordBytes = password.getBytes("UTF-8");
		int length = passwordBytes.length < keyBytes.length ? passwordBytes.length : keyBytes.length;
		System.arraycopy(passwordBytes, 0, keyBytes, 0, length);
		SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
		return key;
	}
	
	private static SecretKeySpec getKey(byte[] passwordBytes) throws UnsupportedEncodingException {
		byte[] keyBytes = new byte[keyLength / 8];
		Arrays.fill(keyBytes, (byte) 0x0);
		int length = passwordBytes.length < keyBytes.length ? passwordBytes.length : keyBytes.length;
		System.arraycopy(passwordBytes, 0, keyBytes, 0, length);
		SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
		return key;
	}
    
    //签名
    public static String sign(String key, String datas) {  
        String reString = "";  
  
        try  
        {  
            byte[] data = key.getBytes("UTF-8");    
            //根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称     
            SecretKey secretKey = new SecretKeySpec(data, "HmacSHA1");     
            //生成一个指定 Mac 算法 的 Mac 对象     
            Mac mac = Mac.getInstance("HmacSHA1");     
            //用给定密钥初始化 Mac 对象     
            mac.init(secretKey);      
            
            byte[] text = datas.getBytes("UTF-8");      
            //完成 Mac 操作      
            byte[] resultData = mac.doFinal(text);     
            reString = new String(Base64.encode(resultData,Base64.DEFAULT));  
  
        } catch (Exception e)  
        {  
        }  
          
        return reString;  
    }
	
}
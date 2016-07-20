package com.bentools.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import android.util.Base64;

public class AES256CbcEncry {
	private static final String TAG = "AES256CbcEncry";
	/**
	 * Encodes a String in AES-256 with a given key
	 * 
	 * @param context
	 * @param password
	 * @param text
	 * @return String Base64 and AES encoded String
	 */
	public static String encode(String keyString, byte[] iv,String stringToEncode)
			throws NullPointerException {
		BenLog.debug(TAG, "encode keyString "+keyString);
		BenLog.debug(TAG, "encode iv "+new String(iv));
		if (keyString.length() == 0 || keyString == null) {
			throw new NullPointerException("Please give Password");
		}

		if (stringToEncode.length() == 0 || stringToEncode == null) {
			throw new NullPointerException("Please give text");
		}

		try {
			SecretKeySpec skeySpec = getKey(keyString);
			byte[] clearText = stringToEncode.getBytes("UTF8");

			// IMPORTANT TO GET SAME RESULTS ON iOS and ANDROID
			
//			Arrays.fill(iv, (byte) 0x00);
			IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
//			Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
			// Cipher is not thread safe
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivParameterSpec);

			String encrypedValue = Base64.encodeToString(cipher.doFinal(clearText), Base64.DEFAULT);
			return encrypedValue;

		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * Decodes a String using AES-256 and Base64
	 * 
	 * @param context
	 * @param password
	 * @param text
	 * @return desoded String
	 */
	public static String decode(String password, byte[] iv,String text)
			throws NullPointerException {
		BenLog.debug(TAG, "decode keyString "+password);
		BenLog.debug(TAG, "encode iv "+new String(iv));
		if (password.length() == 0 || password == null) {
			throw new NullPointerException("Please give Password");
		}

		if (text.length() == 0 || text == null) {
			throw new NullPointerException("Please give text");
		}

		try {
			SecretKey key = getKey(password);

			// IMPORTANT TO GET SAME RESULTS ON iOS and ANDROID
//			Arrays.fill(iv, (byte) 0x00);
			IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

			byte[] encrypedPwdBytes = Base64.decode(text, Base64.DEFAULT);
			// cipher is not thread safe
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
			cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);
			byte[] decrypedValueBytes = (cipher.doFinal(encrypedPwdBytes));

			String decrypedValue = new String(decrypedValueBytes);
			return decrypedValue;

		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static byte[] decode(byte[] password, byte[] iv,String text)
			throws NullPointerException{

		if (password.length == 0 || password == null) {
			throw new NullPointerException("Please give Password");
		}

		if (text.length() == 0 || text == null) {
			throw new NullPointerException("Please give text");
		}

		try {
			// You can change it to 128 if you wish
			int keyLength = 256;
			byte[] keyBytes = new byte[keyLength / 8];
			// explicitly fill with zeros
			Arrays.fill(keyBytes, (byte) 0x0);

			// if password is shorter then key length, it will be zero-padded
			// to key length
			byte[] passwordBytes = password;
			int length = passwordBytes.length < keyBytes.length ? passwordBytes.length
					: keyBytes.length;
			System.arraycopy(passwordBytes, 0, keyBytes, 0, length);
			SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");

			// IMPORTANT TO GET SAME RESULTS ON iOS and ANDROID
//			Arrays.fill(iv, (byte) 0x00);
			IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

			byte[] encrypedPwdBytes = Base64.decode(text, Base64.DEFAULT);
			// cipher is not thread safe
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
			cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);
			byte[] decrypedValueBytes = (cipher.doFinal(encrypedPwdBytes));

			return decrypedValueBytes;

		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Generates a SecretKeySpec for given password
	 * 
	 * @param password
	 * @return SecretKeySpec
	 * @throws UnsupportedEncodingException
	 */
	private static SecretKeySpec getKey(String password)
			throws UnsupportedEncodingException {

		// You can change it to 128 if you wish
		int keyLength = 256;
		byte[] keyBytes = new byte[keyLength / 8];
		// explicitly fill with zeros
		Arrays.fill(keyBytes, (byte) 0x0);

		// if password is shorter then key length, it will be zero-padded
		// to key length
		byte[] passwordBytes = password.getBytes("UTF-8");
		int length = passwordBytes.length < keyBytes.length ? passwordBytes.length
				: keyBytes.length;
		System.arraycopy(passwordBytes, 0, keyBytes, 0, length);
		SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
		return key;
	}

	public static void main(String[] args) throws UnsupportedEncodingException {

	}
}

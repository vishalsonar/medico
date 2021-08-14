package com.sonar.vishal.medico.common.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.sonar.vishal.medico.common.message.common.Constant;

public class Hashing {

	private Hashing() {
		throw new IllegalStateException("Utility class");
	}

	public static String getHashValue(String message) {
		String result = null;
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance(Constant.SHA3256);
			byte[] hashbytes = digest.digest(message.getBytes(StandardCharsets.UTF_8));
			result = bytesToHex(hashbytes);
		} catch (NoSuchAlgorithmException e) {
			Logger.error(LoggerMessage.HASHING_CLASS_NAME, e.getMessage());
		}
		return result;
	}

	private static String bytesToHex(byte[] hash) {
		StringBuilder hexString = new StringBuilder(2 * hash.length);
		for (int i = 0; i < hash.length; i++) {
			String hex = Integer.toHexString(0xff & hash[i]);
			if (hex.length() == 1) {
				hexString.append(Constant.CHAR_ZERO);
			}
			hexString.append(hex);
		}
		return hexString.toString();
	}
}

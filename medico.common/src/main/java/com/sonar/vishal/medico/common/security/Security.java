package com.sonar.vishal.medico.common.security;

import java.security.Key;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;

public class Security {

	private static Mac mac;
	private static Key key;

	private Security() {
		throw new IllegalStateException("Utility class");
	}

	public static synchronized void init() {
		try {
			KeyGenerator keyGen = KeyGenerator.getInstance("AES");
			mac = Mac.getInstance("HmacSHA256");
			keyGen.init(256);
			key = keyGen.generateKey();
			mac.init(key);
		} catch (Exception e) {
			// Do Nothing
		}
	}

	public static synchronized void init(Key key) {
		try {
			Security.key = key;
			mac.init(key);
		} catch (Exception e) {
			// Do Nothing
		}
	}

	public static synchronized boolean isValidMessage(String message, String mac) {
		String messageMac = generateMac(message);
		return mac.equals(messageMac);
	}

	public static synchronized String generateMac(String message) {
		StringBuilder builder = new StringBuilder();
		byte[] byteMessage = mac.doFinal(message.getBytes());
		for (byte singleByte : byteMessage) {
			builder.append(Math.abs(singleByte));
		}
		return builder.toString();
	}

	public static synchronized Key getKey() {
		return key;
	}
}

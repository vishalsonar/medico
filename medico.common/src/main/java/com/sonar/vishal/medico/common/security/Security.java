package com.sonar.vishal.medico.common.security;

import java.security.Key;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;

public class Security {

	private static Mac MAC;
	private static Key KEY;
	private static Security SECURITY;

	private Security() {

	}

	public static Security getIntance() {
		if (SECURITY == null) {
			SECURITY = new Security();
		}
		return SECURITY;
	}

	public Security init() {
		try {
			KeyGenerator keyGen = KeyGenerator.getInstance("AES");
			MAC = Mac.getInstance("HmacSHA256");
			keyGen.init(256);
			KEY = keyGen.generateKey();
			MAC.init(KEY);
			return this;
		} catch (Exception e) {
			return null;
		}
	}

	public Security init(Key KEY) {
		try {
			Security.KEY = KEY;
			MAC.init(KEY);
			return this;
		} catch (Exception e) {
			return null;
		}
	}

	public boolean isValidMessage(String message, String mac) {
		String messageMac = generateMac(message);
		return mac.equals(messageMac);
	}

	public String generateMac(String message) {
		StringBuilder builder = new StringBuilder();
		byte[] byteMessage = MAC.doFinal(message.getBytes());
		for (byte singleByte : byteMessage) {
			builder.append(Math.abs(singleByte));
		}
		return builder.toString();
	}

	public Key getKey() {
		return KEY;
	}
}

package com.sonar.vishal.ui.util;

public class UIConstant {
	
	private UIConstant() {
		throw new IllegalStateException("Constant class");
	}
	
	public static final int PASSWORD_MIN_LENGTH = 8;
	public static final int TWENTY = 20;

	public static final String EMPTY = "";
	public static final String REQUIRED_MANDATORY_FIELD = "Required Field is Empty";
	public static final String INVALID_PASSWORD = "Invalid Password";
	public static final String PASSWORD_MISMATCH = "Password Mismatch";
	public static final String INVALID_REQUIRED_FIELD = "Invalid Require Field or Length Exceed";
	public static final String SHORT_PASSWORD_LENGTH = "Password length should be greater than " + PASSWORD_MIN_LENGTH + " digit";
}

package com.sonar.vishal.ui.definition;

import com.sonar.vishal.ui.exception.MedicoValidationException;
import com.sonar.vishal.ui.util.UIUtil;

public interface Validator<T> {

	public default void isAlphaNumericSpaceHyphen(String key, String errorMsg) throws MedicoValidationException {
		if (!UIUtil.isAlphaNumericSpaceHypenString(key)) {
			throw new MedicoValidationException(errorMsg);
		}
	}

	public default void isNumeric(String key, String errorMsg) throws MedicoValidationException {
		if (!UIUtil.isNumericString(key)) {
			throw new MedicoValidationException(errorMsg);
		}
	}
	
	public default void isDecimalNumeric(String key, String errorMsg) throws MedicoValidationException {
		if (!UIUtil.isDecimalNumericString(key)) {
			throw new MedicoValidationException(errorMsg);
		}
	}

	public default void isAlphaNumeric(String key, String errorMsg) throws MedicoValidationException {
		if (!UIUtil.isAlphaNumericString(key)) {
			throw new MedicoValidationException(errorMsg);
		}
	}
	
	public default void isAlphaNumericSpace(String key, String errorMsg) throws MedicoValidationException {
		if (!UIUtil.isAlphaNumericSpaceString(key)) {
			throw new MedicoValidationException(errorMsg);
		}
	}

	void doValidation(T data) throws MedicoValidationException;
}

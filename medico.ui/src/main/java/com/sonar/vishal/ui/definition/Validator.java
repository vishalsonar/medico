package com.sonar.vishal.ui.definition;

import com.sonar.vishal.ui.exception.MedicoValidationException;

public interface Validator<T> {

	void doValidation(T data) throws MedicoValidationException;
}

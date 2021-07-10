package com.sonar.vishal.ui.validator;

import com.sonar.vishal.medico.common.pojo.Address;
import com.sonar.vishal.ui.definition.Validator;
import com.sonar.vishal.ui.exception.MedicoValidationException;
import com.sonar.vishal.ui.util.UIConstant;
import com.sonar.vishal.ui.util.UIUtil;

public class AddressDataValidator implements Validator<Address> {

	private static final String INVALID_LINE1 = "Line 1, must not contains special character";
	private static final String INVALID_LINE2 = "Line 2, must not contains special character";
	private static final String INVALID_CITY = "Invalid City";
	private static final String INVALID_PIN = "Invalid Pin";
	private static final String INVALID_STATE = "Invalid State";

	@Override
	public void doValidation(Address data) throws MedicoValidationException {
		String line1 = data.getLine1();
		String line2 = data.getLine2();
		String city = data.getCity();
		String pin = data.getPinCode();
		String state = data.getState();
		if (line1 == null || line2 == null || city == null) {
			throw new MedicoValidationException(UIConstant.ALL_FIELDS_MANDATORY);
		}
		if (pin == null || state == null) {
			throw new MedicoValidationException(UIConstant.ALL_FIELDS_MANDATORY);
		}
		if (line1.trim().length() == 0 || line2.trim().length() == 0 || city.trim().length() == 0) {
			throw new MedicoValidationException(UIConstant.ALL_FIELDS_MANDATORY);
		}
		if (pin.trim().length() == 0 || state.trim().length() == 0) {
			throw new MedicoValidationException(UIConstant.ALL_FIELDS_MANDATORY);
		}
		if (!UIUtil.isAddressLineString(line1)) {
			throw new MedicoValidationException(INVALID_LINE1);
		}
		if (!UIUtil.isAddressLineString(line2)) {
			throw new MedicoValidationException(INVALID_LINE2);
		}
		if (!UIUtil.isString(city)) {
			throw new MedicoValidationException(INVALID_CITY);
		}
		if (!UIUtil.isString(state)) {
			throw new MedicoValidationException(INVALID_STATE);
		}
		if (!UIUtil.isNumericString(pin)) {
			throw new MedicoValidationException(INVALID_PIN);
		}
	}

}

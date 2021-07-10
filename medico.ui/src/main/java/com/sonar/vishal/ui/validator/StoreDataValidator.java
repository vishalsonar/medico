package com.sonar.vishal.ui.validator;

import com.sonar.vishal.medico.common.pojo.Address;
import com.sonar.vishal.medico.common.pojo.Store;
import com.sonar.vishal.medico.common.structure.StoreData;
import com.sonar.vishal.ui.definition.Validator;
import com.sonar.vishal.ui.exception.MedicoValidationException;
import com.sonar.vishal.ui.util.UIConstant;
import com.sonar.vishal.ui.util.UIUtil;

public class StoreDataValidator implements Validator<StoreData> {

	private static final String INVALID_CIN = "Invalid CIN Number";
	private static final String INVALID_DL_EXPIRY = "Invalid DL Expiry";
	private static final String INVALID_DL_NUMBER = "Invalid DL Number";
	private static final String INVALID_FASSAI = "Invalid FASSAI";
	private static final String INVALID_GST = "Invalid GST";
	private static final String INVALID_NAME = "Invalid Store Name";
	private static final String INVALID_PAN = "Invalid PAN Number";
	private static final String INVALID_PHONE_NUMBER = "Invalid Phone Number";
	private static final String INVALID_SX_DL_EXPIRY = "Invalid SX DL Expiry";
	private static final String INVALID_SX_DL_NUMBER = "Invalid SX DL Number";

	@Override
	public void doValidation(StoreData data) throws MedicoValidationException {
		Store store = data.getStore();
		Address address = store.getAddress();
		String name = store.getName();
		if (address == null) {
			throw new MedicoValidationException(UIConstant.ALL_FIELDS_MANDATORY);
		}
		handleNullEmpty(store);
		if (!UIUtil.isAlphaNumericSpaceString(name)) {
			throw new MedicoValidationException(INVALID_NAME);
		}
		new AddressDataValidator().doValidation(address);
		handleFields(store);
	}
	
	private void handleNullEmpty(Store store) throws MedicoValidationException {
		if (store.getCinNumber().trim().length() == 0 || store.getDlExpiry().trim().length() == 0 || store.getDlNumber().trim().length() == 0) {
			throw new MedicoValidationException(UIConstant.ALL_FIELDS_MANDATORY);
		}
		if (store.getFassaiNumber().trim().length() == 0 || store.getGstin().trim().length() == 0 || store.getName().trim().length() == 0) {
			throw new MedicoValidationException(UIConstant.ALL_FIELDS_MANDATORY);
		}
		if (store.getPanNumber().trim().length() == 0 || store.getPhoneNumber().trim().length() == 0) {
			throw new MedicoValidationException(UIConstant.ALL_FIELDS_MANDATORY);
		}
		if (store.getSxDlExpiry().trim().length() == 0 || store.getSxDlNumber().trim().length() == 0) {
			throw new MedicoValidationException(UIConstant.ALL_FIELDS_MANDATORY);
		}
	}

	private void handleFields(Store store) throws MedicoValidationException {
		isNumeric(store.getPhoneNumber(), INVALID_PHONE_NUMBER);
		isAlphaNumericSpaceHyphen(store.getDlNumber(), INVALID_DL_NUMBER);
		isNumeric(store.getDlExpiry(), INVALID_DL_EXPIRY);
		isAlphaNumericSpaceHyphen(store.getSxDlExpiry(), INVALID_SX_DL_NUMBER);
		isAlphaNumeric(store.getSxDlNumber(), INVALID_SX_DL_EXPIRY);
		isAlphaNumeric(store.getPanNumber(), INVALID_PAN);
		isNumeric(store.getCinNumber(), INVALID_CIN);
		isAlphaNumeric(store.getGstin(), INVALID_GST);
		isNumeric(store.getFassaiNumber(), INVALID_FASSAI);
	}
	
}

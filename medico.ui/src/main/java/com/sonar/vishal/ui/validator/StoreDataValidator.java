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
	private static final String INVALID_NAME = "Invalid Name";
	private static final String INVALID_PAN = "Invalid PAN Number";
	private static final String INVALID_PHONE_NUMBER = "Invalid Phone Number";
	private static final String INVALID_SX_DL_EXPIRY = "Invalid SX DL Expiry";
	private static final String INVALID_SX_DL_NUMBER = "Invalid SX DL Number";

	@Override
	public void doValidation(StoreData data) throws MedicoValidationException {
		Store store = data.getStore();
		Address address = store.getAddress();
		String cinNumber = store.getCinNumber();
		String dlExpiry = store.getDlExpiry();
		String dlNumber = store.getDlNumber();
		String fassai = store.getFassaiNumber();
		String gst = store.getGstin();
		String name = store.getName();
		String pan = store.getPanNumber();
		String phoneNumber = store.getPhoneNumber();
		String sxdlExpiry = store.getSxDlExpiry();
		String sxdlNumber = store.getSxDlNumber();
		if (address == null) {
			throw new MedicoValidationException(UIConstant.ALL_FIELDS_MANDATORY);
		}
		if (cinNumber.trim().length() == 0 || dlExpiry.trim().length() == 0 || dlNumber.trim().length() == 0) {
			throw new MedicoValidationException(UIConstant.ALL_FIELDS_MANDATORY);
		}
		if (fassai.trim().length() == 0 || gst.trim().length() == 0 || name.trim().length() == 0) {
			throw new MedicoValidationException(UIConstant.ALL_FIELDS_MANDATORY);
		}
		if (pan.trim().length() == 0 || phoneNumber.trim().length() == 0) {
			throw new MedicoValidationException(UIConstant.ALL_FIELDS_MANDATORY);
		}
		if (sxdlExpiry.trim().length() == 0 || sxdlNumber.trim().length() == 0) {
			throw new MedicoValidationException(UIConstant.ALL_FIELDS_MANDATORY);
		}
		if (!UIUtil.isNumericString(cinNumber)) {
			throw new MedicoValidationException(INVALID_CIN);
		}
		if (!UIUtil.isNumericString(dlExpiry)) {
			throw new MedicoValidationException(INVALID_DL_EXPIRY);
		}
		if (!UIUtil.isNumericString(dlNumber)) {
			throw new MedicoValidationException(INVALID_DL_NUMBER);
		}
		if (!UIUtil.isNumericString(fassai)) {
			throw new MedicoValidationException(INVALID_FASSAI);
		}
		if (!UIUtil.isAlphaNumericString(gst)) {
			throw new MedicoValidationException(INVALID_GST);
		}
		if (!UIUtil.isAlphaNumericSpaceString(name)) {
			throw new MedicoValidationException(INVALID_NAME);
		}
		if (!UIUtil.isAlphaNumericString(pan)) {
			throw new MedicoValidationException(INVALID_PAN);
		}
		if (!UIUtil.isNumericString(phoneNumber)) {
			throw new MedicoValidationException(INVALID_PHONE_NUMBER);
		}
		if (!UIUtil.isNumericString(sxdlNumber)) {
			throw new MedicoValidationException(INVALID_SX_DL_NUMBER);
		}
		if (!UIUtil.isNumericString(sxdlExpiry)) {
			throw new MedicoValidationException(INVALID_SX_DL_EXPIRY);
		}
		new AddressDataValidator().doValidation(address);
	}

}

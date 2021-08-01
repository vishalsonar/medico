package com.sonar.vishal.ui.window.store;

import com.sonar.vishal.medico.common.pojo.Address;
import com.sonar.vishal.medico.common.pojo.Store;
import com.sonar.vishal.ui.component.Component;
import com.sonar.vishal.ui.util.UIConstant;
import com.vaadin.data.Binder;
import com.vaadin.ui.TextField;

public class StoreWindowDecorator {

	TextField name;
	TextField line1;
	TextField line2;
	TextField city;
	TextField pinCode;
	TextField state;
	TextField phoneNumber;
	TextField dlNumber;
	TextField dlExpiry;
	TextField sxDlNumber;
	TextField sxDlExpiry;
	TextField panNumber;
	TextField cinNumber;
	TextField gst;
	TextField fassai;
	Binder<Store> storeBinder = new Binder<>();
	Binder<Address> addressBinder = new Binder<>();
	
	public StoreWindowDecorator() {
		Component component = Component.getInstance();
		name = component.getTextField(UIConstant.STORE_NAME, UIConstant.STORE_NAME, UIConstant.FIELD_LENGTH_300);
		line1 = component.getTextField(UIConstant.ADDRESS_LINE_1, UIConstant.ADDRESS_LINE_1, UIConstant.FIELD_LENGTH_300);
		line2 = component.getTextField(UIConstant.ADDRESS_LINE_2, UIConstant.ADDRESS_LINE_2, UIConstant.FIELD_LENGTH_300);
		city = component.getTextField(UIConstant.CITY, UIConstant.CITY, UIConstant.FIELD_LENGTH_300);
		pinCode = component.getTextField(UIConstant.PIN_CODE, UIConstant.PIN_CODE, UIConstant.FIELD_LENGTH_300);
		state = component.getTextField(UIConstant.STATE, UIConstant.STATE, UIConstant.FIELD_LENGTH_300);
		phoneNumber = component.getTextField(UIConstant.PHONE_NUMBER, UIConstant.PHONE_NUMBER, UIConstant.FIELD_LENGTH_300);
		dlNumber = component.getTextField(UIConstant.DL_NUMBER, UIConstant.DL_NUMBER, UIConstant.FIELD_LENGTH_300);
		dlExpiry = component.getTextField(UIConstant.DL_EXPIRY, UIConstant.DL_EXPIRY, UIConstant.FIELD_LENGTH_300);
		sxDlNumber = component.getTextField(UIConstant.SX_DL_NUMBER, UIConstant.SX_DL_NUMBER, UIConstant.FIELD_LENGTH_300);
		sxDlExpiry = component.getTextField(UIConstant.SX_DL_EXPIRY, UIConstant.SX_DL_EXPIRY, UIConstant.FIELD_LENGTH_300);
		panNumber = component.getTextField(UIConstant.PAN_NUMBER, UIConstant.PAN_NUMBER, UIConstant.FIELD_LENGTH_300);
		cinNumber = component.getTextField(UIConstant.CIN_NUMBER, UIConstant.CIN_NUMBER, UIConstant.FIELD_LENGTH_300);
		gst = component.getTextField(UIConstant.GST, UIConstant.GST, UIConstant.FIELD_LENGTH_300);
		fassai = component.getTextField(UIConstant.FASSAI, UIConstant.FASSAI, UIConstant.FIELD_LENGTH_300);
		setInputDigitLimit();
		storeBinder.bind(name, Store::getName, Store::setName);
		addressBinder.bind(line1, Address::getLine1, Address::setLine1);
		addressBinder.bind(line2, Address::getLine2, Address::setLine2);
		addressBinder.bind(city, Address::getCity, Address::setCity);
		addressBinder.bind(pinCode, Address::getPinCode, Address::setPinCode);
		addressBinder.bind(state, Address::getState, Address::setState);
		storeBinder.bind(phoneNumber, Store::getPhoneNumber, Store::setPhoneNumber);
		storeBinder.bind(dlNumber, Store::getDlNumber, Store::setDlNumber);
		storeBinder.bind(dlExpiry, Store::getDlExpiry, Store::setDlExpiry);
		storeBinder.bind(sxDlNumber, Store::getSxDlNumber, Store::setSxDlNumber);
		storeBinder.bind(sxDlExpiry, Store::getSxDlExpiry, Store::setSxDlExpiry);
		storeBinder.bind(panNumber, Store::getPanNumber, Store::setPanNumber);
		storeBinder.bind(cinNumber, Store::getCinNumber, Store::setCinNumber);
		storeBinder.bind(gst, Store::getGstin, Store::setGstin);
		storeBinder.bind(fassai, Store::getFassaiNumber, Store::setFassaiNumber);
	}
	
	private void setInputDigitLimit() {
		name.setMaxLength(50);
		phoneNumber.setMaxLength(15);
		dlNumber.setMaxLength(10);
		dlExpiry.setMaxLength(10);
		sxDlNumber.setMaxLength(10);
		sxDlExpiry.setMaxLength(10);
		panNumber.setMaxLength(10);
		cinNumber.setMaxLength(10);
		fassai.setMaxLength(10);
		line1.setMaxLength(50);
		line2.setMaxLength(50);
		pinCode.setMaxLength(10);
	}
}

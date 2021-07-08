package com.sonar.vishal.ui.window.patient;

import com.sonar.vishal.medico.common.pojo.Address;
import com.sonar.vishal.medico.common.pojo.Patient;
import com.sonar.vishal.ui.component.Component;
import com.sonar.vishal.ui.util.UIConstant;
import com.vaadin.data.Binder;
import com.vaadin.ui.TextField;

public class PatientWindowDecorator {

	TextField name;
	TextField phoneNumber;
	TextField doctorName;
	TextField line1;
	TextField line2;
	TextField city;
	TextField pinCode;
	TextField state;
	Binder<Patient> patientBinder = new Binder<>();
	Binder<Address> addressBinder = new Binder<>();

	public PatientWindowDecorator() {
		Component component = Component.getInstance();
		name = component.getTextField(UIConstant.NAME, UIConstant.PATTIENT_NAME, UIConstant.FIELD_LENGTH_300);
		phoneNumber = component.getTextField(UIConstant.PHONE_NUMBER, UIConstant.PHONE_NUMBER, UIConstant.FIELD_LENGTH_300);
		doctorName = component.getTextField(UIConstant.DOCTOR_NAME, UIConstant.DOCTOR_NAME, UIConstant.FIELD_LENGTH_300);
		line1 = component.getTextField(UIConstant.ADDRESS_LINE_1, UIConstant.ADDRESS_LINE_1, UIConstant.FIELD_LENGTH_300);
		line2 = component.getTextField(UIConstant.ADDRESS_LINE_2, UIConstant.ADDRESS_LINE_2, UIConstant.FIELD_LENGTH_300);
		city = component.getTextField(UIConstant.CITY, UIConstant.CITY, UIConstant.FIELD_LENGTH_300);
		pinCode = component.getTextField(UIConstant.PIN_CODE, UIConstant.PIN_CODE, UIConstant.FIELD_LENGTH_300);
		state = component.getTextField(UIConstant.STATE, UIConstant.STATE, UIConstant.FIELD_LENGTH_300);
		patientBinder.bind(name, Patient::getPatientName, Patient::setPatientName);
		patientBinder.bind(phoneNumber, Patient::getPhoneNumber, Patient::setPhoneNumber);
		patientBinder.bind(doctorName, Patient::getDoctorName, Patient::setDoctorName);
		addressBinder.bind(line1, Address::getLine1, Address::setLine1);
		addressBinder.bind(line2, Address::getLine2, Address::setLine2);
		addressBinder.bind(city, Address::getCity, Address::setCity);
		addressBinder.bind(pinCode, Address::getPinCode, Address::setPinCode);
		addressBinder.bind(state, Address::getState, Address::setState);
	}
}

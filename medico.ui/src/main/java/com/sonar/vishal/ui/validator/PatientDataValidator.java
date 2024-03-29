package com.sonar.vishal.ui.validator;

import com.sonar.vishal.medico.common.pojo.Address;
import com.sonar.vishal.medico.common.pojo.Patient;
import com.sonar.vishal.medico.common.structure.PatientData;
import com.sonar.vishal.ui.definition.Validator;
import com.sonar.vishal.ui.exception.MedicoValidationException;
import com.sonar.vishal.ui.util.UIConstant;

public class PatientDataValidator implements Validator<PatientData> {

	private static final String INVALID_PATIENT_NAME = "Invalid Patient Name";
	private static final String INVALID_DOCTOR_NAME = "Invalid Doctor Name";
	private static final String INVALID_PHONE_NUMBER = "Invalid Phone Number";

	@Override
	public void doValidation(PatientData data) throws MedicoValidationException {
		Patient patient = data.getPatient();
		String patientName = patient.getPatientName();
		String phoneNumber = patient.getPhoneNumber();
		String doctorName = patient.getDoctorName();
		Address address = patient.getAddress();
		if (patientName.trim().length() == 0 || phoneNumber.trim().length() == 0) {
			throw new MedicoValidationException(UIConstant.ALL_FIELDS_MANDATORY);
		}
		if (doctorName.trim().length() == 0) {
			throw new MedicoValidationException(UIConstant.ALL_FIELDS_MANDATORY);
		}
		if (address == null) {
			throw new MedicoValidationException(UIConstant.ALL_FIELDS_MANDATORY);
		}
		isAlphaNumericSpace(patientName, INVALID_PATIENT_NAME);
		isAlphaNumericSpace(doctorName, INVALID_DOCTOR_NAME);
		isNumeric(phoneNumber, INVALID_PHONE_NUMBER);
		new AddressDataValidator().doValidation(address);
	}

}

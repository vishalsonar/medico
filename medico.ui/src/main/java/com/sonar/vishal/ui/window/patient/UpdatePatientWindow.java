package com.sonar.vishal.ui.window.patient;

import com.sonar.vishal.medico.common.pojo.Address;
import com.sonar.vishal.medico.common.pojo.Patient;
import com.sonar.vishal.ui.definition.CRUDStructure;
import com.sonar.vishal.ui.listener.patient.UpdatePatientListener;
import com.sonar.vishal.ui.window.MedicoWindow;
import com.vaadin.data.Binder;
import com.vaadin.ui.TextField;

public class UpdatePatientWindow extends MedicoWindow {

	private static final long serialVersionUID = -262384955881959894L;
	private transient Patient selectedPatient;
	private Binder<Patient> patientBinder = new Binder<>();
	private Binder<Address> addressBinder = new Binder<>();

	public UpdatePatientWindow(CRUDStructure structure, Patient selectedPatient) {
		super("Update Patient", structure);
		this.selectedPatient = selectedPatient;
	}

	@Override
	public void setWindow() {
		TextField name = COMPONENT.getTextField("Name", "Patient Name", "300");
		TextField phoneNumber = COMPONENT.getTextField("Phone Number", "Phone Number", "300");
		TextField doctorName = COMPONENT.getTextField("Doctor Name", "Doctor Name", "300");
		TextField line1 = COMPONENT.getTextField("Address Line 1", "Line 1", "300");
		TextField line2 = COMPONENT.getTextField("Address Line 2", "Line 2", "300");
		TextField city = COMPONENT.getTextField("City", "City", "300");
		TextField pinCode = COMPONENT.getTextField("Pin Code", "Pin Code", "300");
		TextField state = COMPONENT.getTextField("State", "State", "300");
		patientBinder.bind(name, Patient::getPatientName, Patient::setPatientName);
		patientBinder.bind(phoneNumber, Patient::getPhoneNumber, Patient::setPhoneNumber);
		patientBinder.bind(doctorName, Patient::getDoctorName, Patient::setDoctorName);
		addressBinder.bind(line1, Address::getLine1, Address::setLine1);
		addressBinder.bind(line2, Address::getLine2, Address::setLine2);
		addressBinder.bind(city, Address::getCity, Address::setCity);
		addressBinder.bind(pinCode, Address::getPinCode, Address::setPinCode);
		addressBinder.bind(state, Address::getState, Address::setState);
		name.setValue(selectedPatient.getPatientName());
		phoneNumber.setValue(selectedPatient.getPhoneNumber());
		doctorName.setValue(selectedPatient.getDoctorName());
		line1.setValue(selectedPatient.getAddress().getLine1());
		line2.setValue(selectedPatient.getAddress().getLine2());
		city.setValue(selectedPatient.getAddress().getCity());
		pinCode.setValue(selectedPatient.getAddress().getPinCode());
		state.setValue(selectedPatient.getAddress().getPinCode());
		addComponents(name, phoneNumber, doctorName, line1, line2, city, pinCode, state);
		addAction();
		addCancelListener(this);
		addSubmitListener(new UpdatePatientListener(patientBinder, addressBinder, selectedPatient.getId(), this, structure));
	}
}

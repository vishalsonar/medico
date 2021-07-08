package com.sonar.vishal.ui.window.patient;

import com.sonar.vishal.medico.common.pojo.Address;
import com.sonar.vishal.medico.common.pojo.Patient;
import com.sonar.vishal.ui.listener.patient.AddPatientListener;
import com.sonar.vishal.ui.structure.PatientStructure;
import com.sonar.vishal.ui.window.MedicoWindow;
import com.vaadin.data.Binder;
import com.vaadin.ui.TextField;

public class AddPatientWindow extends MedicoWindow {

	private static final long serialVersionUID = 6602793284125960723L;
	private Binder<Patient> patientBinder = new Binder<>();
	private Binder<Address> addressBinder = new Binder<>();

	public AddPatientWindow(PatientStructure structure) {
		super("Add Patient", structure);
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
		addComponents(name, phoneNumber, doctorName, line1, line2, city, pinCode, state);
		addAction();
		addCancelListener(this);
		addSubmitListener(new AddPatientListener(patientBinder, addressBinder, this, structure));
	}
}

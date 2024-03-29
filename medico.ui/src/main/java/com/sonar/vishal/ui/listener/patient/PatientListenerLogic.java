package com.sonar.vishal.ui.listener.patient;

import com.sonar.vishal.medico.common.pojo.Address;
import com.sonar.vishal.medico.common.pojo.Patient;
import com.sonar.vishal.medico.common.structure.Data;
import com.sonar.vishal.medico.common.structure.PatientData;
import com.sonar.vishal.ui.definition.BiListenerLogic;
import com.sonar.vishal.ui.exception.MedicoValidationException;
import com.sonar.vishal.ui.validator.PatientDataValidator;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;

public class PatientListenerLogic implements BiListenerLogic<Binder<Patient>, Binder<Address>> {

	@Override
	public Data process(Binder<Patient> parentBinder, Binder<Address> childBinder, Integer id) throws ValidationException, MedicoValidationException {
		Patient patient = new Patient();
		Address address = new Address();
		PatientData data = new PatientData();
		childBinder.writeBean(address);
		parentBinder.writeBean(patient);
		patient.setAddress(address);
		if (id != null) {
			patient.setId(id);
		}
		data.setPatient(patient);
		new PatientDataValidator().doValidation(data);
		return data;
	}

}

package com.sonar.vishal.ui.listener.patient;

import com.sonar.vishal.medico.common.pojo.Address;
import com.sonar.vishal.medico.common.pojo.Patient;
import com.sonar.vishal.medico.common.structure.Data;
import com.sonar.vishal.medico.common.structure.PatientData;
import com.sonar.vishal.ui.definition.BiListenerLogic;
import com.vaadin.data.Binder;

public class PatientListenerLogic implements BiListenerLogic<Binder<Patient>, Binder<Address>> {

	@Override
	public Data process(Binder<Patient> parentBinder, Binder<Address> childBinder, Integer id) throws Exception {
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
		return data;
	}

}
